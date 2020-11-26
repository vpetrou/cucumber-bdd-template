#!/bin/bash

ENVIRONMENT=prefat
BROWSER=chrome
DRIVER_REMOTE_URL=
TIMEOUT=15
HEADLESS_MODE=false

# DEFAULT OPTION: Full Execution
if [ "$1" == "" ]; then
	echo "Execution of ALL Tests in $ENVIRONMENT with $BROWSER"
	cd ../../../
	mvn clean verify -Denvironment=$ENVIRONMENT -Dbrowser=$BROWSER -Ddriver.remote.url=$DRIVER_REMOTE_URL -Dtimeout=$TIMEOUT -Dheadless.mode=$HEADLESS_MODE
  exit
fi

# ADDITIONAL OPTION: Partial Execution to run specific tests based on tag (1st argument)
# for example:
#      ./run-tests.sh TC-UI-CON-001
# to execute only scenario with tag TC-UI-CON-001
#
if [ "$1" != "" ]; then
	echo "Execution of Test: $1 in $ENVIRONMENT with $BROWSER"
	cd ../../../
	mvn clean install -Dcucumber.options="--tags @$1" -Denvironment=$ENVIRONMENT -Dbrowser=$BROWSER -Ddriver.remote.url=$DRIVER_REMOTE_URL -Dtimeout=$TIMEOUT -Dheadless.mode=$HEADLESS_MODE
	exit
fi
