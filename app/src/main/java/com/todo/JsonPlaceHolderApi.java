package com.todo;

import com.todo.Tarea;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("todos")
    Call<ArrayList<Tarea>> getTodo();
}
