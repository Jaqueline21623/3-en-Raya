package com.example.triki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    // Inicializar los elementos usados en la app
    private Button casilla1;
    private Button casilla2;
    private Button casilla3;
    private Button casilla4;
    private Button casilla5;
    private Button casilla6;
    private Button casilla7;
    private Button casilla8;
    private Button casilla9;
    private Button btnReiniciar;
    private TextView txtJugador;
    private TextInputEditText editTextJugador1;
    private TextInputEditText editTextJugador2;
    private String[][] array;
    private int jugador;

    // Instancia de ApiService
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuración de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/michi-api/") // Cambia la URL según sea necesario
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        inicializar(); // Inicializa los elementos de la app
        inicializarArray(); // Inicializa el arreglo de casillas

        // Asignar un listener de clic a cada casilla
        casilla1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarMovimiento(0, 0);
            }
        });
        casilla2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarMovimiento(0, 1);
            }
        });
        casilla3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarMovimiento(0, 2);
            }
        });
        casilla4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarMovimiento(1, 0);
            }
        });
        casilla5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarMovimiento(1, 1);
            }
        });
        casilla6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarMovimiento(1, 2);
            }
        });
        casilla7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarMovimiento(2, 0);
            }
        });
        casilla8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarMovimiento(2, 1);
            }
        });
        casilla9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarMovimiento(2, 2);
            }
        });

        // Lógica para reiniciar el juego
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarJuego();
            }
        });
    }

    // Inicializar los elementos de la interfaz y variables
    private void inicializar() {
        // Asignar los botones de las casillas en la variable correspondiente
        casilla1 = findViewById(R.id.btn1);
        casilla2 = findViewById(R.id.btn2);
        casilla3 = findViewById(R.id.btn3);
        casilla4 = findViewById(R.id.btn4);
        casilla5 = findViewById(R.id.btn5);
        casilla6 = findViewById(R.id.btn6);
        casilla7 = findViewById(R.id.btn7);
        casilla8 = findViewById(R.id.btn8);
        casilla9 = findViewById(R.id.btn9);
        // Obtiene la referencia al TextView del jugador en el diseño de la actividad
        txtJugador = findViewById(R.id.txtJugador);
        // Inicializa los TextInputEditText para los nombres de los jugadores
        editTextJugador1 = findViewById(R.id.editTextJugador1);
        editTextJugador2 = findViewById(R.id.editTextJugador2);
        // Inicializa el botón de reiniciar juego
        btnReiniciar = findViewById(R.id.btn_reiniciar);

        // Inicializa una matriz de cadenas de tamaño 3x3 para representar las casillas del juego
        array = new String[3][3];
        // Inicializar el jugador en 1
        jugador = 1;
    }

    // Inicializar todas las casillas del arreglo con una cadena vacía
    private void inicializarArray() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = "";
            }
        }
    }

    // Alternar el turno entre los jugadores y colocar "X" o "O" en la casilla según sea el caso
    private String turnoJugador() {
        if (jugador == 1) {
            txtJugador.setText("Turno jugador " + jugador);
            jugador = 2;
            return "X";
        } else {
            txtJugador.setText("Turno jugador " + jugador);
            jugador = 1;
            return "O";
        }
    }

    // Verifica si una casilla está vacía y devuelve el valor a colocar en ella
    private String validar_casilla(String celda) {
        if (celda.equals("")) {
            String imprimir = turnoJugador();
            return imprimir;
        } else {
            Toast.makeText(MainActivity.this, "No se puede seleccionar esta casilla", Toast.LENGTH_SHORT).show();
            return celda;
        }
    }

    // Limpia el texto de todas las casillas en la interfaz
    private void limpiarCasillas() {
        casilla1.setText("");
        casilla2.setText("");
        casilla3.setText("");
        casilla4.setText("");
        casilla5.setText("");
        casilla6.setText("");
        casilla7.setText("");
        casilla8.setText("");
        casilla9.setText("");
    }

    // Reinicia el juego
    private void reiniciarJuego() {
        // Limpiar el arreglo y reiniciar las casillas en la interfaz
        inicializarArray();
        limpiarCasillas();

        // Reiniciar el jugador y actualizar el TextView
        jugador = 1;
        txtJugador.setText("Turno jugador " + jugador);
    }

    // Verifica si hay un ganador o empate
    private void verificarGanador() {
        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (array[i][0].equals(array[i][1]) && array[i][0].equals(array[i][2]) && !array[i][0].equals("")) {
                mostrarGanador(array[i][0]);
                return;
            }
        }

        // Verificar columnas
        for (int i = 0; i < 3; i++) {
            if (array[0][i].equals(array[1][i]) && array[0][i].equals(array[2][i]) && !array[0][i].equals("")) {
                mostrarGanador(array[0][i]);
                return;
            }
        }

        // Verificar diagonales
        if (array[0][0].equals(array[1][1]) && array[0][0].equals(array[2][2]) && !array[0][0].equals("")) {
            mostrarGanador(array[0][0]);
            return;
        }

        if (array[0][2].equals(array[1][1]) && array[0][2].equals(array[2][0]) && !array[0][2].equals("")) {
            mostrarGanador(array[0][2]);
            return;
        }

        // Verificar empate
        boolean empate = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i][j].equals("")) {
                    empate = false;
                    break;
                }
            }
        }
        if (empate) {
            mostrarEmpate();
            return;
        }
    }

    // Muestra el mensaje de ganador y guarda el resultado en la base de datos
    private void mostrarGanador(String valorGanador) {
        // Determinar el nombre del jugador ganador según el valor "X" o "O"
        String nombreGanador;
        if (valorGanador.equals("X")) {
            nombreGanador = editTextJugador1.getText().toString(); // Si X ganó, el Jugador 1 es el ganador
        } else {
            nombreGanador = editTextJugador2.getText().toString(); // Si O ganó, el Jugador 2 es el ganador
        }

        Toast.makeText(MainActivity.this, "¡Ha ganado el jugador " + nombreGanador + "!", Toast.LENGTH_SHORT).show();

        // Obtener los nombres de los jugadores
        String nombreJugador1 = editTextJugador1.getText().toString();
        String nombreJugador2 = editTextJugador2.getText().toString();

        // Guardar en la base de datos
        apiService.guardarGanador(nombreJugador1, nombreJugador2, nombreGanador).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Ganador guardado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error al guardar el ganador", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        reiniciarJuego();
    }

    // Muestra el mensaje de empate y reinicia el juego
    private void mostrarEmpate() {
        Toast.makeText(MainActivity.this, "¡Empate!", Toast.LENGTH_SHORT).show();
        reiniciarJuego();
    }

    // Realiza el movimiento del jugador y verifica el estado del juego
    private void realizarMovimiento(int fila, int columna) {
        String imprimir = validar_casilla(array[fila][columna]);
        array[fila][columna] = imprimir;

        // Obtener el botón correspondiente según la fila y columna
        Button boton = null;
        switch (fila) {
            case 0:
                switch (columna) {
                    case 0:
                        boton = casilla1;
                        break;
                    case 1:
                        boton = casilla2;
                        break;
                    case 2:
                        boton = casilla3;
                        break;
                }
                break;
            case 1:
                switch (columna) {
                    case 0:
                        boton = casilla4;
                        break;
                    case 1:
                        boton = casilla5;
                        break;
                    case 2:
                        boton = casilla6;
                        break;
                }
                break;
            case 2:
                switch (columna) {
                    case 0:
                        boton = casilla7;
                        break;
                    case 1:
                        boton = casilla8;
                        break;
                    case 2:
                        boton = casilla9;
                        break;
                }
                break;
        }

        // Asignar el valor ("X" o "O") al texto del botón
        if (boton != null) {
            boton.setText(imprimir);
        }

        // Verificar el estado del juego después de cada movimiento
        verificarGanador();
    }
}