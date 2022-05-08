#!/bin/bash

./database/accounts/migrate_db.sh
./database/customers/migrate_db.sh
./database/transactions/migrate_db.sh
./database/users/migrate_db.sh