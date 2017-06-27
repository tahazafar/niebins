import serial,time,sqlite3

arduinoSerialData = serial.Serial('com6', 9600)  # Create Serial port object called arduinoSerialData

while (1 == 1):
    if (arduinoSerialData.inWaiting() > 0):
        myData = arduinoSerialData.readline()
        myData = float(myData)
        if myData < 100:
            conn = sqlite3.connect("bin_Fullness.db")
            cursor = conn.cursor()
            sql = 'UPDATE bin_fullness SET \'paper\' = %s WHERE ID = 1' %  myData
            cursor.execute(sql)
            conn.commit()
            cursor.close()
            conn.close()

        if myData < 4:
            print('red')
        elif myData<10:
            print('yellow')
        else:
            print('green')







