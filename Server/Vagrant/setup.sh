#!/bin/bash

error_message() {
        echo "Something go wrong"
        echo "Process interrupted"
	echo "Please try again later, if the problem persists, report the developer"
        exit 1
}

sudo mkdir ./data -m 777 2> /dev/null
sudo touch ./data/database.db || error_message
sudo chmod 777 ./data/database.db || error_message
sudo apt-get update || error_message
sudo apt-get install -y virtualbox || error_message 
cd /tmp/ && wget https://releases.hashicorp.com/vagrant/2.1.2/vagrant_2.1.2_x86_64.deb || error_message 
sudo dpkg -i /tmp/vagrant_2.1.2_x86_64.deb || error_message
sudo rm -f /tmp/vagrant_2.1.2_x86_64.deb 2> /dev/null
vagrant plugin install vagrant-vbguest || error_message

