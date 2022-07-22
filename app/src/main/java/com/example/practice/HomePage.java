package com.example.practice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class HomePage extends AppCompatActivity {
    ArrayList<ContactModel> arrContacts = new ArrayList<>();
    RecyclerView recyclerView ;
    RecyclerContactAdapter adapter;
    FloatingActionButton btnOpenDialog ;
    String dateAndTime ;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        databaseHelper= new DatabaseHelper(this);
        arrContacts=databaseHelper.getTasks();

        recyclerView =findViewById(R.id.recyclerview);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);



        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(HomePage.this);
                dialog.setContentView(R.layout.update);

                EditText edtName = dialog.findViewById(R.id.edtName);
                //EditText edtDate = dialog.findViewById(R.id.edtDate);
                EditText edtSystolic = dialog.findViewById(R.id.edtSys);
                EditText edtDiastolic = dialog.findViewById(R.id.edtDias);
                EditText edtHeart = dialog.findViewById(R.id.edtHeart);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String name = edtName.getText().toString();
                        String systolic = edtSystolic.getText().toString();
                        String diastolic = edtDiastolic.getText().toString();
                        String heart = edtHeart.getText().toString();


                        Calendar calendar = calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy,   HH:mm:ss");
                        dateAndTime = simpleDateFormat.format(calendar.getTime());

                        if(Integer.valueOf(systolic)>200)
                        {
                            Toast.makeText(HomePage.this,"Invalid!! systolic must be between 70 and 180",Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.valueOf(systolic)<60)
                        {
                            Toast.makeText(HomePage.this,"Invalid!! systolic must be between 70 and 180",Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.valueOf(diastolic)<20)
                        {
                            Toast.makeText(HomePage.this,"Invalid!! diastolic must be between 20 and 120",Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.valueOf(diastolic)>100)
                        {
                            Toast.makeText(HomePage.this,"Invalid!! diastolic must be between 20 and 120",Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.valueOf(heart)<50)
                        {
                            Toast.makeText(HomePage.this,"Invalid!! heart rate must be between 50 and 100",Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.valueOf(heart)>100)
                        {
                            Toast.makeText(HomePage.this,"Invalid!! heart rate must be between 50 and 100",Toast.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(name))
                        {
                            Toast.makeText(HomePage.this,"Please enter your name",Toast.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(systolic))
                        {
                            Toast.makeText(HomePage.this,"Please enter systolic pressure",Toast.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(diastolic))
                        {
                            Toast.makeText(HomePage.this,"Please enter diastolic pressure",Toast.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(heart))
                        {
                            Toast.makeText(HomePage.this,"Please enter the heart rate",Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            arrContacts.add(new ContactModel(name,dateAndTime,systolic,diastolic,heart));
                            adapter.notifyItemInserted(arrContacts.size()-1);

                            databaseHelper.savetasks(arrContacts);
                            recyclerView.scrollToPosition(arrContacts.size()-1);

                            dialog.dismiss();
                        }

                    }
                });


                dialog.show();
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new RecyclerContactAdapter(this,arrContacts);
        recyclerView.setAdapter(adapter);
    }
}