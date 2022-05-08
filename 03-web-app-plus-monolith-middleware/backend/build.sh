#!/bin/bash

# Set the working directory to where the script is
cd $(dirname $0)

mvn clean install -Pprod
