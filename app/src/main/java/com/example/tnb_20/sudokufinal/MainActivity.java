package com.example.tnb_20.sudokufinal;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Spinner> spins = new ArrayList<>();
    Sudoku sd;
    boolean primeraVuelta;
    int cont, contadorParaPrimeraVuelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciamos la clase sudoku
        sd = new Sudoku();
        List<Integer> tablero = sd.inicializar();

        TableLayout tl = findViewById(R.id.tableLayout);
        String[] datos = new String[10];
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        DisplayMetrics metrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(metrics);


        //rellenamos cada cuadrante
        for (int i = 0; i < 9; i++) {
            sd.rellenarCuadrante(i + 1);
        }

        sd.mostrarTablero();

        tablero= sd.getTablero();

        int w=metrics.widthPixels,h=metrics.heightPixels;

        for(int i = 0;i<datos.length;i++){
            datos[i]=i+"";
        }


        primeraVuelta = true;
        cont=0;
        contadorParaPrimeraVuelta=0;

        //creamos el tablero y introducimos los spinners
        for(int i = 0;i<9;i++){
            TableRow tr = new TableRow(getApplicationContext());
            for(int j = 0;j<9;j++) {
               final Spinner sp = new Spinner(getApplicationContext());
                sp.setLayoutParams(new TableRow.LayoutParams(w/9,h/10));
                sp.setPadding(5,5,5,5);
                sp.setBackground(null);
                sp.setAdapter(adapter);
                sp.setSelection(tablero.get(cont));
                spins.add(sp);
                spins.get(cont).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    //comprobacion de que el numero esta repetido o no
                    //Si es correcto el numero lo pondra en verde, si en incorrecto en rojo
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        for (int i = 0; i<spins.size();i++){
                            if (spins.get(i)==sp){

                                if(sd.comprobarTablero(i,position)){
                                    sp.setBackgroundColor(Color.GREEN);
                                    sd.tablero.set(i,position);

                                }else{
                                    sp.setBackgroundColor(Color.RED);
                                    sd.tablero.set(i,position);
                                }
                            }




                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                cont++;
                tr.addView(sp);
            }

            tl.addView(tr);
        }
    }
}
