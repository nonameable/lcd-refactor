# lcd-refactor
Refactor de una impresora LCD propuesto por PSL.


Objetivo: Crear un programa que imprime números en estilo de una pantalla LCD 

Entrada: La entrada contiene varias líneas. Cada línea contiene 2 números separados por coma. El primer número representa el tamaño de la impresión (entre 1 y 10, esta variable se llama “size”). El segundo número es el número a mostrar en la pantalla. Para terminar, se debe digitar 0,0. Esto terminará la aplicación.

Salida: Imprimir los números especificados usando el caracter “-“ para los caracteres horizontales, y “|” para los verticales. Cada dígito debe ocupar exactamente size+2 columnas y 2*size + 3 filas. 

Entre cada impresión debe haber una línea blanca. 
 
Ejemplo: 

**Entrada**: 

<pre>
2,12345

3,67890 

0,0 
</pre>
   
**Salida**:   
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





## Corriendo el proyecto

Ubicarse en el directorio raíz del repositorio y correr:

`javac ./*.java`

Y luego correr:

`java LCDTester`


## Testing

Como no especificaron cómo querían incluir los tests, me abstuve de asumir que los iban a correr con Maven o Gradle. Teniendo esto en cuenta, decidí incluir las librerías necesarias para correr los tests en el repositorio `junit-4.12.jar` y `hamcrest-core-1.3.jar`. Estas librerías se encuentran en la carpeta /testing_libraries. Entonces, para correr las pruebas automáticas, ubicarse en el directorio raíz del repositorio y seguir los siquientes pasos:


Compilar las clases (incluída la clase de tests):

`javac ./*.java`

Correr los tests:

`java -cp ./:./testing_libraries/:./testing_libraries/ org.junit.runner.JUnitCore TestSuite`

Correr esa suite de tests corre los tests para todas las clases.

Pretendía separar las clases del proyecto y las clases de tests en dos carpetas diferentes. Esto hizo que debido a la manera como Java maneja los paquetes las clases de Test no pudieran acceder a las clases del proyecto. Hacer que funcione como pretendía es sencillo con Maven, Gradle o simplemente con un IDE como Eclipse, que nos ahorra el trabajo del manejo de los paquetes y las dependencias para el proyecto y para los tests.

Se realizaron pruebas unitarias sobre las clases: ImpresorLCD, Impresion y Utilidades.


## Cambios en el refactoring


### Convenciones y Lenguaje

1. La mayoría del código está en español. Así es mejor ser consistente y dejar todo en español.

2. Algunas variables no tienen nombres que faciliten la lectura y el entendimiento del código. Cambié algunos nombres por otros más representativos.

3. Removí el uso innecesario de la palabra clave `this`.

4. Algunos atributos no eran private, los dejé private.

Vale mencionar que todo esto debería estar sujeto a las convenciones y reglas del equipo en que se está trabajando. Como no se estableció ninguna regla, asumí varias cosas. 

### Lógica

1. La utilización de puntos fijos es una idea buena, sin embargo, las reglas para definir el punto fijo 4, aunque correctas, pueden llegar a confundir a alguien que lea el código. Actualmente el par de líneas que definen este punto son las siguientes:


```java
pf4[0] = (columnasDigito - 1);
pf4[1] = (filasDigito / 2) + pivotX; 
```

Si se analiza la manera en que se definen los puntos fijos 1, 2, 3 y 5, la posición 0 de cada punto fijo está asociada con la posición en Y en la matriz. De manera similar, la posición 1 del array está asociada con la posición X. Esto se concluye porque la posición 0 es una función de las `filasDigito` y a su vez, la posición 1 una función de las `columnasDigito`. 

Este patrón se rompe en la definición del punto fijo 4, pues la posición 0 es función de `columnasDigito` y la posición 1 es función de `filasDigito`. Este cambio, sin embargo, no impide que el programa funcione correctamente. ¿Por qué? Es por la definición misma de las variables. Nosotros definimos `columnasDigito` y `filasDigito` así:

```java
// Calcula el numero de filas cada digito
filasDigito = (2 * tamanioDigito) + 3;

// Calcula el numero de columna de cada digito
columnasDigito = tamanioDigito + 2;
```
Si seguimos el patrón, la definición correcta de las posiciones del punto fijo 4 sería así:

```java
pf4[0] = (filasDigito / 2);
pf4[1] = (columnasDigito - 1) + pivotX; 
```

Resulta que estas dos definiciones son equivalentes. Para esto se debe probar que 

```java
(filasDigito / 2)
```

es lo mismo que:

```java
(columnasDigito - 1);
```

y a su vez que:

```java
(columnasDigito - 1)
```

es lo mismo que:



Esto es bastante trivial, dada su definición matemática mencionada al comienzo de este README. Sólo es necesario entender la manera como Java trunca la división entre enteros. 

Ahora, como el patrón no se rompe y el programa sigue funcionando a la perfección, decidí cambiar la definición para que la coordenada 0 del punto esté definida con las filas y la coordenada 1 del punto esté definida con las columnas. El código queda así más claro y menos confuso para el siguiente que lo quiera leer.



### Clases nuevas

1. Creé la clase `Utilidades` que por ahora sólo contiene el metodo `esNumerico()`.

2. Creé la clase `DiccionarioSegmentos` que sirve como diccionario para obtener los segmentos que constituyen cada dígito posible.Esta clase podría ser más limpia si pudiéramos cargar los segmentos de cada número de un archivo de texto o similar. Por ahora, los segmentos de cada número están "hardcoded" en el código.


3. Creé la clase `Impresion` que encapsula las propiedades de la impresión actual en un objeto. Estas variables antes estaban como atributos de la impresora. Una explicación más detallada de esta decisión se encuentra abajo. 


