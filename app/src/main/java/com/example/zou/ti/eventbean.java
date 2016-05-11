package com.example.zou.ti;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zou on 2016/4/2.
 */
public class eventbean {



    public String event;

    public eventbean( boolean over,String event, String time,int ID) {
        this.event = event;
        this.time = time;
        this.over = over;
        this.ID=ID;
    }

    public String time;

    public boolean over;

    int ID;


}
