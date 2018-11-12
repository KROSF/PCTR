#!/usr/local/bin/gnuplot -persist
set key box bottom right
set key opaque
set key width 2
set datafile separator comma
set sample 1000
set yr [0:500]
set xr [0:2010]
set xlabel 'Dimension'
set ylabel '% CPU'
plot "matriz.csv" using 1:3 w l lt rgb "#D22E3E" lw 2 t 'Secuencial',\
"matrizConcurrente.csv" using 1:3 w l lt rgb "#1662F4" lw 2 t 'Concurrente'
