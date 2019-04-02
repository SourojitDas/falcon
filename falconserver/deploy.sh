#!/usr/bin/env bash

./gradle jar

sudo docker-compose build

sudo docker-compose down

sudo docker-compose up -d