#!/bin/zsh

cat ~/.bashrc  | grep -q $(pwd) || echo alias tt=\"java -jar "$(pwd)"/trans.jar\" >> ~/.bashrc

cat ~/.zshrc  | grep -q $(pwd) || echo alias tt=\"java -jar "$(pwd)"/trans.jar\" >> ~/.zshrc


export PATH=$PATH:$(pwd)

echo alias tt=\"java -jar "$(pwd)"/trans.jar\"