#!/bin/bash

set -e
cd $(dirname $0)

REACT_IMAGE_NAME=digital-crafting/eregold-ui-react:1.0.0

if docker image ls | grep "digital-crafting/eregold-ui-react.*1.0.0" > /dev/null; then
  docker image rmi $REACT_IMAGE_NAME
fi

docker build -t $REACT_IMAGE_NAME --force-rm .

exit 0