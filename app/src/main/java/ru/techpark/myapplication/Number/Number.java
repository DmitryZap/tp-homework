
package ru.techpark.myapplication.Number;

import android.graphics.Color;

public class Number {
    private Integer value;

    public void setColor(Integer color) {
        this.color = color;
    }

    private Integer color;

    public Number(Integer value) {
        this.value = value;
//        this.color = (value % 2 == 0 ? android.R.color.holo_red_dark : android.R.color.holo_blue_dark);\
        // TODO: Заменить цвета
        this.color = (value % 2 == 0 ? Color.RED : Color.BLUE);
    }

    public Integer getNumber() {
        return value;
    }

    public void setNumber(Integer value) {
        this.value = value;
    }

    public Integer getColor() {
        return this.color;
    }
}
