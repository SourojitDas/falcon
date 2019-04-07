#!/usr/bin/env bash

git pull

gradle jar

export HOSTNAME=$HOSTNAME

sudo docker-compose build

sudo docker-compose down

sudo docker-compose up -d