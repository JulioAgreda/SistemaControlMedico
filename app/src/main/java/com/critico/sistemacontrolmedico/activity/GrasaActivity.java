package com.critico.sistemacontrolmedico.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.critico.sistemacontrolmedico.R;

public class GrasaActivity extends AppCompatActivity
{

    private Button botonCalcular;

    private TextView cuello, cintura, cadera;
    private int intCuello, intCintura, intCadera, intGrasa;
    private double varTamaño;
    private String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grasa);

        cintura = (TextView)findViewById(R.id.txtCintura);
        cuello = (TextView)findViewById(R.id.txtCuello);
        cadera = (TextView)findViewById(R.id.txtCadera);

        botonCalcular = (Button)findViewById(R.id.btnResultado);

        botonCalcular.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                calcular();

                Intent intent = new Intent(getApplicationContext(), InicialActivity.class);
                startActivity(intent);
            }
        });

    }

    public void calcular()
    {
        intCintura = Integer.valueOf(cintura.getText().toString());
        intCuello = Integer.valueOf(cuello.getText().toString());
        intCadera = Integer.valueOf(cadera.getText().toString());

        sexo = "hombre";
        varTamaño = 1.73;

        if(sexo == "hombre")
        {
            porcentajeGrasaHombres();
        }

        if(sexo == "mujer")
        {
            porcentajeGrasaMujer();
        }
    }

    public void porcentajeGrasaHombres()
    {
        double res = 495/(1.0324-0.19077*(Math.log(intCintura-intCuello))+0.15456*(Math.log(varTamaño)))-450;
        intGrasa = (int)res;
        Toast.makeText (this, "&  de Grasa: "+res,Toast.LENGTH_LONG).show();
    }

    public void porcentajeGrasaMujer()
    {
        double res = 495/(1.29579-0.35004*(Math.log(intCintura+intCadera-intCuello))+0.22100*(Math.log(varTamaño)))-450;
        intGrasa = (int)res;
        Toast.makeText (this, "&  de Grasa: "+res,Toast.LENGTH_LONG).show();
    }
}
