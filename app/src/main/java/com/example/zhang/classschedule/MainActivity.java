package com.example.zhang.classschedule;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner weekShow;
    private GridView mainGrid;
    private List<String> dataList;
    private Context nowContext;

    private String[][] contents;  //显示在课程格子中的内容
    private GridAdapter myAdapter;  //用于显示课程的适配器
    private Dialog addClassWindow;
//    private

    public dateBase myClasses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //启动主界面时要干的事
        weekShow = (Spinner) findViewById(R.id.switchWeek);
        mainGrid = (GridView) findViewById(R.id.classTable);
        nowContext = this;

        initGridView();
        initSpinner();

//        dialogEvent();

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e("屏幕焦点", "屏幕失去焦点！");
//        initContent();
        initGridView();  //屏幕失去焦点时刷新gridView
    }

    //初始化Spinner
    protected void initSpinner() {
        Log.e("初始化", "Spinner");
        dataList = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            dataList.add("第" + i + "周");
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dataList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekShow.setAdapter(spinnerAdapter);


    }


    protected void initContent() {
        contents = new String[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                contents[i][j] = "";
            }
        }

        myClasses = new dateBase(this);   //新建数据库
        ArrayList<aClass> tempList_class;
        tempList_class = myClasses.getAll();
//        System.out.println("查询到"+tempList_class.size()+"条记录！");
        for (aClass tempClass : tempList_class) {
            tempClass.showInfo();
            if (tempClass.getPoX() >= 0 && tempClass.getPoY() >= 0) {
                contents[tempClass.getPoX()][tempClass.getPoY()] = tempClass.getInfo();
            }
        }
        System.out.println(contents[0][1]);
    }

    //读取数据库，初始化gridView
    protected void initGridView() {

//        contents[0][0] = "现代测试技术\nB211";
//        contents[1][0] = "微机原理及应用\nE203";
//        contents[2][0] = "电磁场理论\nA212";
//        contents[3][0] = "传感器电子测量A\nC309";
//        contents[4][0] = "";
//        contents[5][0] = "";
//        contents[0][1] = "数据结构与算法\nB211";
//        contents[1][1] = "";
//        contents[2][1] = "面向对象程序设计\nA309";
//        contents[3][1] = "面向对象程序设计\nA309";
//        contents[4][1] = "";
//        contents[5][1] = "";
//        contents[0][2] = "微机原理及应用\nE203";
//        contents[1][2] = "电磁场理论\nA212";
//        contents[2][2] = "现代测试技术\nB211";
//        contents[3][2] = "";
//        contents[4][2] = "";
//        contents[5][2] = "";
//        contents[0][3] = "面向对象程序设计\nA309";
//        contents[1][3] = "传感器电子测量A\nC309";
//        contents[2][3] = "";
//        contents[3][3] = "";
//        contents[4][3] = "";
//        contents[5][3] = "";
//        contents[0][4] = "数据结构与算法\nB211";
//        contents[1][4] = "微机原理及应用\nE203";
//        contents[2][4] = "";
//        contents[3][4] = "";
//        contents[4][4] = "";
//        contents[5][4] = "";
//        contents[0][5] = "";
//        contents[1][5] = "";
//        contents[2][5] = "";
//        contents[3][5] = "";
//        contents[4][5] = "";
//        contents[5][5] = "";
//        contents[0][6] = "";
//        contents[1][6] = "";
//        contents[2][6] = "";
//        contents[3][6] = "";
//        contents[4][6] = "";
//        contents[5][6] = "测试基础万盛道";

        initContent();
        Log.e("初始化", "gridView");
        myAdapter = new GridAdapter(this);
        myAdapter.setContents(contents, 6, 7);
        System.out.println("记录长度为"+contents.length);
        mainGrid.setAdapter(myAdapter);
        this.addClassWindow = myAdapter.addClassDialog;


    }


    //
}
