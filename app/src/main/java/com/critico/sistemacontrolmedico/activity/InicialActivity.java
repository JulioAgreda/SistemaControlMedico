package com.critico.sistemacontrolmedico.activity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.critico.sistemacontrolmedico.MainActivity;
import com.critico.sistemacontrolmedico.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InicialActivity extends AppCompatActivity implements View.OnClickListener
{

    private Spinner listaActividades, listaDia, listaMes, listaAño;
    private ArrayAdapter sp, sd, sm, sa;
    private String actividad, dia, mes, año,stringMes;

    private Button botonCalendario, botonCalculadora, botonCalcular;
    private EditText varPeso, varEstatura, varEdad, varPromedioGrasa;
    private RadioButton rgbHombre, rgbMujer;
    private int intMb, intCmp, intCb, intCs, intImc, intEstatura, intPeso, intEdad, intPromedioGrasa;

    private int intDia, intMes, intAño;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        iniciando();

        botonCalcular.setOnClickListener(this);
        botonCalculadora.setOnClickListener(this);
        botonCalendario.setOnClickListener(this);
    }

    public void iniciando()
    {
        cargandoListas();

        botonCalcular = (Button)findViewById(R.id.btnCalcular);
        botonCalendario = (Button)findViewById(R.id.btnCalendario);
        botonCalculadora = (Button)findViewById(R.id.btnCalculadora);

        varPeso = (EditText)findViewById(R.id.txtPeso);
        varEstatura = (EditText)findViewById(R.id.txtEstatura);
        varPromedioGrasa = (EditText)findViewById(R.id.txtGrasa);

        intEdad = 31;

        rgbHombre = (RadioButton) findViewById(R.id.rbHombre);
        rgbMujer = (RadioButton) findViewById(R.id.rbMujer);
    }

    public void cargandoListas()
    {
        listaActividades = (Spinner) findViewById(R.id.spActividad);
        listaDia = (Spinner)findViewById(R.id.spDia);
        listaMes = (Spinner)findViewById(R.id.spMes);
        listaAño = (Spinner)findViewById(R.id.spAño);

        sp = ArrayAdapter.createFromResource(this, R.array.actividades, android.R.layout.simple_spinner_item);
        sd = ArrayAdapter.createFromResource(this, R.array.dia, android.R.layout.simple_spinner_item);
        sm = ArrayAdapter.createFromResource(this, R.array.mes, android.R.layout.simple_spinner_item);
        sa = ArrayAdapter.createFromResource(this, R.array.año, android.R.layout.simple_spinner_item);

        sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listaActividades.setAdapter(sp);
        listaDia.setAdapter(sd);
        listaMes.setAdapter(sm);
        listaAño.setAdapter(sa);

        actividad = listaActividades.getSelectedItem().toString();
        dia = listaDia.getSelectedItem().toString();
        mes = listaMes.getSelectedItem().toString();
        año = listaAño.getSelectedItem().toString();
    }

    public void Calcular()
    {
        intEstatura = Integer.valueOf(varEstatura.getText().toString());
        intPeso = Integer.valueOf(varPeso.getText().toString());
        intPromedioGrasa = Integer.valueOf(varPromedioGrasa.getText().toString());

        if (rgbHombre.isChecked())
        {
            double db;
            //  Obteniendo el Metabolismo Basal
            db = (10 * intPeso) + (6.25 * intEstatura) - (5 * intEdad) + 5;
            intMb = (int) db;

            actividadSeleccionada();

            bajarSubirCalorias(intMb);

            calcularImc();

            enviarDatos();

            //Toast.makeText(MainActivity.this, selec, Toast.LENGTH_SHORT).show();
        }

        if (rgbMujer.isChecked())
        {
            double db;
            //  Obteniendo el Metabolismo Basal
            db = (10 * intPeso) + (6.25 * intEstatura) - (5 * intEdad) - 161;
            intMb = (int) db;

            actividadSeleccionada();

            bajarSubirCalorias(intMb);

            calcularImc();

            enviarDatos();
        }
    }

    public double convertirMtrCm(int cm)
    {
        double res = 0;
        double temp = 0.0;
        temp = cm/100.00;
        res = (temp*temp);
        return res;

    }

    public void calcularImc()
    {
        double res = intPeso/(convertirMtrCm(intEstatura));
        intImc = (int)res;
    }

    public void actividadSeleccionada()
    {
        switch(actividad)
        {
            // Calorias para mantener el peso
            case "Sedentario":
                double c = intMb * 1.2;
                intCmp = (int)c;
                break;

            case "Actividad Moderada":
                double d = intMb * 1.375;
                intCmp = (int) d;
                break;

            case "Actividad Intensa":
                double a = intMb * 1.55;
                intCmp = (int) a;
                break;

            case "Actividad Muy Intensa":
                double b = intMb * 1.725;
                intCmp = (int) b;
                break;
        }
    }

    public void obtenerFechaNac()
    {
        switch(stringMes)
        {
            case "Enero":
                intMes = 1;
                 break;

            case "Febrero":
                intMes = 2;
                break;

            case "Marzo":
                intMes = 3;
            break;

            case "Abril":
                intMes = 4;
                break;

            case "Mayo":
                intMes = 5;
                break;

            case "Junio":
                intMes = 6;
                break;

            case "Julio":
                intMes = 7;
                break;

            case "Agosto":
                intMes = 8;
                break;

            case "Septiembre":
                intMes = 9;
                break;

            case "Octubre":
                intMes = 10;
                break;

            case "Noviembre":
                intMes = 11;
                break;

            case "Diciembre":
                intMes = 12;
                break;
        }
    }

    public void bajarSubirCalorias(int mb)
    {
        // Calorias para bajar de peso
        intCb = (int) intCmp - (intCmp *15/100);
        // Calorias para subir de peso
        intCs = (int) intCmp + (intCmp *15/100);
    }

    public void enviarDatos()
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        intent.putExtra("alt", intEstatura);
        intent.putExtra("pes", intPeso);
//      GRASA
        intent.putExtra("imc", intImc);
        intent.putExtra("tmb", intMb);
        intent.putExtra("cmp", intCmp);
        intent.putExtra("csp", intCs);
        intent.putExtra("cbp", intCb);

        startActivity(intent);
    }

    public void fechaActual()
    {
        String dia, mes, año;
        Date fechaActual = new Date();
        SimpleDateFormat dDia = new SimpleDateFormat("dd");
        SimpleDateFormat dMes = new SimpleDateFormat("MM");
        SimpleDateFormat dAño = new SimpleDateFormat("yyyy");
        dia = dDia.format(fechaActual);
        mes = dMes.format(fechaActual);
        año = dAño.format(fechaActual);

        Toast.makeText (this, "Fecha Actual: "+dia+"-"+mes+"-"+año,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onClick(View view)
    {
        if(view == botonCalcular)
        {
            Calcular();
        }

        if(view == botonCalculadora)
        {
            Intent intent = new Intent(getApplicationContext(), GrasaActivity.class);
            startActivity(intent);
        }

        if(view == botonCalendario)
        {
            fechaActual();
        }

    }
}
