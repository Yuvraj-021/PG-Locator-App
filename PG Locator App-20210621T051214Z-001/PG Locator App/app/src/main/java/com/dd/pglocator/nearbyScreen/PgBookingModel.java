package com.dd.pglocator.nearbyScreen;

public class PgBookingModel {
    String pgid,pgname,checkindate,checkoutdate,noofguests,noofmonths,price,customername,cust_mobileno,cust_email;


    public PgBookingModel() {
    }

    public PgBookingModel(String pgid, String pgname, String checkindate, String checkoutdate, String noofguests, String noofmonths, String price,String customername, String cust_mobileno, String cust_email) {
        this.pgid = pgid;
        this.pgname = pgname;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
        this.noofguests = noofguests;
        this.noofmonths = noofmonths;
        this.price = price;
        this.customername = customername;
        this.cust_mobileno = cust_mobileno;
        this.cust_email = cust_email;
    }

    public String getPgid() {
        return pgid;
    }

    public void setPgid(String pgid) {
        this.pgid = pgid;
    }

    public String getPgname() {
        return pgname;
    }

    public void setPgname(String pgname) {
        this.pgname = pgname;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public String getNoofguests() {
        return noofguests;
    }

    public void setNoofguests(String noofguests) {
        this.noofguests = noofguests;
    }

    public String getNoofmonths() {
        return noofmonths;
    }

    public void setNoofmonths(String noofmonths) {
        this.noofmonths = noofmonths;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCust_mobileno() {
        return cust_mobileno;
    }

    public void setCust_mobileno(String cust_mobileno) {
        this.cust_mobileno = cust_mobileno;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }
}
