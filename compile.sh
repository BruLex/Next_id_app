#!/usr/bin/env bash

error_message() {
	echo "Something go wrong"
	echo "Process interrupted"
	echo "Please try again later, if the problem persists, report the developer"
	exit 1
}

sudo kill -9 `pidof java` 2>/dev/null

cd /vagrant && ./gradlew build &&  ./gradlew installDist || error_message
