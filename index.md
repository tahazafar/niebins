##          _One day, all of us will have NICE bins!_ 
![](https://s-media-cache-ak0.pinimg.com/originals/65/a8/58/65a858cc077444705b1112ef71986eb7.png)





## **Vision**

Waste production is constantly increasing in urban areas, leading to waste abandon and untidy neighborhoods. The classification of waste is a priority to create a healthier environment and it helps to reuse and recycle what otherwise remains just waste.

**NICE bins** tackles these issues by making the habit of waste classification easier and fun for residents and by allowing faster notification of waste abandon for trash collectors. Residents receive points according to how often they use classified bins and have access to a crowdsourcing database which shows them the right bin for the items they don’t know how to classify. The more use residents make of classified bins, the more they are recognized as **_environmental ambassadors_** and rewarded. Residents receive a vocal suggestion of the nearest available bin if the one they want to use is full, saving their time and preventing waste abandon. Trash collectors notify waste abandon in a faster and easier way using **NICE bins** to keep the neighborhood tidy. A map of the bins in the neighborhood is available to show their location and current filling. 

Easier waste classification allows residents to have an active role in reducing waste in their neighborhood and the maintenance of tidy surroundings facilitates the adoption of good habits for the environment.

## AmI main steps

**Sensing** : The system senses the level of trash in the bin as well as the situation of the bins nearby.

**Reasoning** :  It calculates the percentage of fullness of the trash bin and evaluates the situation of the bins nearby.

**Acting** :  The system closes the bins and compresses the trash inside them according to the levels of fullness.

**Interacting** : It provides the suggested bins to be opened or closed, the map of the bins in the city and suggestions of the nearest available bins.	

## AmI features 
**Sensitive** : The system senses the level of trash in the bin.

**Responsive** : The system understands when is a good level to compress the waste and also given users’ requests, responds to them with the requested information.

**Adaptive** :  If the garbage bins’ positions are changed, they keep their ability to communicate with other bins.

**Transparent** : Residents don’t need to set up anything when they are throwing their waste and also the system is hidden to them.

**Ubiquitous** : The system can be use in wider areas and their situation is available to be consulted by every user.

**Intelligent** : /

## Glossary

* Registered users: people using bins that have an account
* Unregistered users: people using bins that don’t have an account
* Workers: waste collectors
* Passwords: a specific word chosen by the system that will be used by the registered users
* Green: 0% - 20% level of fullness of the bin
* Yellow: 20% - 60% level of fullness of the bin
* Orange: 60% - 80% level of fullness of the bin
* Red: 80% - 100% level of fullness of the bin

## Functional Requirements

**Functional Area** | **Description**
:----------- | :------------
1 - USR | User login, registration and logout
2 - WEB | Public web pages visible by any user (even not registered users)
3 - NOTIF | Notifications sent by the system to user devices
4 - PREFS | Interface for setting user preferences

**FR** | **Title** | **Description** | **Priority**
:----------: | :----------------: | :----------- | :-----------:
1.1 | Basic interface | General users don't need to log in and are provided with a basic interface | 1
1.2 | Sign up (w/ voice)  | For registration/sign-up users have to choose a nickname and password and later to use the bins they have to register their voice in the system that will send then an email with a specific word to say every time they want to throw the rubbish to get points | 2
1.3 | Workers login | Workers login for waste management and waste abandon notification | 1
2.1 | Get information for bins | The website can be reached by anyone willing to get the location map and the free space percentage of the bins in the city | 1
2.2 | Know ranking | Website shows to registered users their points and the ranking of all users | 2
2.3 | Send feedback | Registered users can send feedback (e.g. waste abandon) to the system | 1
3.1 | Notify the levels | The system will notify the users if they reach a new level and get rewards to encourage them to continue | 2
3.2 | Notify the fullness | Notifications to the collectors about filled bins and abandon trash | 1
3.3 | Remind to throw trash | Alarm reminder for throwing waste | 2
4.1 | Colors and fonts preferences | Users can edit the colors and fonts preferences for the interface | 2
4.2 | Notifications control | Users can set if the notification mute or not, shown on the main interface or not | 1
4.3 | Social Networks sharing | Users can decide to share or not on their social networks the new levels, rewards they get, etc. | 2
4.4 | Sound and vibration control | Users can set and update the voice information to make it more precise | 1


## Non Functional Requirements

**#** | **Category** | **Area** | **Description**
:-----------: | :------------: | :-----------: | --------------
1 | Product | Efficiency | **_NICE bins_** is able to reply in real time and it works in a neighborhood range. The system occupies little space in the bin which it is integrated to
2 | Product | Reliability | The crowdsourcing database may lead to some wrong information for classification that cannot be checked instantaneously
3 | Product |Portability | **_NICE bins_** is available on smartphones (Android), tablets and PCs
4 | Organizational |Implementation | Every bin in the neighborhood has to be equipped with **_NICE bins_** system. Every resident has to receive his own code for voice recognition and register to **_NICE bins_** system
5 | External |Interoperability | Using **_NICE bins_** app trash collector notifies abandon waste not reachable by the sensor of the bins and the company receives the notification and takes care of its collection. **_NICE bins_** app uses the map of the bins’ location provided by waste management company and the crowdsourcing database for classification of trash
6 | External |Legislative | In order to guarantee users’ privacy, **_NICE bins_** provides them with a code for voice recognition related only to their residence and their e-mails for registration

## Group members

**ID (matricola)** | **Last Name** | **First Name** | **e-mail** | **GitHub** |	**Role in the Project**
------------ | ------------- | ------------- | ------------ | ------------ | ------------
191555 | Seijas Portocarrero | Xileny |	xileny.seijasp@gmail.com	| @Xileny | Hardware Developer & Graphic Designer 
217946 | Ma | Qiang | ma2214889041@gmail.com | @ma2214889041  | Software Developer & Designer        
206376 | Medina	| Francesco | pacimedina@gmail.com | @francescomedina	| Hardware and Software Developer 
217600 | Chen | Yun | 137082773@qq.com | @cystephanie0727 | Software Developer & Web Designer	

### Open issues

* ~~**Communication** between bins~~
* The **scalability** of the system is expensive to cover the whole city   
* ~~More knowledge about the economical technology to deal with the compression of garbage and sense of amount and classification of garbage~~
* To have a demonstration, we will need to have a real bin, the big one, that **cannot fit the LADISPE’s tables’ size**
* ***new*** The way to provide **rewards** to the users 
* ***new*** NICE bins uses voice recognition and voice notification so it’s not yet **convenient for disabled people** who cannot speak and/or hear
		
[https://ami-2017.github.io/NICE-bins](https://ami-2017.github.io/NICE-bins)
