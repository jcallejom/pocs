#!/bin/bash

set -e

BASEDIR=$(dirname "$0")

initEnvironment(){
  printf "\nStarting environment...\n"
  docker compose -f "$BASEDIR"/docker/docker-compose.yml up -d
}

registerConnector(){
  printf "\nRegistering Debezium connector Oracle...\n"
  sleep 20
  curl -s -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d @"$BASEDIR"/debezium/postgres-connector.json > /dev/null 
}

initEnvironment
registerConnector