#!/bin/bash

cd $(dirname $0)

./middleware/build.sh
./backend/build.sh