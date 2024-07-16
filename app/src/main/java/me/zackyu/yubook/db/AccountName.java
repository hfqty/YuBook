package me.zackyu.yubook.db;

public class AccountName {


    private int id;

    private String aname;


    public AccountName() {
    }

    public AccountName(int id, String aname) {
        this.id = id;
        this.aname = aname;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }


    @Override
    public String toString() {
        return "AccountName{" +
                "id=" + id +
                ", aname='" + aname + '\'' +
                '}';
    }
}
