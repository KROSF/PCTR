# Speedup

En paralelismo, podemos definir el concepto de speedup como el coeficiente entre el tiempo necesario para completar una tarea secuencialmete y el tiempo necesario para hacerlo en paralelo.

$$S = \frac{Ts}{Tp}$$

1. Si $S < 1$, la paralelización ha hecho que la solución emperore.
1. Si $1 < S \leq nucleos$, hemos conseguido mejorar la solución en un rango normal.
1. Si $S > nucleos$, la mejora obtenidad ha sido hiperlineal.
