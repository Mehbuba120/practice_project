package com.example.practice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.ContactModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {
    Context context;
    ArrayList<ContactModel> arrContacts ;



        //arrContacts=databaseHelper.getTasks();


    RecyclerContactAdapter(Context context , ArrayList<ContactModel> arrContacts){
        this.context = context ;
        this.arrContacts = arrContacts;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.items , parent ,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ContactModel model = (ContactModel) arrContacts.get(position);
        holder.txtname.setText(model.name);
        holder.txttime.setText(model.time);
        holder.txtsystolic.setText(model.systolic);
        holder.txtdiastolic.setText(model.diastolic);
        holder.txtheart.setText(model.heart);



        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.update);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtSystolic = dialog.findViewById(R.id.edtSys);
                EditText edtDiastolic = dialog.findViewById(R.id.edtDias);
                EditText edtHeart = dialog.findViewById(R.id.edtHeart);
                TextView txtTitle = dialog.findViewById(R.id.txtTitle);

                Button btnAction = dialog.findViewById(R.id.btnAction);

                txtTitle.setText("UPDATE INFO");

                btnAction.setText("UPDATE");


                edtName.setText((arrContacts.get(position)).name);
                //edtDate.setText((arrContacts.get(position)).time);
                edtSystolic.setText((arrContacts.get(position)).systolic);
                edtDiastolic.setText((arrContacts.get(position).diastolic));
                edtHeart.setText((arrContacts.get(position).heart));

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = edtName.getText().toString();
                        String systolic = edtSystolic.getText().toString();
                        String diastolic =edtDiastolic.getText().toString();
                        String heart =edtHeart.getText().toString();

                        Calendar calendar = calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
                        String dateAndTime = simpleDateFormat.format(calendar.getTime());


                        if(Integer.valueOf(systolic)>200)
                        {
                            Toast.makeText(context,"Invalid!! systolic must be between 70 and 180",Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.valueOf(systolic)<60)
                        {
                            Toast.makeText(context,"Invalid!! systolic must be between 70 and 180",Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.valueOf(diastolic)<20)
                        {
                            Toast.makeText(context,"Invalid!! diastolic must be between 20 and 120",Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.valueOf(diastolic)>100)
                        {
                            Toast.makeText(context,"Invalid!! diastolic must be between 20 and 120",Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.valueOf(heart)<50)
                        {
                            Toast.makeText(context,"Invalid!! heart rate must be between 50 and 100",Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.valueOf(heart)>100)
                        {
                            Toast.makeText(context,"Invalid!! heart rate must be between 50 and 100",Toast.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(name))
                        {
                            Toast.makeText(context,"Please enter your name",Toast.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(systolic))
                        {
                            Toast.makeText(context,"Please enter systolic pressure",Toast.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(diastolic))
                        {
                            Toast.makeText(context,"Please enter the diastolic pressure",Toast.LENGTH_SHORT).show();                       }
                        else if (TextUtils.isEmpty(heart))
                        {
                            Toast.makeText(context,"Please enter the heart rate",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            arrContacts.set(position, new ContactModel(name , dateAndTime , systolic,diastolic,heart));
                            notifyItemChanged(position);
                            DatabaseHelper databaseHelper=new DatabaseHelper(context);
                            databaseHelper.savetasks(arrContacts);


                            dialog.dismiss();
                        }



                    }
                });

                dialog.show();
            }
        });

        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("DELETE")
                        .setMessage("Are you sure want to delete ?").setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                arrContacts.remove(position);
                                notifyItemRemoved(position);
                                notifyDataSetChanged();
                                DatabaseHelper databaseHelper=new DatabaseHelper(context);
                                databaseHelper.savetasks(arrContacts);

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtname , txttime , txtsystolic , txtdiastolic , txtheart;
        LinearLayout llrow ;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            txtname = itemView.findViewById(R.id.name);
            txttime = itemView.findViewById(R.id.time);
            txtsystolic = itemView.findViewById(R.id.systolic);
            txtdiastolic = itemView.findViewById(R.id.diastolic);
            txtheart = itemView.findViewById(R.id.heart);
            llrow = itemView.findViewById(R.id.llrow);
        }
    }

}