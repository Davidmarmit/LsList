package com.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Tarea> tareas = new ArrayList<Tarea>(Arrays.asList(
            new Tarea("Sacar el perro."),
            new Tarea("Comprar el pan."),
            new Tarea("Revisar el correo de la Salle."),
            new Tarea("Preparar reuniones del dia"),
            new Tarea("Hacer ejercicio.")
    ));
    private FloatingActionButton nueva_tarea;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().getSerializableExtra("lista_tareas") != null){
            this.tareas.clear();
            this.tareas.addAll((ArrayList<Tarea>)getIntent().getSerializableExtra("lista_tareas"));
        }
        nueva_tarea = findViewById(R.id.floating_button);

        nueva_tarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("lista_tareas",tareas);
                startActivity(intent);
            }
        });

        // Getting reference of recyclerView
        recyclerView =findViewById(R.id.recycler_view);

        // Setting the layout as linear
        // layout for vertical orientation
        this.layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Sending reference and data to Adapter
        this.adapter = new TareaAdapter(tareas);

        // Setting Adapter to RecyclerView
        recyclerView.setAdapter(adapter);
    }

    public void openSecondActivity(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}