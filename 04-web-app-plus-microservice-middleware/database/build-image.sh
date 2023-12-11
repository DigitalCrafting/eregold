#!/bin/bash

set -e
cd $(dirname $0)

SCHEMA_IMAGE_NAME=digital-crafting/eregold-schema:04-1.0.0

if docker image ls | grep "digital-crafting/eregold-schema.*04-1.0.0" > /dev/null; then
  docker image rmi $SCHEMA_IMAGE_NAME
fi

docker build -t $SCHEMA_IMAGE_NAME --force-rm ./schema

exit 0