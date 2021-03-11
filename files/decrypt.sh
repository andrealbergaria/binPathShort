#!/bin/bash
openssl enc -d -nopad -aes-256-cbc -in cipherText16 -K 616161 -iv 616161 -out plainText16
openssl enc -d -nopad -aes-256-cbc -in cipherText32 -K 616161 -iv 616161 -out plainText32
