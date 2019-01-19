package com.edebtor.oclimb.edebtor.Model;

public class payment_de {

    private String id;
    private String userid;
    private String dailyPay;
    private String payFor;
    private String addAmount;
    private String paidAmount;
    private String cid;



    public payment_de(String id, String userid, String dailyPay, String payFor, String addAmount, String paidAmount, String cid) {
        this.setId(id);
        this.setUserid(userid);
        this.setDailyPay(dailyPay);
        this.setPayFor(payFor);
        this.setAddAmount(addAmount);
        this.setPaidAmount(paidAmount);
        this.setCid(cid);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDailyPay() {
        return dailyPay;
    }

    public void setDailyPay(String dailyPay) {
        this.dailyPay = dailyPay;
    }

    public String getPayFor() {
        return payFor;
    }

    public void setPayFor(String payFor) {
        this.payFor = payFor;
    }

    public String getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(String addAmount) {
        this.addAmount = addAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

}
