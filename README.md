# Server Wi-Fi Network

Control systems versions: Maven;

Framework: SpringBoot;

WEB: Freemarker, HTML, Bootstrap;

<hr>

The program of inclusion and shutdown of the Internet at the IP address of the device connected to the wifi.

<hr>

API

```
GET /
   Response:
         list network connect all
```
```
GET /?data=url

   Request:
      param name=<name network>
      
   Response:
      list of network connections by specific name 
```

```
Post /

  Body: 
      status= 0 & 1

   Response: ?status=0
      connect network ip
      
  Response: ?status=1
        disconnect network ip  
```

<hr>

To run the program, examine the deploy.sh file.

[deploy.sh](script/deploy.sh)

for settings command
```
    bash deploy.sh -config
```
command to start 
```
    bash deploy.sh -start
```
command to stop 
```
    bash deploy.sh -stop
```

help
```
 HELPER
 Parameters
 
    ./deploy.sh -start       | Start server"
    ./deploy.sh -stop        | Stop server"
    ./deploy.sh -restart     | Restart server"
    ./deploy.sh -status      | Status server"; echo
    ./deploy.sh -package     | Maven package project"
    ./deploy.sh -update/-up  | Re build project"; echo
    ./deploy.sh -config      | Check installed software"
    ./deploy.sh -h           | Help"
    ./deploy.sh -help        | Help"
```
<hr>

![](image/Capture-1.PNG)

![](image/Capture-2.PNG)