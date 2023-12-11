#!/bin/bash

set -e
cd $(dirname $0)

ACCOUNTS_IMAGE_NAME=digital-crafting/arkenstone-accounts:04-1.0.0
GATEWAY_IMAGE_NAME=digital-crafting/arkenstone-gateway:04-1.0.0
CUSTOMERS_IMAGE_NAME=digital-crafting/arkenstone-customers:04-1.0.0
TRANSACTIONS_IMAGE_NAME=digital-crafting/arkenstone-transactions:04-1.0.0

if docker image ls | grep "digital-crafting/arkenstone-accounts.*04-1.0.0" > /dev/null; then
  docker image rmi $ACCOUNTS_IMAGE_NAME
fi
if docker image ls | grep "digital-crafting/arkenstone-gateway.*04-1.0.0" > /dev/null; then
  docker image rmi $GATEWAY_IMAGE_NAME
fi
if docker image ls | grep "digital-crafting/arkenstone-customers.*04-1.0.0" > /dev/null; then
  docker image rmi $CUSTOMERS_IMAGE_NAME
fi
if docker image ls | grep "digital-crafting/arkenstone-transactions.*04-1.0.0" > /dev/null; then
  docker image rmi $TRANSACTIONS_IMAGE_NAME
fi

docker build -t $ACCOUNTS_IMAGE_NAME --force-rm ./accounts
docker build -t $GATEWAY_IMAGE_NAME --force-rm ./api-gateway
docker build -t $CUSTOMERS_IMAGE_NAME --force-rm ./customers
docker build -t $TRANSACTIONS_IMAGE_NAME --force-rm ./transactions

exit 0