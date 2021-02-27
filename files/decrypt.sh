#!/bin/bash
openssl enc -d -nopad -aes-256-cbc -in cipherText -K 616161 -iv 616161 -out plainText
