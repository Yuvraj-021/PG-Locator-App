package com.dd.pgadmin;

class PgDetailsmodel {

    String id,pgname,description,pgrent,ac_noac,address,boys_girls, bhksize,no_of_beds,pgimage,gym_nogym;
    String food_nofood,fur_nofur,rent,deposit,contactno,userid;


    public PgDetailsmodel(String id, String pgname, String description, String pgrent, String ac_noac, String address, String boys_girls, String bhksize, String no_of_beds, String pgimage, String gym_nogym, String food_nofood, String fur_nofur, String rent, String deposit, String contactno,String userid) {
        this.id = id;
        this.pgname = pgname;
        this.description = description;
        this.pgrent = pgrent;
        this.ac_noac = ac_noac;
        this.address = address;
        this.boys_girls = boys_girls;
        this.bhksize = bhksize;
        this.no_of_beds = no_of_beds;
        this.pgimage = pgimage;
        this.gym_nogym = gym_nogym;
        this.food_nofood = food_nofood;
        this.fur_nofur = fur_nofur;
        this.rent = rent;
        this.deposit = deposit;
        this.contactno = contactno;
        this.userid = userid;
    }

    public PgDetailsmodel() {
    }


    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPgimage() {
        return pgimage;
    }

    public void setPgimage(String pgimage) {
        this.pgimage = pgimage;
    }

    public String getGym_nogym() {
        return gym_nogym;
    }

    public void setGym_nogym(String gym_nogym) {
        this.gym_nogym = gym_nogym;
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

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
