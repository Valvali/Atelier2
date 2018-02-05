#!/bin/bash
cd "Wildfly admin" &&
docker build -t "atelier2/wildfly-admin" . &&
cd ../Postgres &&
docker build -t "atelier2/pgsql" . &&
cd .. &&
echo "images construites, utlisez docker-compose up pour les lancer"
