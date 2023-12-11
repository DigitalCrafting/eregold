#!/bin/bash

set -e
cd $(dirname $0)

ANGULAR_IMAGE_NAME=digital-crafting/eregold-ui-angular:1.0.0

if docker image ls | grep "digital-crafting/eregold-ui-angular.*1.0.0" > /dev/null; then
  docker image rmi $ANGULAR_IMAGE_NAME
fi

docker build -t $ANGULAR_IMAGE_NAME --force-rm .

exit 0