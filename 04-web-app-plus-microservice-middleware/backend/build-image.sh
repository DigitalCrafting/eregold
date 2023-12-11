#!/bin/bash

set -e
cd $(dirname $0)

BE_IMAGE_NAME=digital-crafting/eregold-be:04-1.0.0

if docker image ls | grep "digital-crafting/eregold-be.*04-1.0.0" > /dev/null; then
  docker image rmi $BE_IMAGE_NAME
fi

docker build -t $BE_IMAGE_NAME --force-rm .

exit 0