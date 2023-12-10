#!/bin/bash

set -e

IMAGE_NAME=digital-crafting/eregold-be:01-1.0.0

if docker image ls | grep "digital-crafting/eregold-be.*01-1.0.0" > /dev/null; then
  docker image rmi $IMAGE_NAME
fi

docker build -t $IMAGE_NAME --force-rm ./backend

exit 0