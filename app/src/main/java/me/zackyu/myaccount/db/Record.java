package me.zackyu.myaccount.db;

import java.util.Date;

public class Record {

    private int id;
    private String type;
    private String account;
    private Double amount;
    private Date crttime;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCrttime() {
        return crttime;
    }

    public void setCrttime(Date crttime) {
        this.crttime = crttime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
