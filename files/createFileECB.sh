#!/bin/bash
seq 1 8 | tr '\n' 'A' > plainText16
seq 1 8 | tr '\n' 'A' > plainText32
seq 1 8 | tr '\n' 'B' >> plainText32


ls -l plainText*
