package com.example.practice;

public class ContactModel  implements Comparable<ContactModel>{
/*
    Calendar calendar = calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String dateAndTime = simpleDateFormat.format(calendar.getTime());
*/

    String name , time , systolic , diastolic , heart;

    public ContactModel(String name , String time){
        this.name = name ;
        this.time = time ;
    }

    public ContactModel(String name , String time , String systolic , String diastolic , String heart ){
        this.name = name ;
        this.time = time ;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heart = heart ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    @Override
    public int compareTo(ContactModel record) {
        return this.systolic.compareTo(record.getSystolic());
    }

}
