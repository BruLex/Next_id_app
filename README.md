# nextid_app
Nextid_app

## Copy project files
- Clone repository:
`$ git clone https://github.com/BruLex/test_vagrant/`

FOR VAGRANT USE:
## Preparations, installing VirtualBox and Vagrant with plugin
- Navigate to project's folder, and do next:
- To install, do:
 `$ sudo ./setup.sh`
## Run
- Navigate to project's folder, and do next:
- Run `$ vagrant up && vagrant ssh`
## Get a result
- To get next_id: `$ nextid`
## Exit 
- To exit: `$ exit`

FOR DOCKER USE:
## Preparations, installing Docker
- To run with Docker
  - [docker](https://docs.docker.com/install/) - version 18.03 at the moment of writing
## Installing nextid_app
- To install, do:
 `$ sudo ./nextid -setup`
- To uninstall, do:
 `$ sudo ./nextid -close`
## Get a result
- To get next_id: `$ nc localhost 9999 <<< next`


