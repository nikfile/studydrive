package com.studydrive.studydrive4;

public class ListItem {

    private String head;
    private String desc;

    private int num;

    public ListItem(String head, String desc, int num) {
        this.head = head;
        this.desc = desc;
        this.num = num;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public int getNum() {
        return num;
    }
}
