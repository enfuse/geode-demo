#!/usr/bin/env bash

# TODO: Find a better way to wait until all mongo instances are up
sleep 12

mongo --host mongodb-primary:27017 <<EOF
   var config = {
        "_id": "rs0",
        "version": 1,
        "members": [
            {
                "_id": 0,
                "host": "mongodb-primary:27017"
            },
            {
                "_id": 1,
                "host": "mongodb-secondary:27017"
            },
            {
                "_id": 2,
                "host": "mongodb-arbiter:27017"
            }
        ]
    };
    rs.initiate(config);
EOF
