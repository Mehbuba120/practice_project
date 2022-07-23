package com.example.practice;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

public class DataBaseTest {
    Context context;
    DataBaseTest(Context context)
    {
        this.context=context;
    }

    @Test
    public void testDatabase()
    {
        ArrayList<ContactModel> arrayList=new ArrayList<>();
        DatabaseHelper databaseHelper= new DatabaseHelper(context);
        arrayList=databaseHelper.getTasks();
        ContactModel data1= new ContactModel("esha","1-12-2022, 12:12:12","95","70","70");
        arrayList.add(data1);
        databaseHelper.savetasks(arrayList);
        assertTrue (databaseHelper.getTasks().contains(arrayList));




    }



}
