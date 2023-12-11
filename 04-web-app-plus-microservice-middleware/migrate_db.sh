#!/bin/bash

set -e
cd $(dirname $0)

./database/schema/accounts/migrate_db.sh
./database/schema/customers/migrate_db.sh
./database/schema/transactions/migrate_db.sh
./database/schema/users/migrate_db.sh