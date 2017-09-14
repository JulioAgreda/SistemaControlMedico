package com.critico.sistemacontrolmedico;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.critico.sistemacontrolmedico.activity.InicialActivity;
import com.critico.sistemacontrolmedico.activity.RegistrosActivity;

import java.security.Principal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView tmb, cmp, cbp, csp, imc, pg, mg, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);tmb = (TextView)findViewById(R.id.txtTmb);

        cmp = (TextView)findViewById(R.id.txtCmp);
        cbp = (TextView)findViewById(R.id.txtCbp);
        csp = (TextView)findViewById(R.id.txtCsp);
        imc = (TextView)findViewById(R.id.txtImc);
        pg = (TextView)findViewById(R.id.txtPg);
        mg = (TextView)findViewById(R.id.txtMdg);
        msg = (TextView)findViewById(R.id.txtMsg);

        int intTmb = getIntent().getIntExtra("tmb", 0);
        int intCmp = getIntent().getIntExtra("cmp", 0);
        int intCbp = getIntent().getIntExtra("cbp", 0);
        int intCsp = getIntent().getIntExtra("csp", 0);
        int intImc = getIntent().getIntExtra("imc", 0);
        int intPg = getIntent().getIntExtra("pgrasa", 0);
        int intMg = getIntent().getIntExtra("mg", 0);
        int intMsg = getIntent().getIntExtra("msg", 0);

        tmb.setText(""+intTmb);
        cmp.setText(""+intCmp);
        cbp.setText(""+intCbp);
        csp.setText(""+intCsp);
        cbp.setText(""+intImc+" %");
        csp.setText(""+intPg);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.fab)
        {
            Intent intent = new Intent(getApplicationContext(), InicialActivity.class);
            startActivity(intent);
        }
    }


}
