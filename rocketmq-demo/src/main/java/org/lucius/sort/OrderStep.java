package org.lucius.sort;

import java.io.Serializable;

/**
 * @Author: Lucius
 * @Date: 2022-04-04 11:01
 */
public class OrderStep implements Serializable {
    private long id;
    private String desc;

    public OrderStep() {

    }

    public OrderStep(long id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "OrderStep{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
