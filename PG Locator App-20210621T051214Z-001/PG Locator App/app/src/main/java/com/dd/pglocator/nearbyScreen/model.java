package com.dd.pglocator.nearbyScreen;

public class model {
    String id,pgname,rent,ac_noac,address,boys_girls, bhksize,no_of_beds,pgimage,userid;
    //Integer id;

    public model(){

    }

    public model(String id, String pgname, String rent, String ac_noac, String address, String boys_girls, String bhksize, String no_of_beds, String pgimage,String userid) {
        this.id = id;
        this.pgname = pgname;
        this.rent = rent;
        this.ac_noac = ac_noac;
        this.address = address;
        this.boys_girls = boys_girls;
        this.bhksize = bhksize;
        this.no_of_beds = no_of_beds;
        this.pgimage = pgimage;
        this.userid = userid;

    }

    public String getPgimage() {
        return pgimage;
    }

    public void setPgimage(String pgimage) {
        this.pgimage = pgimage;
    }

    public String getBhksize() {
        return bhksize;
    }

    public void setBhksize(String bhksize) {
        this.bhksize = bhksize;
    }

    public String getNo_of_beds() {
        return no_of_beds;
    }

    public void setNo_of_beds(String no_of_beds) {
        this.no_of_beds = no_of_beds;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPgname() {
        return pgname;
    }

    public void setPgname(String pgname) {
        this.pgname = pgname;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getAc_noac() {
        return ac_noac;
    }

    public void setAc_noac(String ac_noac) {
        this.ac_noac = ac_noac;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBoys_girls() {
        return boys_girls;
    }

    public void setBoys_girls(String boys_girls) {
        this.boys_girls = boys_girls;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}


