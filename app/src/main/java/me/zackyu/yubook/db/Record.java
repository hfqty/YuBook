package me.zackyu.yubook.db;

import java.util.Date;

public class Record {

    private int id;
    private String source;
    private String type;
    private String account;
    private Double amount;
    private Date crttime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String type) {
        this.source = type;
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

    public String getType() {
        return type;
    }

    public void setType(String remark) {
        this.type = remark;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", account='" + account + '\'' +
                ", amount=" + amount +
                ", crttime=" + crttime +
                '}';
    }
}
