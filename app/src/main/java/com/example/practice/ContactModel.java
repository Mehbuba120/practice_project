package com.example.practice;

public class ContactModel {
/*
    Calendar calendar = calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String dateAndTime = simpleDateFormat.format(calendar.getTime());
*/

    String name , time ;
    int   systolic , diastolic , heart;

    public ContactModel(String name , String time){
        this.name = name ;
        this.time = time ;
    }

    public ContactModel(String name , String time , int systolic , int diastolic , int heart ){
        this.name = name ;
        this.time = time ;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heart = heart ;
    }

}
