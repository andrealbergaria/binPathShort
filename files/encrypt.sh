#!/bin/bash

openssl enc -aes-256-cbc -nopad -p -K 616161 -iv 616161 -in plainText16  -out cipherText16
openssl enc -aes-256-cbc -nopad -p -K 616161 -iv 616161 -in plainText32  -out cipherText32
