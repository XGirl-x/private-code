package org.example.work;

import org.example.annotation.FiledAnnotation;
import org.example.annotation.MethodAnnotation;
import org.example.annotation.TypeAnnotation;

@TypeAnnotation(value = "doWork")
public class Worker {

    private Integer myInt;

    @FiledAnnotation(value = "myField")
    private String myField = "";

    @MethodAnnotation
    public String getDefaultInfo() {
        return "do method getDefaultInfo";
    }

    @MethodAnnotation(name = "谷歌", url = "https://www.google.cn/")
    public String getDefineInfo() {
        return "do method getDefineInfo";
    }

    public Integer getMyInt() {
        return myInt;
    }

    public void setMyInt(Integer myInt) {
        this.myInt = myInt;
    }

    public String getMyField() {
        return myField;
    }

    public void setMyField(String myField) {
        this.myField = myField;
    }
}
