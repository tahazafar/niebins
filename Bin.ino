#define buttonPin 2
#define RedLED 5
#define YellowLED 6
#define GreenLED 7
#define trig_pin 8
#define echo_pin 9
#define pirSensor 10
#define buzzer 12

#include "Ultrasonic.h" //Library for HC-SR04 module
#include <Console.h>
#include <Process.h> // Run Linux processes on the AR9331 - wifi
Ultrasonic ultrasonic(trig_pin, echo_pin);

int i;
int distance;
int val = 0;
int filling_percentage;
int buttonState = 0;
int openState = LOW; 
int beeper = 0;

void setup() {
  // put your setup code here, to run once:  
  Bridge.begin();
  Console.begin();

  while(!Console){
    ;
  }
  //Serial.begin(9600); 
   
  pinMode(GreenLED, OUTPUT);
  pinMode(YellowLED, OUTPUT);
  pinMode(RedLED, OUTPUT);
  pinMode(buzzer, OUTPUT);
  pinMode(pirSensor, INPUT);
  pinMode(buttonPin, INPUT);

  digitalWrite(GreenLED, LOW);
  digitalWrite(YellowLED, LOW);
  digitalWrite(RedLED, LOW);
  digitalWrite(buzzer, LOW);
  
}

void loop() {
  // put your main code here, to run repeatedly:
  int sensorValue = digitalRead(pirSensor);
  //Serial.println("Sensor Value: ");
  //Serial.println(sensorValue); 
  if(sensorValue == LOW){
    SetLEDLights(-1);
  }
  else{
    int buttonState = digitalRead(buttonPin);
    //Serial.println("Button Pin: ");
    Serial.println(buttonState);
    if (buttonState == HIGH){
      SetLEDLights(-1);
      CheckOpening(buttonState);
    }
    else{
      CheckOpening(buttonState);
      distance = ultrasonic.Ranging(CM); //Measure the distance in cm;
      while(distance < 0){ //Avoid false readings, measure the distance again
        distance = ultrasonic.Ranging(CM);
      }
      filling_percentage = (33 - distance) * 100 / 32;
      SetLEDLights(filling_percentage);
      //Serial.println("Distance and filling percentage: ");
      //Serial.println(distance);
      //Serial.println(filling_percentage);
      if(buttonState == LOW){
        //Serial.println(filling_percentage);
        if (Console.available() > 0){
          Console.write(filling_percentage);
        }
      }
      //if (Console.read(beeper) == 1) {
      //BuzzerSound();
      //}
    }
  }
  delay(1000);
}

void SetLEDLights(int filling){ 
  if (filling == -1){
    digitalWrite(GreenLED, LOW);
    digitalWrite(YellowLED, LOW);
    digitalWrite(RedLED, LOW);
  }  
  if (filling >= 0 && filling <= 50){
    digitalWrite(GreenLED, HIGH);
    digitalWrite(YellowLED, LOW);
    digitalWrite(RedLED, LOW);
  }
  if (filling > 50 && filling <= 75){
    digitalWrite(GreenLED, LOW);
    digitalWrite(YellowLED, HIGH);
    digitalWrite(RedLED, LOW);
  }
  if (filling > 75){
    digitalWrite(GreenLED, LOW);
    digitalWrite(YellowLED, LOW);
    digitalWrite(RedLED, HIGH);
  }    
}

void CheckOpening(int buttonState){
  int userPoints;
  if (buttonState == HIGH) {
    if(openState == LOW){
      openState = HIGH;
    }    
  } else {
    if (openState == HIGH){
      openState = LOW; 
      userPoints = -1;
      Serial.println(userPoints);
      /*if (Console.available() > 0){
        Console.write(userPoints);
      }*/
    }    
  }
  return;              
}

void BuzzerSound(){
  unsigned char i;
  while(1){
    for(i=0; i<80; i++){
      digitalWrite(buzzer, HIGH);
      delay(200);
      digitalWrite(buzzer, LOW);
      delay(200);
    }
  }
}

