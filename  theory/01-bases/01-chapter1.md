# **Tema 1**
## **Bases de la Concurrencia**

### **Proceso**
Un **proceso** es un programa que puede terner varios hilos (secuencia de instrucciones, que se ejecutan de forma independiete).

Los hilos no comparten la pila ni el contador de programa, pero si memoria de proceso y los recursos del sistema.

### **Concurrencia vs Paralelismo**

La **concurrencia** es la capacidad que tiene un procesador de realizar mas de una tarea a la vez, por el contrario el **paralelismo** sigue la filosofia "*divide y venceras*", que consiste en tomar una unica tarea, dividirla en subtareas mas pequeñas para procesar estas de forma **concurrente** aprovechado al maximo esta capicidad del procesador.

![Concurrencia](https://www.oscarblancarteblog.com/wp-content/uploads/2017/03/1-1.png)

![Paralelismo](https://www.oscarblancarteblog.com/wp-content/uploads/2017/03/2.png)

### **Sistemas**

* Sistemas **inherentemente** concurrentes:
  * El entorno con el que interactuán, o el entoro que modelan tienen forzosamente actividad simultaneas.
* Sistema potencialmente concurrentes:
  * No es estrictamente necesario que exista concurrencia, pero se puede sacar partido de ella.

### **Atomicidad**

La **atomicidad** es la propiedad que asegura que una operacion se ha realizado o no, y por lo tanto ante un fallo del sistema esta no puede quedar en un estado intermedio, se dice que una operacion es atomica si es imposible para otra parte de un sistema encontrar pasos intermedios.

### **SpeedUp**

Coeficiente entre el tiempo necesario para completar una tarea secuencialmente y el tiempo en hacerlo paralelamente, a partir de este coeficiente podemos de terminar si es conveniente emplear un solucion paralela o no.
$$S=\frac{T_s}{T_p}$$

* Si $S < 1$, la paralelización ha empeorado la solución.
* Si $1 < S < nucleos$, hemos conseguido mejorar la solucón en un rango normal.
* Si $S > nucleos$, la mejora de la solución es hiperlineal.

### **Exclusion mutua**

Consisten en evitar que una condición de concurso sobre un recurso compartido, forzando la ejecución atomica definiendo un **seccion critica** en las entidades concurrentes que actua sobre dicho recurso. Esto implica sincronizar para deterner la la ejucución de una entidad concurrente, hasta que se cumplan una codiciones para poder continuar. Para lograr la **sincronización** se aplican protocolos de entrada y salida para garantizar que un hay un solo hilo en la **seccion critica**, junto a condiciones de vivacidad para permitir que un hilo entre mas de una vez en la **seccion critica**.

* **Beneficios:**
  * Dentro de la seccion critica no se produce entrelazado.
  * Se incrementa el **determinismo**, ya que garantiza la ejecucion secuncial dentro de la **seccion critica**.
  * Permite la comunicacion entre los procesos.

### Taxonomia de Flynn
#### SISD
![SISD](https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/SISD.svg/500px-SISD.svg.png)
Arquitectura computacional en la que un único procesador ejecuta un sólo flujo de instrucciones, para operar sobre datos almacenados en una única memoria.
#### MISD
Muchas unidades funcionales realizan diferentes operaciones en los mismos datos
![MISD](https://upload.wikimedia.org/wikipedia/commons/thumb/9/97/MISD.svg/500px-MISD.svg.png)

#### SIMD
Permite efectuar varias operaciones de cálculo con una sola instrucción.
![SIMD](https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/SIMD.svg/500px-SIMD.svg.png)
#### MIMD
Es un sistema con un flujo de múltiples instrucciones que operan sobre múltiples datos.
![MIMD](https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/MIMD.svg/500px-MIMD.svg.png)

### **Atomic Statements**
Instrucciones que se ejecutarán y finalizarán sin la posibilidad de entrelazado de instrucciones procedentes de otro proceso.Esto proporciona la capacidad de ejecutar dos procesos concurrentes y obtener el mismo resultado que la ejecución secuencial de estos,por lo tanto no habrá inconsistencias.

### **Atomic Access**

### **Volatile and Non-Atomic Variables**

### **Entrelazado**
Dada una línea de tiempo, el entrelazado es la característica que denota diferentes grupos de instrucciones de distintos procesos en el procesador lo cual se produce por diferentes ejecucciones de distintos hilo.

### **Corrección**


