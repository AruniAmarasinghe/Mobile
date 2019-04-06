package com.edebtor.oclimb.edebtor.Model;

public class credit_report {
    private String id;
    private String crdit;
    private String de_name;
    private String de_date;

    public credit_report(String id, String crdit, String de_name, String de_date) {
        this.id = id;
        this.crdit = crdit;
        this.de_name = de_name;
        this.de_date = de_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrdit() {
        return crdit;
    }

    public void setCrdit(String crdit) {
        this.crdit = crdit;
    }

    public String getDe_name() {
        return de_name;
    }

    public void setDe_name(String de_name) {
        this.de_name = de_name;
    }

    public String getDe_date() {
        return de_date;
    }

    public void setDe_date(String de_date) {
        this.de_date = de_date;
    }
}
