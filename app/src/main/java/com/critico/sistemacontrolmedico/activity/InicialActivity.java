package com.critico.sistemacontrolmedico.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.critico.sistemacontrolmedico.MainActivity;
import com.critico.sistemacontrolmedico.R;

public class InicialActivity extends AppCompatActivity
{

    private Spinner listaActividades;
    private ArrayAdapter sp;
    private String actividad;

    private Button calcular;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        //Agregando la lista al Spinner
        listaActividades = (Spinner) findViewById(R.id.spActividad);
        sp = ArrayAdapter.createFromResource(this, R.array.actividades, android.R.layout.simple_spinner_item);
        sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        calcular = (Button)findViewById(R.id.btnCalcular);



        calcular.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });






    }
}
