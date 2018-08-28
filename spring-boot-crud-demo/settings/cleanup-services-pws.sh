#!/usr/bin/env bash
cf s

cf ds -f mysql
cf ds -f config 
cf ds -f registry
cf ds -f rabbit
cf ds -f breaker

cf s
