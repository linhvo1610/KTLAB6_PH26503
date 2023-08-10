package com.example.ktlab6_ph26503.DTO;

public class SP {
    private int id;
    private String content;
    private String date;
    private int price;
    public static final String TB_NAME = "SP";
    public static final String COL_NAME_ID = "ID";
    public static final String COL_NAME_content = "CONTENT";
    public static final String COL_NAME_DATE = "DATE";
    public static final String COL_NAME_PRICE = "PRICE";

    public SP(int id, String content, String date, int price) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.price = price;
    }

    public SP() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}