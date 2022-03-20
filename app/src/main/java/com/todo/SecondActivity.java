package com.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private Button anadir;
    private EditText texto;
    private ArrayList<Tarea> tareas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        tareas = (ArrayList<Tarea>) getIntent().getSerializableExtra("lista_tareas");

        anadir = findViewById(R.id.boton_añadir);
        texto = findViewById(R.id.tarea_nueva);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(texto.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Casi, pero no puedo añadir una tarea vacia.", Toast.LENGTH_SHORT).show();
                } else{
                    tareas.add(new Tarea(texto.getText().toString()));
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("lista_tareas",tareas);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}