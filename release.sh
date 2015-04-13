#!/usr/bin/env bash
grails clean --stacktrace
grails compile --stacktrace

#grails publish-plugin --snapshot --stacktrace
grails maven-install --stacktrace
