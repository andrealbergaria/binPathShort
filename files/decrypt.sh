#!/bin/bash
openssl enc -d -aes-256-cbc -in cipherText -K 61616161 -iv 0 -out plainTextDeciphered
