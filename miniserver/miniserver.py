from flask import Flask, jsonify, abort, request, Response, render_template

import db_interatcion

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route('/appregister', methods=['POST'])
def app_register():
    reg_request = request.json
    if (reg_request is not None) and ('name' in reg_request) and ('password' in reg_request) \
            and ('email' in reg_request) and ('confirm' in reg_request) and ('username' in reg_request):
        name = reg_request['name']
        username = reg_request['username']
        password = reg_request['password']
        email = reg_request['email']
        confirm = reg_request['confirm']
        if password == confirm:
            if db_interatcion.register(name, username, email, password):
                return Response("success", mimetype='text/xml')
    return Response("fail", mimetype='text/xml')


@app.route('/applogin', methods=['POST'])
def app_login():
    login_request = request.json
    if (login_request is not None) and ('username' in login_request) and ('password' in login_request):
        username = login_request['username']
        password = login_request['password']
        data = db_interatcion.login(username, password)
        if data:
            result = {"username": data[0]["username"], "points": str(data[0]['points']), "posts": data[1]}
            return jsonify(result)
    return Response(status=500)


@app.route('/appcommentsubmit', methods=['POST'])
def app_comment_submit():
    submit_request = request.json
    if (submit_request is not None) and ('username' in submit_request) and ('comment' in submit_request):
        if submit_request['comment'] == "":
            return Response(status=500)
        username = submit_request['username']
        comment = submit_request['comment']
        data = db_interatcion.comment_submit(username, comment)
        if data:
            result = {"username": data[0]["username"], "points": str(data[0]['points']), "posts": data[1]}
            return jsonify(result)
    return Response(status=500)

if __name__ == '__main__':
    app.run()
