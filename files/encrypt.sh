#!/bin/bash
openssl enc -aes-256-cbc -p -K 616161 -iv 616161 -in plainText  -out cipherText
