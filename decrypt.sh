#!/bin/bash
openssl enc -d -aes-256-cbc -in cipherText -K 616161 -iv 616161 -out plainText
openssl enc -d -aes-256-ecb -K 616161 -in cipherTextECB -out plainTextECB
