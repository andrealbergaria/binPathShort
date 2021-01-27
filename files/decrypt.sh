#!/bin/bash
openssl enc -d -aes-256-cbc -in cipherText -K 6161616161 -iv 6161616161 -out plainText
