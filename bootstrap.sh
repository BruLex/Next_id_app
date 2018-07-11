#!/bin/bash

error_message() {
	echo -e "\n\nSomething go wrong"
	echo "Process interrupted"
	echo "Please try again later, if the problem persists, report the developer"
	exit 1
}

#install java
yum -y install java-devel || error_message

#install wget
yum -y install wget || error_message

#install unzip
yum -y install unzip || error_message

#install nc
yum -y install nc || error_message

#install gradle and init gradle wrapper
cd /tmp && wget https://services.gradle.org/distributions/gradle-4.8.1-bin.zip
mkdir -p /opt/gradle
unzip -d /opt/gradle /tmp/gradle-4.8.1-bin.zip
cd /vagrant/ && /opt/gradle/gradle-4.8.1/bin/gradle wrapper

#create and init alias for app
echo 'alias nextid="nc localhost 9999 <<< \"next\""' >> ./.bashrc || error_message
source ./.bashrc || error_message


