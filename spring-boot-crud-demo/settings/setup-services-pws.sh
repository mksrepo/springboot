#!/usr/bin/env bash
cf s

cf cs cleardb spark mysql
cf cs p-config-server standard config -c config-server-setup.json

# cf cs p-service-registry standard registry
# cf cs cloudamqp lemur rabbit
# cf cs p-circuit-breaker-dashboard standard breaker

cf s
