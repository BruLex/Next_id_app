#!/bin/bash

setup() {
        sudo mkdir ./data -m 777  2>/dev/null
        sudo touch ./data/database.db || error_message
        sudo chmod 777 ./data/database.db || error_message
        sudo docker build -t nextid . || error_message
        sudo docker swarm init  || error_message
        sudo docker stack deploy -c docker-compose.yml nextid  || error_message
        echo -e "\n\nOK, done."
        echo "To take nextid, do:"
        echo "	 nc localhost 9999 <<< next"
}

close() {
        sudo docker stack rm nextid 2>/dev/null
        sudo docker swarm leave -f 2>/dev/null
}

error_message(){
        close
        echo "Something go wrong"
        echo "Process interrupted"
	echo "Please try again later, if the problem persists, report the developer"
        exit 1
}

help_info() {
        echo "Usage: ./nextid COMMAND"
        echo "Options:"
        echo "	-setup	build and start nextid_app"
        echo "	-close	close nextid_app"
}

if [ "$1" != " " ]; then
        case $1 in
                -setup ) setup
                exit
                ;;
                -close ) close
                exit
                ;;
                * ) help_info
                exit 1
                esac
fi

