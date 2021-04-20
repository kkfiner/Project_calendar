package com.example.project_calendar.base;

import org.litepal.crud.DataSupport;

import java.io.Serializable;


public class QQBean extends DataSupport implements Serializable {

    private String name1;
    private String name2;
    private String yueri;
    private String times;

    public String getYueri() {
        return yueri;
    }

    public void setYueri(String yueri) {
        this.yueri = yueri;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }


    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
