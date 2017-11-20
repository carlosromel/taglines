#!/bin/bash

for tag in a b c d e f g h i j k l m n o p q r s t u v w x y z other quotes; do
    if [ -f "Taglines Galore ${tag}.tags.html" ]; then
        mv "Taglines Galore ${tag}.tags.html" ${tag}.txt
    fi

    if [ -f ${tag}.txt ]; then
        sed --in-place "s/\<html\>.*\<pre\>//m" ${tag}.txt
    fi
done

