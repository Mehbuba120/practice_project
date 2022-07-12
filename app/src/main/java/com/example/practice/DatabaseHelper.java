package com.example.practice;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DatabaseHelper {
    private final SharedPreferences taskDatabase;
    private final Gson gson;
    public DatabaseHelper(Context context)
    {
        taskDatabase= context.getSharedPreferences("taskDatabase",Context.MODE_PRIVATE);
        gson= new Gson();
    }

    public void savetasks(ArrayList<ContactModel> tasks){
        SharedPreferences.Editor editor = taskDatabase.edit();
        editor.putString("tasks",gson.toJson(tasks));
        editor.apply();

    }
    public ArrayList<ContactModel> getTasks(){
        String taskString = taskDatabase.getString("tasks",null);

        Type taskListType= new TypeToken<ArrayList<ContactModel>>(){}.getType();
        ArrayList<ContactModel> tasks= gson.fromJson(taskString,taskListType);
        if(tasks!=null) return tasks;
        else return new ArrayList<>();

    }
}
