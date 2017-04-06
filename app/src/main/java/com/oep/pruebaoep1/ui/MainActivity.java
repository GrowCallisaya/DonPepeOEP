package com.oep.pruebaoep1.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.oep.pruebaoep1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
        , AdapterView.OnItemSelectedListener {
    EditText txtUsuario;
    Spinner txtDepartamentos;
    Button btnEmpezar;
    String datos [] = new String[]{"La Paz","Santa","Cocha"};
    String depa_escogido="";
    String nombre_ingresado="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciamos a los Elementos
        txtUsuario = (EditText) findViewById(R.id.et_nombre);
        btnEmpezar = (Button) findViewById(R.id.btn_empezar);
        txtDepartamentos = (Spinner) findViewById(R.id.sp_departamento);

        //Configuramos el Spinner: Poner Datos
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,datos);
        adaptador.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        txtDepartamentos.setAdapter(adaptador);
        txtDepartamentos.setOnItemSelectedListener(this);
        //Configuramos el Botón: Enviar datos al hacer clic
        btnEmpezar.setOnClickListener(this);

    }


    @Override
    public void onClick(View vista) {
        //Configuramos el Texto: Obtener Texto
        nombre_ingresado = txtUsuario.getText().toString();

        //Mostramos el MensajeGROE
//        Toast.makeText(this, "Hola "+nombre_ingresado+" Tu eres de "+ depa_escogido, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, SupermercadoActivity.class);
        intent.putExtra("NOMBRE_KEY",nombre_ingresado);
        intent.putExtra("CIUDAD_KEY",depa_escogido);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Obtenemos el valor de el Departamento que escogio
        depa_escogido = parent.getItemAtPosition(position)+"";
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
