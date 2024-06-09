#!/bin/bash

# Check if the correct number of arguments is provided
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 [c|r] [6-22]"
    exit 1
fi

# Assign the arguments to variables
ACTION=$1
VERSION=$2

# Check if the version is within the valid range
if ! [[ "$VERSION" =~ ^(6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22)$ ]]; then
    echo "Invalid version: $VERSION. Must be between 6 and 22."
    exit 1
fi

# Define the submodule URL
SUBMODULE_URL="https://github.com/openjdk/jdk$VERSION"
SUBMODULE_PATH="jdk$VERSION"

# Perform the action based on the first argument
case $ACTION in
    c)
        git submodule add $SUBMODULE_URL $SUBMODULE_PATH
        ;;
    r)
        git submodule deinit -f $SUBMODULE_PATH
        rm -rf $SUBMODULE_PATH
        git rm -f $SUBMODULE_PATH
        # Remove the corresponding section from .gitmodules
        if [ -f .gitmodules ]; then
            sed -i "/\[submodule \"$SUBMODULE_PATH\"\]/,/^$/d" .gitmodules
        fi
        ;;
    *)
        echo "Invalid action: $ACTION. Use 'c' to create or 'r' to remove."
        exit 1
        ;;
esac

echo "Action $ACTION on JDK version $VERSION completed."