### Funciones Nuevas

1. Cambié el nombre de `imprimirNumero()` a `insertarDigitosEnMatriz()`, pues esa es la responsabilidad de ese método. El nombre anterior era confuso, pues podría dar a entender que se estaba imprimiendo sólo uno de los digitos de toda la cadena.


2. Creé el método `obtenerDigitos()` que permite obtener los dígitos a partir de un String particular, validando que todos los caracteres sean números. Esta conversión y verificación antes se hacia dentro de `insertarDigitosEnMatriz()` (antes llamada `imprimirNumero()`), lo que implicaba que las responsabilidades de ese método eran muchas. Como este método se implementó, cambié los parámetros de entrada de `insertarDigitosEnMatriz()`, para que le llegue un int array con los dígitos a imprimir. 

3. Creé el Método `imprimirMatriz()` que imprime en consola la matriz que entra por parametro. En caso de que la matriz no este inicializada, lanza `NullPointerException`.

### Funciones viejas

1. Dentro de la función `adicionarDigito()` estaba el código que escogía que segmentos pertenecían a cada número. Esto debe hacerse, sólo que considero no es responsabilidad de esta función. Decidí simplificarla y crear una nueva función `obtenerSegmentosPara()` que obtiene los segmentos de cada número. Estos están almacenados en un objeto externo que funciona como un diccionario de los segmentos para cada número. Esto nos permite tener una mejor distribución de responsabilidades entre las clases. Sería bueno que el método con el que se obtienen los números fuera estático para que pudiera llamarse sin necesidad de instanciar la clase en ImpresorLCD. Esto, sin embargo, haría que fuera necesario llamar otro método estático que "poblara" los segmentos para cada número dentro del código de ImpresorLCD en algún momento, lo que me parece no debería suceder pues no es su responsabilidad. Preferí hacer que fuera necesario instanciar la clase, pues es más limpio.

### Variables

1. Hay 6 variables que se usan constantemente en todos métodos de la impresora:


```java
private int filasDigito;
private int columnasDigito;
private int totalFilas;
private int totalColumnas;
private int tamanioDigito;
private int espacioEntreDigitos;
```
Algunas de estas variables eran variables de cada instancia de la clase `ImpresorLCD`, otras eran pasadas por parámetro a cada función que las necesitaba. Dónde deberían estar estas variables no es obvio. Algo obvio sería un atributo como raza respecto a una clase Perro. En este caso, estas variables no describen la impresora en sí, sino las características de la impresión actual. Después de una búsqueda rápida, encontré los siguientes comentarios que tal vez nos ayuden a determinar dónde deberían ir (tomadas de [link](https://stackoverflow.com/questions/346169/when-to-pass-a-parameter-and-when-to-use-an-instance-variable) ) :


> Instance variables are typically considered to be attributes of a class.

> Local variables are used within the scope of methods to help them complete their work.

> Mainly this depends on the lifetime of the data you store in the variable. If the data is only used during a computation, pass it as a parameter. If the data is bound to the lifetime of the object use an instance variable.
When your list of variables gets too long, maybe it's a good point to think about refactoring some parts of the class into a new class.

> If the variable forms part of the state of the instance, then it should be an instance variable - classinstance HAS-A instancevariable.

> About passing parameters all they way up the stack, this can get ugly very fast. A rule of thumb is to keep your method signatures clean and elegant. If you see many methods using the same data, decide either if it's important enough to be a class member, and if it's not, refactor your code to have it make more sense.
It boils down to common sense. Think exactly where and why you are declaring each new variable, what it's function should be, and from there make a decision regarding which scope it should live in.

La última respuesta me parece es que la más útil. Si estos atributos se pasarán de función en función, pasarían por funciones en donde no se están usando para nada con el fin de llegar a la función final. Esto complica la definición de cada función y hace el código más complicado de seguir. Esto me hace concluir que debo dejar de pasarlas como parámetros para dejarlas accesibles en un scope más global, para que cualquier método pueda usarlas una vez hayan sido inicializadas.

Ahora, vale preguntarse si estas variables definen el estado de la impresora. Como mencioné arriba, pienso que estas variables definen las características de la impresión actual. Una opción que tiene sentido sería **encapsular todas estas variables dentro de una nueva clase que llamaríamos Impresion**. Esta solución sin embargo, hace que el código fuera un **poco más complejo**. 

Otras variables que definitivamente no describen el estado actual de la impresora, pero sí de la impresión, son los puntos fijos. Después de considerarlo, **decidí encapsular estos puntos fijos y a su vez las variables** que definen la impresión actual dentro de una nueva clase llamada `Impresion`.

Esta encapsulación sólo complica un poco el código, como podemos verlo en este caso.


#### Antes
```java
private void construirMatrizVacia(){
        
        // crea matriz para almacenar los numero a imprimir
        String[][] matrizImpresion = new String[totalFilas][totalColumnas];

        // Inicializa matriz
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColumnas; j++) {
                matrizImpresion[i][j] = " ";
            }
        }

        this.matrizImpresion = matrizImpresion;
        
    }

```

#### Ahora

```java
private void construirMatrizVacia(){
        
        int totalFilas = impresionActual.darTotalFilas();
        int totalColumnas = impresionActual.darTotalColumnas();
        
        // crea matriz para almacenar los numero a imprimir
        String[][] matrizImpresion = new String[totalFilas][totalColumnas];

        // Inicializa matriz
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColumnas; j++) {
                matrizImpresion[i][j] = " ";
            }
        }

        impresionActual.matrizImpresion = matrizImpresion;
        
    }
```


















