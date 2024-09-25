x# AppAndroid-TicTacToe-Game-Triki
Este aplicativo de Android llamado "Tic Tac Toe" (Triki o Tres en Raya) es un juego interactivo desarrollado en el lenguaje de programación Java utilizando el entorno de desarrollo integrado Android Studio. El juego permite a dos jugadores competir para lograr alinear tres de sus símbolos (X u O) en una cuadrícula de 3x3.

## Interfal del aplicativo
A continuación, se muestran dos imágenes que presentan la interfaz del aplicativo.

![image](https://github.com/DannyRMoreno28/AppAndroid-TicTacToe-Game-Triki/assets/108888740/dca31565-8b18-493b-abd4-67196c7a593a) -
![image](https://github.com/DannyRMoreno28/AppAndroid-TicTacToe-Game-Triki/assets/108888740/28490bae-d225-4b2c-9b57-8c0b4cf494d4)


## Descripción del código fuente del aplicativo
El código fuente del aplicativo se encuentra en el archivo MainActivity.java (app/src/main/java/com/example/triki/MainActivity.java). A continuación, se proporciona una descripción de las principales características y funciones de este código:

* El método ***onCreate*** se ejecuta cuando la actividad se crea e inicializa los elementos de la interfaz y las variables necesarias para el juego.
* El método ***inicializar*** se encarga de asignar los botones de las casillas, el TextView del jugador y el botón de reinicio.
* El método ***inicializarArray*** inicializa el arreglo de casillas con valores vacíos.
* El método ***turnoJugador*** alterna el turno entre los jugadores y devuelve el símbolo correspondiente ("X" u "O").
* El método ***validar_casilla*** verifica si una casilla está vacía y devuelve el valor a colocar en ella.
* El método ***limpiarCasillas*** se encarga de limpiar el texto de todas las casillas en la interfaz.
* El método ***reiniciarJuego*** reinicia el juego, limpiando el arreglo y las casillas en la interfaz, y reiniciando el jugador.
* El método ***verificarGanador*** verifica si hay un ganador o un empate en el juego.
* Los métodos ***mostrarGanador*** y ***mostrarEmpate*** muestran mensajes en forma de Toast cuando hay un ganador o un empate, respectivamente.
* El método ***realizarMovimiento*** se ejecuta cuando se realiza un movimiento en una casilla. Actualiza el arreglo, muestra el símbolo en la casilla correspondiente y verifica el estado del juego.

El archivo de diseño ***activity_main.xml*** contiene la estructura de la interfaz de usuario. Se utiliza el componente ConstraintLayout para posicionar los elementos en la pantalla. Los botones representan las casillas del juego, el TextView muestra el turno del jugador y el botón de reinicio reinicia el juego. EL codigó referente a la interfas se encuenta en el archivo ***activity_main.xml*** (app/src/main/res/layout/activity_main.xml).

Para ejecutar este aplicativo, es necesario tener instalado Android Studio y configurar un emulador o dispositivo Android para la depuración. Se recomienda compilar y ejecutar el código en una versión de Android compatible.

¡Diviértete jugando "Triki", "Tres en Raya" o "Tic Tac Toe" con este aplicativo!
