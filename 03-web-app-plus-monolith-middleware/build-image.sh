#!/bin/bash

set -e

BE_IMAGE_NAME=digital-crafting/eregold-be:03-1.0.0
MW_IMAGE_NAME=digital-crafting/arkenstone-mw:03-1.0.0

#if docker image ls | grep "digital-crafting/eregold-be.*03-1.0.0" > /dev/null; then
#  docker image rmi $BE_IMAGE_NAME
#fi

#if docker image ls | grep "digital-crafting/arkenstone-mw.*03-1.0.0" > /dev/null; then
#  docker image rmi $MW_IMAGE_NAME
#fi

docker build -t $BE_IMAGE_NAME --force-rm ./backend
docker build -t $MW_IMAGE_NAME --force-rm ./middleware

exit 0