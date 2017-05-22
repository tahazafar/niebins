##          _One day, all of us will have NICE bins!_ 
![](https://s-media-cache-ak0.pinimg.com/originals/65/a8/58/65a858cc077444705b1112ef71986eb7.png)





## **Vision**


Waste production is constantly increasing in urban areas, leading to waste abandon and untidy neighborhoods. The classification of waste is a priority to create a healthier environment and it helps to reuse and recycle what otherwise remains waste.

***NICE bins*** tackles these issues by making the habit of waste classification easier and fun for residents and by allowing faster notification of waste abandon. Residents receive points according to how often they use classified bins and have access to a crowdsourcing database which shows them the right bins for the items they don’t know how to classify. The more use residents make of classified bins, the closer they are to become **_environmental ambassadors_** and rewarded. Residents receive a vocal suggestion of the nearest available bin if the one they want to use is full, saving their time and preventing waste abandon. Trash collectors and residents notify waste abandon in a faster and easier way to the company using ***NICE bins*** to keep the neighborhood tidy. A map of the bins in the neighborhood is available to show their location and current filling. 

Easier waste classification allows residents to have an active role in reducing waste in their neighborhood and the maintenance of tidy surroundings facilitates the adoption of good habits for the environment.

## AmI main steps

**Sensing** : The system senses the level of trash in the bin as well as the situation of the bins nearby. It also senses registered users’ speech.

**Reasoning** :  The bin calculates the percentage of fullness  of the trash bin and evaluates the situation of the bins nearby. The system understands the user's request (speech recognition) and elaborates the correct answer according to database. The gamification system also rank the users according to the points.

**Acting** :  The system educates the user about the diversified harvest through the dialogue of both (user and bin: e.g. User: "Where can I throw this can?" so bin start to blink green led and emit a sound in the correct bin) .Through the interactive platform which is  a sort of gamification (e.g: if user want to know how much rubbish he has thrown in a given period of time).

**Interacting** : It provides suggestions of the nearest available(unfilled) bins, reminder about collection days for a specific bin.	
	

## AmI features 

**Sensitive** : The system senses the level of trash in the bin. 

**Responsive** : The system, given user's  requests, responds to them with the requested information.

**Adaptive** :  If the garbage bins’ positions are changed, they keep their ability to communicate with other bins.

**Transparent** : Residents don’t need to set up anything when they are throwing their waste and also the system is hidden to them.

**Ubiquitous** : The system can be used in wider areas and is easy to use for users.

## Purpose and scope

***NICE bins*** aims to make the neighborhood cleaner and tidier and to educate people on classification of trash by providing a fun platform with a rewarding system.

The system deals with the following main functions: it shows the situation according to filling level, location and clearness around of all the bins in the neighborhood on the app or website, it receives waste abandon notifications from registered users and trash collectors and it gamifies the use of classified bins; it gives real-time warning and/or suggestions for residents to forbid them to abandon the trash outside and suggest them the closest bins if the current one is not available to be used; it educates people to care more about trash classification by the gamified system which provides also rewards and it uses the crowdsourcing database of recycling information according to the products’ barcode.

This system doesn’t involve the residents out of the neighborhood who haven’t yet registered. Besides, it doesn’t do any trash disposal and recognition or optimize the management of the trash collection company.


## Definitions
### Glossary
* Registered user: people using bins that have an account
* Unregistered user: people using bins that don’t have an account
* Worker: waste collectors
* Password: a specific word chosen by the system that will be used by the registered users
* Green: 0% - 20% level of fullness of the bin
* Yellow: 20% - 60% level of fullness of the bin
* Orange: 60% - 80% level of fullness of the bin
* Red: 80% - 100% level of fullness of the bin

### Actors
**Residents** throwing their trash in this neighborhood and looking for a usable bin (80%)

**Trash collectors** working in this neighborhood and checking the abandon waste in the area (20%)



## Functional Requirements

**Functional Area** | **Description**
:----------- | :------------
1 - USR | User login, registration and logout
2 - WEB & APP | Public web page and app visible by any user (even not registered users) with basic functionalities for unregistered users and advanced ones for registered users
3 - NOTIF | Notifications sent by the system to user devices
4 - PREFS | Interface for setting user preferences
5 - BINTERF | Basic interface provided on the bins to any user


**FR** | **Title** | **Description** | **Priority** | **Hardware**
:---------------- | :---------------- | :----------- | :----------- | :----------- 
1.1 | Web & App interface | Users are provided with map of bins location and filling levels of bins whether they are registered or not. Registered users can access to the gamification for trash classification and to the crowdsourcing database for item classification. | 1 | pc, smartphone with microphones
1.2 | Sign up (with voice)  | For registering/signup users need to choose a nickname and password, and register also your voice (of a specific word) in the system that users say every time they want to throw the rubbish to get points. | 1 | pc, smartphone with microphones
1.3 | Workers login | workers login for waste management (location map and filling levels of the bins) and waste abandoning notification. | 3 | pc, smartphone with microphones
2.1 | Get information for bins | The website and the app show the location map of the bins  and the filling level of the bins in the city.  | 1 | pc, smartphone
2.2 | Know ranking | Website shows to registered users their points and the ranking of all users | 2 | pc, smartphone
2.3 | Send feedback | Registered users can send feedback (e.g. waste abandon) to the system | 3 | pc, smartphone
2.4 | Item classification | The website and the app are integrated with a crowdsourcing database for item classification according to its bar code. The user can scan the items’ bar code he/she doesn't know how to classify to access the database.  | 3 | pc, smartphone
3.1 | Notify the levels | The system notifies the users if they reach a new level and get rewards to encourage them to continue. | 3 | pc, smartphone
3.2 | Notify the dates to userss | The system notifies the user about waste collection dates to engage her/him in the waste collection game. | 5 | pc, smartphone
3.3 | Notify the situation to collectors | The system notifies the collectors about filling levels of  bins and abandoned trash reportings. | 3 | pc, smartphone
4.1 | Notifications control | Users can set if the notification mute or not, shown on the main interface or not | 5 | pc, smartphone
4.2 | Social Networks sharing | Users can decide to share or not on their social networks the new levels, rewards they get, etc. | 4 | pc, smartphone
5.1 | Alarm for waste abandon | The bins alarm users if they sense that  there is abandoned waste near the bins. | 1 | breadboard, speaker, LED
5.2 | Suggest other bins | The bins suggest users the next closest bins of the same type if they are full themselves or they sense abandoned waste. | 1 | raspberry, speaker, LED
5.3 | Sense exterior situation | The bins  sense if there is trash left near them.  | 1 | raspberry, infrared sensors outside the bin
5.4 | Sense interior situation | The bins sense the filling level inside them according to space | 3 | Raspberry, infrared sensor inside the bin, at the top of it
5.5 | Show filling levels | The bins show their filling level. | 3 | Raspberry, LED
5.6 | Speech recognition | The bins recognize the password to give registered users the points. | 1 |Raspberry, microphone / human voice sensor 

## Non Functional Requirements

**#** | **Category** | **Area** | **Description**
:----------- | :------------ | :----------- | --------------
1 | Product | Efficiency | **_NICE bins_** is able to reply in real time and it works in a neighborhood range. The system occupies little space in the bin which it is integrated to
2 | Product | Reliability | The crowdsourcing database may lead to some wrong information for classification that cannot be checked instantaneously
3 | Product |Portability | **_NICE bins_** is available on smartphones (Android), tablets and PCs
4 | Organizational |Implementation | Every bin in the neighborhood has to be equipped with **_NICE bins_** system. Every resident has to receive his own code for voice recognition and register to **_NICE bins_** system
5 | External |Interoperability | Using **_NICE bins_** system trash collectors and registered users notify abandoned waste not reachable by the sensor of the bins to the company which takes care of its collection. **_NICE bins_** app uses the map of the bins’ location provided by waste management company and the crowdsourcing database for classification of trash
6 | External |Legislative | In order to guarantee users’ privacy, **_NICE bins_** provides them with a code for voice recognition related only to their residence and their e-mails for registration


## System Architecture

![](https://s-media-cache-ak0.pinimg.com/originals/67/70/ff/6770ff40a9bfe01fe99ec6f7d7ac4a1b.png)

### Hardware Architecture

**Computational nodes:**
* “central” - server on a remote computer: it manages the 5 bins by directly communicating with the main bin (generic one) through the bin interface, it manages the web and app interfaces and it retrieves information from the crowdsourced database. It receives information from the bin sensors
* “distributed” - raspberry placed in the main bin (generic one) type: RaspBerry Pi, function: compute the information it receives (filling level sensors from the main bin and the other bins, movement sensors around the bins, voice sensor for interaction with the user) and communication with central server location: on the main bin,  and arduinos placed in the 5 bins (paper, plastic, glass and can, organic, generic) function: control sensors of movement, sensor for filling level, voice sensor, voice actuator, led lights), location: in the 5 bins
* “mobile” - no need

**Devices:**
* 4 movement sensors in each bin - sense abandoned trash
* 1 ultrasound sensor - in each bin - sense filling level
* 1 voice sensor - in generic main bin - sense human voice (no aliens allowed  heheheh)
* 1 speaker - other bins - sound emission
* 1 voice actuator - in each bin - give user suggestions of other bins location and classification
* led light - in each bin - according to color it gives suggestions to the user about filling level and classification
* 1 accelerometer - in each bin - to understand if the bin is open or closed

**UID:**
* bin interface 
*  & app interface: computer smartphone, function: interact with the user and support gamification

### Software Architecture

**Function 1**: If the bin is full ,give suggestion to the nearest unfilled bin.


**Software** | **Purpose** | **Location**
:------------ | :------------- | :------------- 
Database | we give every bin an ID,and use database for recording which  bin is more near to this bin;Another database for recording full% of every bin and location of every bin. | central
Text To Speech | changing location text to voice | central
LED controller | Show full% of every bin with different colors | bin

**Function 2**: Get game points if you throw rubbish into sorted rubbish bin.

**Software** | **Purpose** | **Location**
:----------- | :------------ | :-----------
Database | Use database for recording every user ID and their voice code,game points location and other related information. | central
Voice recognition | change the voice to text for user login  and get user's information | bin
Instapush |  send game points and waste collection dates to user and  is connected to the central database | Smartphone

**Function 3**: On Google Map it shows the full% and location of every bin.
 
**Software** | **Purpose** | **Location**
:------------ | :------------- | :------------- 
Data visualization | the residents and  garbage collectors can see  full% and location of every bin on map,and garbage collectors can adjust the number and location of bins according to statistics. | central
 
### Network Architecture
* Central server: world-accessible public IP address
* Database : connected to center server by gateway
* Website connected to center server by local gateway
* Master  bin of each group of bins: using SIM card  to connect to google APIs by 3G network.
* Wireless sensors network (e.g., Z-Wave), connected to SIM 3G network also.
* Phones connected to local wi-fi or to 3G network . Connects to central server, only.
* Slave bins and Master bin of each group are connected by wires.

## Selected components
### Hardware Components
**OTS** | **AD-HOC** 
:------------ | :------------- 
1 Raspberry Pi |  sensor for filling level?? 1 ultrasound sensor
5 movement sensors | photovoltaic panels  for powering the system??
5 LEDs | roll ball switcher to understand if the bin is open
5 arduino boards / Breadboards | 
5 USB Microphones for Raspberry Pi | 
10 - 20 proximity sensors for Raspberry Pi (it seems quite a lot, maybe for demo a bit less) | 
1 USB power bank ?? | 

### Software Components
For programing we make use of Python,and java(mostly for android application):
* 1.Smartbin uses MYSQL injection Database .
* 2.For data visualization we use Google Map JavaScript API connect to database 
* 3.For text to speech we use pyttsx API to change text to voice.
* 4.For  voice  recognition we make use of google assistant library for python.
* 5.furthermore we make use of gsm technology to connect smartbins to main server.
* 6.For LED control we make use of PWM API.
* 7.To check more about modules you can visit the respective websites.



### Open issues

* ~~**Communication** between bins~~
* The **scalability** of the system is expensive to cover the whole city   
* ~~More knowledge about the economical technology to deal with the compression of garbage and sense of amount and classification of garbage~~
* ~~To have a demonstration, we will need to have a real bin, the big one, that **cannot fit the LADISPE’s tables’ size**~~
* ***new*** The way to provide **rewards** to the users (sponser?)
* ***new*** NICE bins uses voice recognition and voice notification so it’s not yet **convenient for disabled people** who cannot speak and/or hear
* ***new*** The situation of waste abandon could be a problem if we think about these situations:
1.cars parkings
2.changing locations of the bins
3.sensors which are efficient and can differentiate between waste and other nearby objects
* ***new*** Suggestions on the connection way of master bins to center server and between bins.
* ***new*** We want to use crowdsourcing database of recycling information related to barcode of items, is it more suitable to make it available on mobile phone or bin interface?

		
## Group members

**ID (matricola)** | **Last Name** | **First Name** | **e-mail** | **GitHub** |	**Role in the Project**
------------ | ------------- | ------------- | ------------ | ------------ | ------------
191555 | Seijas Portocarrero | Xileny |	xileny.seijasp@gmail.com	| @Xileny | Hardware Developer & Graphic Designer 
217946 | Ma | Qiang | ma2214889041@gmail.com | @ma2214889041  | Software Developer & Designer        
206376 | Medina	| Francesco | pacimedina@gmail.com | @francescomedina	| Hardware and Software Developer 
217600 | Chen | Yun | 137082773@qq.com | @cystephanie0727 | Software Developer & Web Designer	
219119 | Zafar | Taha | tahazafar17@gmail.com | @tahazafar | Database Manager
		
[https://ami-2017.github.io/NICE-bins](https://ami-2017.github.io/NICE-bins)
