#!/bin/bash

cat ~/.bashrc | grep $(pwd) || echo "alias tt='java -jar $(pwd)/trans.jar'" >> ~/.bashrc

source ~/.bashrc
