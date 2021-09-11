package com.dd.pglocator.nearbyScreen;

class PgdetailsModel {

    String id,pgname,pgrent,ac_noac,address,boys_girls, bhksize,no_of_beds,contact_no,dec,deposit,pgimage;
    String food_nofood, fur_nofur, gym_nogym,userid;

    public PgdetailsModel(String id, String pgname, String pgrent, String ac_noac, String address, String boys_girls, String bhksize, String no_of_beds, String contact_no, String dec, String deposit, String food_nofood, String fur_nofur, String gym_nogym,String pgimage,String userid) {
        this.id = id;
        this.pgname = pgname;
        this.pgrent = pgrent;
        this.ac_noac = ac_noac;
        this.address = address;
        this.boys_girls = boys_girls;
        this.bhksize = bhksize;
        this.no_of_beds = no_of_beds;
        this.contact_no = contact_no;
        this.dec = dec;
        this.deposit = deposit;
        this.food_nofood = food_nofood;
        this.fur_nofur = fur_nofur;
        this.gym_nogym = gym_nogym;
        this.pgimage = pgimage;
        this.userid = userid;
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

    public String getPgrent() {
        return pgrent;
    }

    public void setPgrent(String pgrent) {
        this.pgrent = pgrent;
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

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getFood_nofood() {
        return food_nofood;
    }

    public void setFood_nofood(String food_nofood) {
        this.food_nofood = food_nofood;
    }

    public String getFur_nofur() {
        return fur_nofur;
    }

    public void setFur_nofur(String fur_nofur) {
        this.fur_nofur = fur_nofur;
    }

    public String getGym_nogym() {
        return gym_nogym;
    }

    public void setGym_nogym(String gym_nogym) {
        this.gym_nogym = gym_nogym;
    }

    public String getPgimage() {
        return pgimage;
    }

    public void setPgimage(String pgimage) {
        this.pgimage = pgimage;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
