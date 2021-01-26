#!/bin/bash
JAVAPID=$(pidof java)
kill -STOP $JAVAPID
#kill -CONT
