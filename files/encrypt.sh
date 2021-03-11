#!/bin/bash

openssl enc -aes-256-cbc -nopad -p -K 6161 -iv 6161 -in plainText16  -out cipherText16
openssl enc -aes-256-cbc -nopad -p -K 6161 -iv 6161 -in plainText32  -out cipherText32
