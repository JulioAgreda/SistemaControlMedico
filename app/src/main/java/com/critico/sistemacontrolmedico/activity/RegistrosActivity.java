package com.critico.sistemacontrolmedico.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.critico.sistemacontrolmedico.MainActivity;
import com.critico.sistemacontrolmedico.R;

import java.security.Principal;

public class RegistrosActivity extends AppCompatActivity
{
    private Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        registrar = (Button)findViewById(R.id.btnRegistrar);


        registrar.setOnClickListener(new View.OnClickListener()
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
