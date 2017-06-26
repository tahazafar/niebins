import sqlite3,time,serial

def change_point(point,word):
        conn = sqlite3.connect("posts.db")
        cursor = conn.cursor()
        sql = 'UPDATE users SET point = %d WHERE word = \'%s\'' % (point,word)
        cursor.execute(sql)
        conn.commit()
        cursor.close()
        conn.close()
        print(point)




arduinoSerialData = serial.Serial('com8', 9600)
time.sleep(3)
while True:
    if (arduinoSerialData.inWaiting() > 0):
        myData = arduinoSerialData.readline()
        myData = int(myData)
        if myData <0:
                time.sleep(10)
                if myData ==0:
                        conn = sqlite3.connect("posts.db")
                        cursor = conn.cursor()
                        sql = 'select \'point\ from users where word=\'apple\''
                        cursor.execute(sql)
                        point = cursor.fetchall()
                        point = int(point)+1
                        sql = 'UPDATE users SET \'point\' = %s WHERE word =\'apple\'' %  str(point)
                        cursor.execute(sql)
                        conn.commit()
                        cursor.close()
                        conn.close()
                else:
                        print('didn`t close door')


