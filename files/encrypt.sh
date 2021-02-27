#!/bin/bash
seq 1 8 | tr '\n' 'A' > plainText
seq 1 8 | tr '\n' 'A' >> plainText

openssl enc -aes-256-cbc -nopad -p -K 616161 -iv 616161 -in plainText  -out cipherText
