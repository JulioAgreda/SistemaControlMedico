package com.critico.sistemacontrolmedico.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.critico.sistemacontrolmedico.MainActivity;
import com.critico.sistemacontrolmedico.R;

public class InicialActivity extends AppCompatActivity
{

    private Spinner listaActividades;
    private ArrayAdapter sp;
    private String actividad;

    private Button botonCalendario, botonCalculadora, botonCalcular;
    private EditText varPeso, varEstatura, varEdad, varPromedioGrasa;
    private RadioButton rgbHombre, rgbMujer;
    private int mb, cmp, cb, cs, imc;
    private int intEstatura, intPeso, intEdad, intPromedioGrasa;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        //Agregando la lista al Spinner
        listaActividades = (Spinner) findViewById(R.id.spActividad);
        sp = ArrayAdapter.createFromResource(this, R.array.actividades, android.R.layout.simple_spinner_item);
        sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaActividades.setAdapter(sp);
        actividad = listaActividades.getSelectedItem().toString();

        botonCalcular = (Button)findViewById(R.id.btnCalcular);
        botonCalendario = (Button)findViewById(R.id.btnCalendario);
        botonCalculadora = (Button)findViewById(R.id.btnCalculadora);

        varPeso = (EditText)findViewById(R.id.txtPeso);
        varEstatura = (EditText)findViewById(R.id.txtEstatura);
        varPromedioGrasa = (EditText)findViewById(R.id.txtGrasa);


        intEdad = 31;

        rgbHombre = (RadioButton) findViewById(R.id.rbHombre);
        rgbMujer = (RadioButton) findViewById(R.id.rbMujer);

        botonCalcular.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void iniciarValoresBasicos()
    {
        if (rgbHombre.isChecked())
        {
            double db;
            //  Obteniendo el Metabolismo Basal
            db = (10 * intPeso) + (6.25 * intEstatura) - (5 * intEdad) + 5;
            mb = (int) db;

            actividadSeleccionada();

            bajarSubirCalorias(mb);

            calcularImc();

            //Toast.makeText(MainActivity.this, selec, Toast.LENGTH_SHORT).show();
        }

        if (rgbMujer.isChecked())
        {
            double db;
            //  Obteniendo el Metabolismo Basal
            db = (10 * intPeso) + (6.25 * intEstatura) - (5 * intEdad) - 161;
            mb = (int) db;

            actividadSeleccionada();

            bajarSubirCalorias(mb);

            calcularImc();
        }
    }
    public void Calcular()
    {
        if (rgbHombre.isChecked())
        {
            double db;
            //  Obteniendo el Metabolismo Basal
            db = (10 * intPeso) + (6.25 * intEstatura) - (5 * intEdad) + 5;
            mb = (int) db;

            actividadSeleccionada();

            bajarSubirCalorias(mb);

            calcularImc();

            enviarDatos();

            //Toast.makeText(MainActivity.this, selec, Toast.LENGTH_SHORT).show();
        }

        if (rgbMujer.isChecked())
        {
            double db;
            //  Obteniendo el Metabolismo Basal
            db = (10 * intPeso) + (6.25 * intEstatura) - (5 * intEdad) - 161;
            mb = (int) db;

            actividadSeleccionada();

            bajarSubirCalorias(mb);

            calcularImc();

            enviarDatos();
        }
    }

    public void calcularImc()
    {
        double res = intPeso/(intEstatura * intEstatura);
        imc = (int)res;
    }

    public void calcularPorcentajeGrasa()
    {
        if (rgbMujer.isChecked())
        {
            double res = (1.2 * imc) + (0.23 * intEdad) - (10.8 * (1)) - 5.4;
            intPromedioGrasa = (int)res;
            varPromedioGrasa.setText(intPromedioGrasa+" %");
        }
        if (rgbHombre.isChecked())
        {
            double res = (1.2 * imc) + (0.23 * intEdad) - (10.8 * (0)) - 5.4;
            intPromedioGrasa = (int) res;
            varPromedioGrasa.setText(intPromedioGrasa+" %");
        }
    }

    public void actividadSeleccionada()
    {
        switch(actividad)
        {
            // Calorias para mantener el peso
            case "Sedentario":
                double c = mb * 1.2;
                cmp = (int)c;
                break;

            case "Actividad Moderada":
                double d = mb * 1.375;
                cmp = (int) d;
                break;

            case "Actividad Intensa":
                double a = mb * 1.55;
                cmp = (int) a;
                break;

            case "Actividad Muy Intensa":
                double b = mb * 1.725;
                cmp = (int) b;
                break;
        }
    }

    public void bajarSubirCalorias(int mb)
    {
        // Calorias para bajar de peso
        cb = (int)cmp - (cmp*15/100);
        // Calorias para subir de peso
        cs = (int)cmp + (cmp*15/100);
    }

    public void enviarDatos()
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        intent.putExtra("tmb", mb);
        intent.putExtra("cmp", cmp);
        intent.putExtra("cbp", cb);
        intent.putExtra("csp", cs);
        intent.putExtra("imc", imc);
        intent.putExtra("pgrasa", intPromedioGrasa);

        startActivity(intent);
    }

}
