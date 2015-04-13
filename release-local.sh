#!/usr/bin/env bash
grails clean --stacktrace
grails compile --stacktrace
# local
grails maven-install --stacktrace --verbose
