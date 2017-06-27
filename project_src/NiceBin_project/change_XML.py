import xml.etree.ElementTree as ET,time,sqlite3


while True:             #just e.g.,
    conn = sqlite3.connect("bin_Fullness.db")
    cursor = conn.cursor()

    sql = 'select * from bin_Fullness where id = 1'
    cursor.execute(sql)
    values = cursor.fetchall()
    cursor.close()
    conn.close()
    fullness = int(values[0][2])          #更改参数

    if fullness < 10:
        mark =  'full'
    elif fullness < 30:
        mark = 'not_full'
    else:
        mark = 'emtey'


    updateTree = ET.parse("static/markers.xml")
    root = updateTree.getroot()

    #修改sub1的name属性
    sub2 = root.find("marker")
    sub2.set("type",mark)
    updateTree.write("static/markers.xml")  ## 此处应该更改，对应17 行
    time.sleep(5)


