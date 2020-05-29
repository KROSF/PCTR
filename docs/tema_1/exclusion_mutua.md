# Exclusión Mutua

Consiste en evitar la condición de concurso sobre un recurso compartido forzando la ejecución atómica de las secciones críticas de las entidades concurrentes que lo usan. Elimina el entrelazado.

- Esto implica detener la ejecución de una entidad concurrente hasta que se se produzcan determindas circunstancias: _sincronización_
- También implica el poder comunicar a otras entidades el estaado de una dada: _comunicación_
- Los lenguajes concurrentes deben permitar ambas

## Beneficios

- Dentro de las secciones críticas no hay entrelazado.
- Se incrementa el determinismo, ya que se garantiza la ejecución secuencial (atómica) de las secciones críticas.
- Permite comunicar a los procesos a través de las secciones críticas.
- Acota a nivel de código (sintácticamente ) las secciones críticas mediante el uso de protocolos de entrada y salida de las mismas.
- Pueden generar sobrecargas de ejecucíon.
