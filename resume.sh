#!/bin/bash
PIDFILE=$(pidof java);
kill -CONT $PIDFILE;
