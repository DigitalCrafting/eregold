#!/bin/bash

# Set the working directory to where the script is
set -e
cd $(dirname $0)

./accounts/build.sh
./api-gateway/build.sh
./customers/build.sh
./transactions/build.sh
./transaction-verification/build.sh
