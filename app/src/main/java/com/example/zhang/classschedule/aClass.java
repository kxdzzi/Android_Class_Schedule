package com.example.zhang.classschedule;

public class aClass {
    private String className;
    private String classRoom;
    private Integer positionX;
    private Integer positionY;

    private int weeks;
    private boolean ifSingal;
    private boolean ifDouble;

    public aClass() {
        this.className = "";
        this.classRoom = "";
        this.positionY = -1;
        this.positionX = -1;

    }

    public String getInfo() {
        String info = this.className + "\n" + this.classRoom;
        return info;
    }

    public void setClassName(String name) {
        this.className = name;
    }

    public void setClassRoom(String room) {
        this.classRoom = room;
    }

    public void setWeeks(int weeksNum) {
        this.weeks = weeksNum;
    }

    public void setPositionX(int poX) {
        this.positionX = poX;
    }

    public void setPositionY(int poY) {
        this.positionY = poY;
    }

    public String getClassName() {
        return this.className;
    }

    public String getClassRoom() {
        return this.classRoom;
    }
    public int getWeeks(){
        return this.weeks;
    }

    public int getPoX(){
        return this.positionX;
    }
    public  int getPoY(){
        return this.positionY;
    }

    public void showInfo() {
        String Msg = "课程信息为\n课程名：" + this.className + "\n"
                + "所在教室：" + this.classRoom + "\n"
                + "持续周数：" + this.weeks + "\n"
                + "所在位置X:" + this.positionX + "\n"
                + "所在位置Y:" + this.positionY;
        System.out.println(Msg);

    }
}
