# Programacion Concurrente

Conjunto de notaciones y tecnicas utilizadas para describir mediante programas el paralelismo potencial de los problemas, resolviendo los problemas de sincronización y comunicación que pueden plantearse.

* Se ocupa: del análisis, diseño, implmetacion y depuración concurrentes.
* No se ocupa: del hardware sobre el cual dichos programas concurrentes se ejecutan, ni del lenguaje concreto con el que implemetar la concurrencia.

## Característicaas
* Mejora del rendimiento
* Indeterminacion
  * Un programas secuencial es determinista
  * Un programa concurrente no es determinista
* No atomicidad
* Velocidad de ejecución de procesos desconocida.
* Incertidumbre sobre el resultado.
* Entrelazado
  * Un programa concurrente puede tener varias secuencias de ejecución diferentes.
  * Puede haber entrelazados patológicos que llevan a interbloqueo.
* Mayor dificultad de verificación de programas
