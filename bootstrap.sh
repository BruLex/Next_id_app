#!/bin/bash

error_message() {
	echo "Something go wrong"
	echo "Process interrupted"
	echo "Please try again later, if the problem persists, report the developer"
	exit 1
}

#install java
yum -y install java-devel || error_message

#install nc
yum -y install nc || error_message

#create and init alias for app
echo 'alias nextid="nc localhost 9999 <<< \"next\""' >> ./.bashrc || error_message
source ./.bashrc || error_message


