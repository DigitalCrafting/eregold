#!/bin/bash

set -e
cd $(dirname $0)

./backend/build-image.sh
./arkenstone/build-image.sh

exit 0