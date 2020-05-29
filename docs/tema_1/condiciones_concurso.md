# Condiciones de Concurso

- Aparecen cuando varias entidades concurrentes comparten recursos comunes accediendo a ellos simultáneamente.
- Pueden dar lugar a problemas graves como sobreescritura de datos, interbloqueos.
- Son propias de los sistemas concurrentes.
- Se caracterizan, a nivel de programación, por zonas de código de las entidades concurrentes desde las que se accede a recursos comunes. Se las llama secciones críticas.
- Dado el indeterminismo de los programas concurrentes, la única forma de evitar una condición de concurso será forzar la ejecución aislda de cada sección crítica.
