package com.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LsTodoList";
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

    public void cargarSpref() {
        SharedPreferences pref = getSharedPreferences("json", Context.MODE_PRIVATE);

        String json_s = pref.getString("json_s","no");
        if (!json_s.equals("no")){
            JsonArray jarray = JsonParser.parseString(json_s).getAsJsonArray();
            tareas.clear();
            for (JsonElement element: jarray) {
                tareas.add(new Gson().fromJson(element, Tarea.class));
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarSpref();

        if(getIntent().getSerializableExtra("lista_tareas") != null){
            this.tareas.clear();
            this.tareas.addAll((ArrayList<Tarea>)getIntent().getSerializableExtra("lista_tareas"));
        }
        FloatingActionButton nueva_tarea = findViewById(R.id.floating_button);

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

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences pref = getSharedPreferences("json", Context.MODE_PRIVATE);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json_s = gson.toJson(tareas);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("json_s");
        editor.putString("json_s",json_s);
        Log.i(TAG, "onStop: Saved!");
        editor.apply();
    }
}