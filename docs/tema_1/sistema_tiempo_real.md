# Sistema de Tiempo Real

Son sistemas concurrentes caracterizados por la existencia de una ligadura temporal para completar una tarea.

- Control robótico, teledirección, telemedicina, multimedia
- El programador tiene acceso al reloj y planificador
- Puden ser:
  - Criticos: las tarea deben, necesariamente, sastisfacer las restricciones impuestas por la ligadura temporal.
  - No criticos: las tareas intentan satisfacer tales restricciones pero el diseño no garantiza la consecución de las mismas con total garantía.
- Linux RT y JRTS

## Caracteristicas

- Predectibilidad
- Determinismo
- Certidumbre
- Control
