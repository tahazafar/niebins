# this is python file for the Web part with function of <log in>,<register>,<Map of every bin`s fullness>,<read users point>and<write posts>

from flask import Flask,render_template,request,redirect,flash,g,url_for,make_response,abort
import sqlite3
from flask_sqlalchemy import SQLAlchemy
from flask_bcrypt import Bcrypt
from form import LoginForm,RegisterForm,MessageForm
from sqlalchemy import ForeignKey
from sqlalchemy.orm import relationship
from flask_login import LoginManager,login_user,login_required,logout_user,current_user


app = Flask(__name__)
db = SQLAlchemy(app)
app.config.from_object('config.BaseConfig')
bcrypt = Bcrypt(app)
login_manager = LoginManager()
login_manager.init_app(app)
login_manager.login_view = "login"
@login_manager.user_loader
def load_user(user_id):
    return User.query.filter(User.id == int(user_id)).first()

class BlogPost(db.Model):
    __tablename__ = "posts"
    id =db.Column(db.Integer,primary_key = True)
    title = db.Column(db.String, nullable=False)
    description = db.Column(db.String, nullable=False)
    author_id = db.Column(db.Integer, ForeignKey('users.id'))
    def __init__(self,title,description,author_id):
        self.title = title
        self.description = description
        self.author_id = author_id
    def __repr__(self):
        return '<title{}>'.format(self.title)

class User(db.Model):
    __tablename__ = "users"
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String, nullable=False)
    email = db.Column(db.String, nullable=False)
    password = db.Column(db.String)
    word = db.Column(db.String)
    point = db.Column(db.String)
    posts = relationship("BlogPost", backref="author")

    def __init__(self, name,word, email, password,point):
        self.name = name
        self.email = email
        self.password = bcrypt.generate_password_hash(password)
        self.word = word
        self.point = point
    def is_authenticated(self):
        return True

    def is_active(self):
        return True

    def is_anonymous(self):
        return False

    def get_id(self):
        return int(self.id)

    def __repr__(self):
        return '<name - {}>'.format(self.name)

@app.route('/', methods=['GET', 'POST'])
@login_required
def home():
    error = None
    point = current_user.point
    form = MessageForm(request.form)
    if form.validate_on_submit():
        new_message = BlogPost(
            form.title.data,
            form.description.data,
            current_user.id
        )
        db.session.add(new_message)
        db.session.commit()
        flash('New entry was successfully posted. Thanks.')
        return redirect(url_for('home'))
    else:
        posts = db.session.query(BlogPost).all()
        return render_template(
            'index.html', posts=posts, form=form, error=error,point = point)

@app.route('/welcome')
def welcome():
    return render_template('welcome.html')

@app.route('/login',methods=['GET','POST'])
def login():
    error = None
    form = LoginForm(request.form)
    if request.method == 'POST':
        if form.validate_on_submit():
            user = User.query.filter_by(name = request.form['username']).first()
            if user is not None and bcrypt.check_password_hash(user.password,request.form['password']):
               # session['logged_in'] = True
                login_user(user)
                flash('you have logged in!')
                return redirect(url_for('home'))
            else:
                error = 'Invalid credentials,please try again.'
    return render_template('login.html',form= form,error=error)

@app.route('/logout')
@login_required
def logout():
    logout_user()
    flash('you have logged out')
    return redirect( url_for('welcome') )

@app.route('/register',methods = ['GET','POST'])
def register():
    form = RegisterForm()
    if form.validate_on_submit():
        user = User(
            name=form.username.data,
            email=form.email.data,
            password=form.password.data,
            word = form.word.data,
            point = 0
        )
        db.session.add(user)
        db.session.commit()
        login_user(user)
        return redirect(url_for('home'))
    return render_template('register.html', form=form)

def connect_db():
    return sqlite3.connect('posts.db')

@app.route('/map')
def map():
    return render_template('map.html')



if __name__ == '__main__':
    app.run()#host='0.0.0.0',port=80


