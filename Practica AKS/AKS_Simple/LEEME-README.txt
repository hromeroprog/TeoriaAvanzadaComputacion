Algoritmos para verificar la primalidad
=======================================

Este directorio contiene la implementación de dos algoritmos para verificar la primalidad de un número: AKS y Miller-Rabin

Las clases que implementan los algoritmos están contenidas en el paquete "uc3m.tac.algoritmos" en el directorio "./src". 
Los nombres de las clases principales son AKS y MillerRabin

En el propio directorio "./src" hay disponibles tres clases ejecutables distintas que sirven de ejemplo para ilustrar 
cómo usar las clases AKS y MillerRabin: 

- AksSimple, 
- AksTiming y 
- MillerRabinTiming.

Cada una de ellas contiene su propio método main() por lo que se pueden crear tres programas completamente separados.

COMPILACIÓN y EJECUCIÓN
=======================

Para compilar los programas mencionados desde el interfaz de línea de comandos, ejecutaremos lo siguiente:

    $ cd src
    $ javac -classpath . AksSimple.java

Para ejecutarlo, desde ese mismo directorio:

    java AksSimple

Lo mismo para las otras dos clases. 

TESTING
=======
En el directorio ./test hay dos conjuntos de tests (JUnit) para ambas clases. Los test se ejecutan más cómodamente desde un IDE: IntelliJ o Eclipse









