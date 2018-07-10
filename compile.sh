#!/usr/bin/env bash
sudo kill -9 `pidof java`
cd /vagrant && ./gradlew build &&  ./gradlew installDist