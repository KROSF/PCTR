#!/bin/bash
count=10
maxcpu=""
tiempo=""
file=$1
java=$2
echo "dimension, tiempo, cpu" > $file
while [ $count -lt 2020 ]; do
    psrecord "java -Dfile.encoding=UTF-8 -cp /Users/krosf/vscode/PCTR/practicas/target/classes -Xmx16g com.krosf.pctr.p4.$java $count" --log $count.txt --interval 0.1
    gsed -i '1d' $count.txt
    maxcpu=$(sort -nrk2,2 $count.txt | head -1 | awk '{print $2}')
    tiempo=$(sort -nrk1,1 $count.txt | head -1 | awk '{print $1}')
    rm -f $count.txt
    echo "$count, $tiempo, $maxcpu" >> $file
    let count=count+100
done