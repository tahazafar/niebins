from flask import Flask
from flask_ask import Ask, statement, convert_errors
import requests
from subprocess import call
import json



app = Flask(__name__)
ask = Ask(app, '/')

@ask.intent('scan')
def scan():
    #photo = 'photo_{timestamp}.jpg'.format(timestamp=datetime.datetime.now().isoformat())
    #text =  'scan_{timestamp}.txt'.format(timestamp=datetime.datetime.now().isoformat())
    f = open("/home/pi/Desktop/scan.txt", "w")
    call("/usr/bin/raspistill -o /home/pi/Desktop/photo.jpg", shell=True)
    call("/usr/bin/zbarimg /home/pi/Desktop/photo.jpg  >  /home/pi/Desktop/scan.txt", shell=True)
    return statement("Tutto ok")

if __name__ == '__main__':

	app.run(host="0.0.0.0",port=3000)

