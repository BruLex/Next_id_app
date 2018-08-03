
# Next id application
This application for testing Server-Client socket.
> Prehistory: 
> User need to get unique ID at any time when enter 'next_id' or simple 'next'.

# Project consist of several parts:
- next-id-client (client console application via Java and C++)
- next-id-client (client GUI application via Android)
- next-id-server (server application via Java and C++)
# You can also:
  - Run server application via Java with Docker by moving the contents from  'Docker' folder into 'Server via JAVA' folder and start script 
    - To start server ```$ ./next_id.sh -setup```
    - To stop server ```$ ./next_id.sh -close```
  - Run server application via Java with Vagrant by moving the contents from  'Vagrant' folder into 'Server via JAVA' folder and start script setup.sh```$ ./setup.sh``` which instal Vagrant and some plugin for them 
    - To start server ```$ vagrant up```
    - To stop server ```$ vagrant halt```