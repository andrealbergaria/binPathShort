#!/bin/bash
seq 1 8 | tr '\n' 'A' >> plainText
seq 1 8 | tr '\n' 'A' >> plainText

openssl enc -aes-256-cbc -no-pad -p -K 616161 -iv 616161 -in plainText  -out cipherText
openssl enc -aes-256-ecb -no-pad -p -K 616161 -in plainTextECB -out cipherTextECB
