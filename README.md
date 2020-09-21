##################################################### 
##                      AVSS                       ##
##       Advanced Vehicular Security System        ##
#####################################################

Developed in 2017 as a Senior Project, to satisfy senior graduation requirments at the American University of Science and Technology, in Beirut, Lebanon.

The project was created to satisfy the need for a technologically advanced, secure, and effective vehicular theft countermeasure, especially during the time were crime rates have been increasing and simultaneously the technological sector in Lebanon has been flourishing as well. 

It was also a way for me and my team to experiment with IOT and its subsequent environment, since we have interest in the topic and have attended a couple of seminars about IOT,
so we took the chance to gain experience in IOT uneder the academic liberty provided by our University.

The core idea of the project was to create a module comprised of a Raspberry Pi, fingerprint scanner, camera, and mobile application. The physical module would have been installed in the User's car via OBD connector.
The mobile application installed on the User's mobile device (Developed for android devices at for experimenting purposes).

The system would work in the following:

The Module in the car:
  The module would become the focal point in the car's ignition system, completing the ignition circuit once it has verified the correct owner of the vehicle.
  The module is connected to the system through an infrastructure that runs MQTT, an IOT communication protocol.
  The fingerprint scanner would be the main point of user identification.
  The camera would have been one aspect of the anti-theft mechanism, its purpose was to take a picture of any unauthorized users attempting to start the vehicle.
  
The Application:
  The application installed on the phone would provide the owner control over the installed system.
  The application would recieve notifications in case the system detects unlawful access and ignition attempts to the car.
  The application would display to the user the picture taken by the camera installed in the car.
  The application would allow the user to even control the ignition of the car on command.
  
 Connectivity:
  The module would have a SIM card from a telecome provider, to establish conectivity to the infrastructure.
  
 Failovers:
  The module installed inside the vehicle would have been connected directly to the car's battery, through an intricate wiring blueprint, it would have been difficult for any    car thief to invest the time and effort into dislocating the module and the wiring.
  The module had a backup battery installed, with a lifespan of an estimated 500 hours, since MQTT is very lightweight, it did not require high power consumption to keep the module activated and communicating with the system.
  The module's MQTT protocol is also by default programmed to send a distress signal with the last know location and car status, in the event the module lost power.
  
  
  CREDITS TO THE TEAM MEMBERS OF AVSS:
  Rawad Zahreddine
  Ahmad Hariri
  Mahmoud Sabbagh
  
  This system was researched , developed, tested and demo'd by the above mentioned members.
  
  completed and submitted in May, 2017.
 The codes attached to this repository were developed to run on Raspbian OS and using python 2.7.
 The application codes attached are android based.
 The database used was MySQL
 The demo was conducted over LAN, hence all IPs mentioned in the codes are local network IPs.
 
