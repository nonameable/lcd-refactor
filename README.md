# lcd-refactor
Refactor of LCD number printer proposed by PSL. 


Objetivo: Crear un programa que imprime números en estilo de una pantalla LCD 

Entrada: La entrada contiene varias líneas. Cada línea contiene 2 números separados por coma. El primer número representa el tamaño de la impresión (entre 1 y 10, esta variable se llama “size”). El segundo número es el número a mostrar en la pantalla. Para terminar, se debe digitar 0,0. Esto terminará la aplicación.

Salida: Imprimir los números especificados usando el caracter “-“ para los caracteres horizontales, y “|” para los verticales. Cada dígito debe ocupar exactamente size+2 columnas y 2*size + 3 filas. 

Entre cada impresión debe haber una línea blanca. 
 
Ejemplo: 
Entrada: 

2,12345

3,67890 

0,0 
   
Salida:   
 <pre>  
   _ _  _ _        _ _
|     |    | |  | |
|  _ _| _ _| |__| |_ _
| |        |    |     |
| |_ _  _ _|    |  _ _|

 _ _ _  _ _ _   _ _ _   _ _ _   _ _ _ 
|            | |     | |     | |     |
|            | |     | |     | |     |
|_ _ _       | |_ _ _| |_ _ _| |     |
|     |      | |     |       | |     |
|     |      | |     |       | |     |
|_ _ _|      | |_ _ _|  _ _ _| |_ _ _|
</pre>
## Cambios en el refactoring


### Convenciones y Lenguaje

1. La mayoría del código está en español. Así es mejor ser consistente y dejar todo en español.

2. Algunas variables no tienen nombres que faciliten la lectura y el entendimiento del código. Cambié algunos nombres por otros más representativos.

Vale notar que todo esto debería estar sujeto a las convenciones y reglas del equipo en que se está trabajando.

### Lógica

1. La utilización de puntos fijos es una idea buena, sin embargo, las reglas para definir el punto fijo 4, aunque correctas, pueden llegar a confundir a alguien que lea el código. Actualmente el par de líneas que definen este punto son las siguientes:


```java
this.pf4[0] = (this.columnasDigito - 1);
this.pf4[1] = (this.filasDigito / 2) + pivotX; 
```

Si se analiza la manera en que se definen los puntos fijos 1, 2, 3 y 5, la posición 0 de cada punto fijo está asociada con la posición en Y en la matriz. De manera similar, la posición 1 del array está asociada con la posición X. Esto se concluye porque la posición 0 es una función de las filasDigito y a su vez, la posición 1 una función de las columnasDigito. 

Este patrón se rompe en la definición del punto fijo 4, pues la posición 0 es función de columnasDigito y la posición 1 es función de filasDigito. Este cambio, sin embargo, no impide que el programa funcione correctamente. ¿Por qué? Es por la definición misma de las variables. Nosotros definimos columnasDigito y filasDigito así:

```java
// Calcula el numero de filas cada digito
this.filasDigito = (2 * this.tamanioDigito) + 3;

// Calcula el numero de columna de cada digito
this.columnasDigito = this.tamanioDigito + 2;
```
Si seguimos el patrón, la definición correcta de las posiciones del punto fijo 4 sería así:

```java
this.pf4[0] = (this.filasDigito / 2);
this.pf4[1] = (this.columnasDigito - 1) + pivotX; 
```

Resulta que estas dos definiciones son equivalentes. Para esto se debe probar que 

```java
(this.filasDigito / 2)
```

es lo mismo que:

```java
(this.columnasDigito - 1);
```

y a su vez que:

```java
(this.columnasDigito - 1)
```

es lo mismo que:

```java
(2 * this.tamanioDigito)
```

Esto es bastante trivial, dada su definición inicial mencionada al comienzo de este README. Sólo es necesario entender la manera como Java trunca la división entre enteros. 

Ahora, como el patrón no se rompe y el programa sigue funcionando a la perfección, decidí cambiar la definición, para que cuando alguien lea el código no tenga la misma confusión que yo tuve. 


### Nuevas funciones

1. Cambié el nombre de imprimirNumero a imprimirDigitos, pues esa es la responsabilidad de ese método. El nombre anterior era confuso, pues podría dar a entender que se estaba imprimiendo sólo uno de los digitos de toda la cadena.


2. Cree el método obtenerDigitos que permite obtener los dígitos a partir de un String particular, validando que todos los caracteres sean números. Esta conversión y verificación antes se hacia dentro de imprimirDigitos(), lo que implicaba que las responsabilidades de ese método eran muchas. Como este método se implementó, cambié los parámetros de entrada de imprimirDigitos(), para que le llegue un int array con los dígitos a imprimir. 















