package com.dd.pglocator;

class Customer_model {
    String customername,email,password,con_password,occupation,address,mobileno;

    public Customer_model(String customername, String email, String password, String con_password, String occupation, String address, String mobileno) {
        this.customername = customername;
        this.email = email;
        this.password = password;
        this.con_password = con_password;
        this.occupation = occupation;
        this.address = address;
        this.mobileno = mobileno;
    }

    public Customer_model() {
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCon_password() {
        return con_password;
    }

    public void setCon_password(String con_password) {
        this.con_password = con_password;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
}
