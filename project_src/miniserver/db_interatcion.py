import sqlite3

# configuration
DATABASE = 'C:/Users/CY/Desktop/miniserver/database/nicebin.db'
CLEAR = True


def dict_factory(cursor, row):
    d = {}
    for idx, col in enumerate(cursor.description):
        d[col[0]] = row[idx]
    return d


def register(name, username, email, password):
    sql = """INSERT INTO users(name, username, email, password, points) VALUES (?, ?, ?, ?, ?)"""

    conn = sqlite3.connect(DATABASE)
    cursor = conn.cursor()

    try:
        cursor.execute(sql, (name, username, email, password, 0))
        conn.commit()
    except Exception as e:
        print(str(e))
        conn.rollback()
        conn.close()
        return False
    conn.close()
    return True


def login(username, password):
    sql = """SELECT * FROM users WHERE username = ?"""
    sql2 = """SELECT * FROM posts ORDER BY id DESC"""
    conn = sqlite3.connect(DATABASE)
    conn.row_factory = dict_factory
    cursor = conn.cursor()
    cursor.execute(sql, (username, ))
    result = cursor.fetchall()
    cursor.execute(sql2)
    posts = cursor.fetchall()
    conn.close()
    if result:
        if result[0]['password'] == password:
            return [result[0], posts]
    return []


def comment_submit(username, comment):
    sql1 = """INSERT INTO posts(username, comment) VALUES (?, ?)"""
    sql2 = """SELECT * FROM users WHERE username = ?"""
    sql3 = """SELECT * FROM posts ORDER BY id DESC"""
    conn = sqlite3.connect(DATABASE)
    conn.row_factory = dict_factory
    cursor = conn.cursor()
    try:
        cursor.execute(sql1, (username, comment))
        conn.commit()
    except Exception as e:
        print(str(e))
        conn.rollback()
        conn.close()
        return []
    cursor.execute(sql2, (username,))
    user_info = cursor.fetchall()
    cursor.execute(sql3)
    posts = cursor.fetchall()
    conn.close()
    return [user_info[0], posts]


if __name__ == '__main__':
    if CLEAR:
        connect = sqlite3.connect(DATABASE)
        curs = connect.cursor()
        f = open("schema/schema.sql", mode='r')
        curs.executescript(f.read())
        connect.commit()
        connect.close()

