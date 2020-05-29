# Entrelazado

- Tiene diferentes grupos de intrucciones de distintos procesos.

## Codicione de Bernstein

- $L(S_k) = \{a_1,a_2,\dots,a_n\}$ es el conjunto de lectura del conjunto de intrucciones $S_k$
- $E(S_k) = \{b_1,b_2,\dots,b_n\}$ es el conjunto de escritura del conjunto de intrucciones $S_k$

* Dos conjunto de intrucciones $S_i$ y $S_j$ se pueden ejecutar concurentemente si:
  - $L(S_i)\cap E(S_j) = \empty$
  - $E(S_i)\cap L(S_j) = \empty$
  - $E(S_i)\cap E(S_j) = \empty$
