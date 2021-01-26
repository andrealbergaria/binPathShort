#!/bin/bash
openssl enc -aes-256-cbc -in plainText -p -K 61616161 -iv 0 -out cipherText
