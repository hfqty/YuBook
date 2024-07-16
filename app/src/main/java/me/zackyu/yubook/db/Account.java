package me.zackyu.yubook.db;

public class Account {


    private int id;

    private String zhanghao;


    public Account() {
    }

    public Account(int id, String zhanghao) {
        this.id = id;
        this.zhanghao = zhanghao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZhanghao() {
        return zhanghao;
    }

    public void setZhanghao(String zhanghao) {
        this.zhanghao = zhanghao;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", zhanghao='" + zhanghao + '\'' +
                '}';
    }
}
