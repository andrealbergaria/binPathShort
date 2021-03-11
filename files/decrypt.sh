#!/bin/bash
openssl enc -d -nopad -aes-256-cbc -in cipherText16 -K 6161 -iv 6161 -out plainText16
openssl enc -d -nopad -aes-256-cbc -in cipherText32 -K 6161 -iv 6161 -out plainText32
