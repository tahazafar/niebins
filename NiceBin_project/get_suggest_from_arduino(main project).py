import serial,sqlite3,time
from gtts import gTTS

def get_location_bin(bin_id):
    conn = sqlite3.connect("bin_location.db")
    cursor = conn.cursor()
    sql = 'select * from bin_location where id = %s' % bin_id
    cursor.execute(sql)
    values = cursor.fetchall()
    cursor.close()
    conn.close()
    location = values[0]
    return location

def get_fullness_bin(bin_id):     #1 混合 ，2 纸 ，3 塑料 ，4 玻璃，5 厨房
    conn = sqlite3.connect("bin_Fullness.db")
    cursor = conn.cursor()
    sql = 'select * from bin_fullness where id = %s' % bin_id
    cursor.execute(sql)
    values = cursor.fetchall()
    cursor.close()
    conn.close()
    fullness = values[0]
    return fullness

#get number near this bin
def get_near_bin_id(bin_id):
    conn = sqlite3.connect("bin_id.db")
    cursor = conn.cursor()
    sql = 'select * from bin_id where id = %s'% bin_id
    cursor.execute(sql)
    values = cursor.fetchall()
    cursor.close()
    conn.close()
    values = values[0]
    return values

def sensor(id,number):
    if number > 100:
        return id,True
    else:
        return id,False



def full(id,kind_bin):
    near1 = get_near_bin_id(id)[1]
    near2 = get_near_bin_id(id)[2]
    near3 = get_near_bin_id(id)[3]
    near4 = get_near_bin_id(id)[4]
    near5 = get_near_bin_id(id)[5]
    near6 = get_near_bin_id(id)[6]
    near7 = get_near_bin_id(id)[7]
    near8 = get_near_bin_id(id)[8]

    if 0<= int(get_fullness_bin(near1)[kind_bin]) < 75 :
        text ='I suggest you go '+ get_location_bin(near1)[1]


    elif 0<= int(get_fullness_bin(near2)[kind_bin]) < 75:
        text ='I suggest you go '+ get_location_bin(near2)[1]


    elif 0<= int(get_fullness_bin(near3)[kind_bin]) < 75:
        text ='I suggest you go '+  get_location_bin(near3)[1]


    elif 0<= int(get_fullness_bin(near4)[kind_bin]) < 75:
        text ='I suggest you go '+  get_location_bin(near4)[1]


    elif 0<= int(get_fullness_bin(near5)[kind_bin]) < 75:
        text ='I suggest you go '+  get_location_bin(near5)[1]


    elif 0<= int(get_fullness_bin(near6)[kind_bin]) < 75:
        text ='I suggest you go '+  get_location_bin(near6)[1]


    elif 0<= int(get_fullness_bin(near7)[kind_bin]) < 75:
        text ='I suggest you go '+  get_location_bin(near7)[1]


    elif 0<= int(get_fullness_bin(near8)[kind_bin]) < 75:
        text ='I suggest you go '+  get_location_bin(near8)[1]


    else:
        text = 'Sorry,All of this kind of bin is full'


    print(text)
    tts = gTTS(text, lang='en')
    tts.save("suggest.mp3")






#green Y  N ,yellow  Q  W, red  E R
arduinoSerialData = serial.Serial('com8', 9600)  # Create Serial port object called arduinoSerialData
time.sleep(3)
while True:
    if (arduinoSerialData.inWaiting() > 0):
        myData = arduinoSerialData.readline()
        myData = int(myData)
        if myData < 100:
            conn = sqlite3.connect("bin_Fullness.db")
            cursor = conn.cursor()
            sql = 'UPDATE bin_fullness SET \'paper\' = %s WHERE ID = 1' %  myData
            cursor.execute(sql)
            conn.commit()
            cursor.close()
            conn.close()
            print(myData)

        if myData < 10:
            full(1, 2)
        elif myData<30:
            print(myData)
        else:
            print(myData)




