#!/bin/bash

mvn clean install

rm -r app
rm -r db

docker-compose down && docker-compose up -d --build

docker cp ./initialiseDatabase.sql online-book-store-db-1:/

sleep 10
docker exec online-book-store-db-1 psql -U postgres -d online-book-store < initialiseDatabase.sql