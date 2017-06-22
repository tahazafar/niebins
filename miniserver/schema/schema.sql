drop table if EXISTS users;
CREATE TABLE users(
  name VARCHAR (200) NOT NULL ,
  username VARCHAR (200),
  email VARCHAR (200),
  password VARCHAR (200) NOT NULL,
  points INTEGER NOT NULL,
  PRIMARY KEY (username)
);
drop table if EXISTS posts;
CREATE TABLE posts(
  id integer primary key autoincrement,
  username VARCHAR (200) REFERENCES users(username),
  comment VARCHAR (1000) NOT NULL
);

INSERT INTO users(name, username, email, password, points) VALUES ('bogeliu', 'swing', 'bogeliu@126.com', '123', 100);
INSERT INTO posts(username, comment) VALUES ('swing', 'nice bin');
INSERT INTO posts(username, comment) VALUES ('swing', 'nice bin bin');
INSERT INTO posts(username, comment) VALUES ('swing', 'nice bin bin bin');
INSERT INTO posts(username, comment) VALUES ('swing', 'nice bin bin bin bin');
INSERT INTO posts(username, comment) VALUES ('swing', 'nice bin bin bin bin bin');