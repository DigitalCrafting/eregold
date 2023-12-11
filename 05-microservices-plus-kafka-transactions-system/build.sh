#!/bin/bash

set -e
cd $(dirname $0)

./arkenstone/build.sh
./backend/build.sh