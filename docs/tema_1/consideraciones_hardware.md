# Consideraciones Sobre el Hardware

- Sistemas monoprocesador
  - Modelo de concurrencia simulado
  - Existe interfoliación de instrucciones.
  - Arquitectura de memoria común.
  - Existe planificación en el acceso al procesador.
- Sistemas multiprocesador
  - Acoplamiento fuerte
    - Arquitectura de memoria común (UMA).
    - Soluciones propuestas para monoprocesadores admisibles.
    - Exite planificación en el acceso a los núcleos.
  - Acoplamiento débil
    - Arquitectura de comunicaciones
    - Arquitectura de memoria no común (NUMA).
    - Necesidad de soluciones ad-hoc
