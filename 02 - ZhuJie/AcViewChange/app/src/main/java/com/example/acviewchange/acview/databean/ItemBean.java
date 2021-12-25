package com.example.acviewchange.acview.databean;

public class ItemBean {

    private String mid;

    private String content;

    public ItemBean() {
    }

    public ItemBean(int id) {
        content = id + "";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
