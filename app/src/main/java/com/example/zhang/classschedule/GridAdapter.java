package com.example.zhang.classschedule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class GridAdapter extends BaseAdapter {

    private Context mContext;
    private String[][] contents;
    private int rowTotal;
    private int colTotal;
    private int positionTotal;
//    private MainActivity mainWindow;

    private dateBase myClasses;

    public Dialog addClassDialog;
    public Dialog showClassDialog;
    public Integer[] Position;
    private boolean ifLongClick = false;

    //适配器的界面绑定
    private Button confirm;
    private Button cancel;
    private EditText addName;
    private EditText addRoom;
    private EditText addWeeks;
    private Switch ifSingal;
    private Switch ifDouble;

    //显示课程信息的界面绑定
    private TextView info_name;
    private TextView info_room;
    private Switch info_if_singal;
    private Switch info_if_double;

    public GridAdapter(Context context) {
        this.mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View dialogview = inflater.inflate(R.layout.add_class_dialog, null);
        addClassDialog = new Dialog(mContext);
        addClassDialog.setContentView(dialogview);

        //展示课程信息的dialog
        LayoutInflater inflater1 = LayoutInflater.from(mContext);
        View dialog_show_info = inflater1.inflate(R.layout.show_class_info,null);
        showClassDialog = new Dialog(mContext);
        showClassDialog.setContentView(dialog_show_info);

        myClasses = new dateBase(mContext);
    }


    @Override
    public int getCount() {
        return positionTotal;
    }

    @Override
    public Object getItem(int position) {
        int col = position % colTotal;      //列号
        int row = position / colTotal;      //行号
        return contents[row][col];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.text);
        if (!getItem(position).equals("")) {
            textView.setText((String) getItem(position));
            textView.setTextColor(Color.WHITE);

            //变换颜色
            int rand = position % colTotal;
            Log.e("Position","值为"+position);
            Log.e("rand","值为"+rand);

            switch (rand) {    //加载不同的底色
                case 0:
                    textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_0));
                    break;
                case 1:
                    textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_1));
                    break;
                case 2:
                    textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_2));
                    break;
                case 3:
                    textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_3));
                    break;
                case 4:
                    textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_4));
                    break;
                case 5:
                    textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_5));
                    break;
                case 6:
                    textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_6));
                    break;
                case 7:
                    textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_7));
                    break;
            }
        }
        if(getItem(position).equals("")){
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!ifLongClick) {
                        setPos(position);
                        int poX = Position[0];
                        int poY = Position[1];
                        addClassDialog.show();
                    } else {
                        ifLongClick = false;  //清除长按标志
                    }
                }
            });
        }else{
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!ifLongClick) {
                        setPos(position);
                        int poX = Position[0];
                        int poY = Position[1];
                        showClassDialog.show();
                    } else {
                        ifLongClick = false;  //清除长按标志
                    }
                }
            });
        }



        convertView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                AlertDialog dialogDetatils = null;
                int row = position / rowTotal;
                int col = position % colTotal;
                String message = "你长按了第" + String.valueOf(row) + "行，第" + String.valueOf(col) + "列";
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                ifLongClick = true;
                return false;
            }
        });

        addClassDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                confirm = (Button) addClassDialog.findViewById(R.id.confirm);
                cancel = (Button) addClassDialog.findViewById(R.id.cancel);
                addName = (EditText) addClassDialog.findViewById(R.id.className);
                addRoom = (EditText) addClassDialog.findViewById(R.id.classRoom);
                addWeeks = (EditText) addClassDialog.findViewById(R.id.weeks);
                ifSingal = (Switch) addClassDialog.findViewById(R.id.if_singal);
                ifDouble = (Switch) addClassDialog.findViewById(R.id.if_double);

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        aClass tempClass = new aClass();
                        {
                            tempClass.setClassName(addName.getText().toString());
                            tempClass.setClassRoom(addRoom.getText().toString());
                            tempClass.setWeeks(Integer.valueOf(addWeeks.getText().toString()));
                            tempClass.setPositionX(Position[0]);
                            tempClass.setPositionY(Position[1]);
                        }
                        tempClass.showInfo();
//                        String msg = "你点击了确定按钮！位置为" + Position[0]+ " " + Position[1];
                        String msg = "添加课程成功！";
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                        myClasses.addClass(tempClass);
                        addClassDialog.dismiss();
//                        mainWindow.initGridView();

                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addClassDialog.cancel();
                    }
                });
//                mainWindow.dialogEvent(Position[0],Position[1]);
            }
        });


        //显示信息的dialog
        showClassDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                info_name = (TextView) showClassDialog.findViewById(R.id.info_name);
                info_room = (TextView)showClassDialog.findViewById(R.id.info_room);
                info_if_double = (Switch)showClassDialog.findViewById(R.id.if_double);
                info_if_singal = (Switch)showClassDialog.findViewById(R.id.if_singal);

                aClass findClass = myClasses.getOne(Position[0],Position[1]);
                info_name.setText(findClass.getClassName());
                info_room.setText(findClass.getClassRoom());

            }
        });
        return convertView;
    }

    public void setPos(int position) {
        int poX = position / colTotal;
        int poY = position % colTotal;
        Position = new Integer[]{poX, poY};
//        return  Position;
    }

    public void setContents(String[][] contents, int row, int col) {
        this.contents = contents;
        this.rowTotal = row;
        this.colTotal = col;
        positionTotal = rowTotal * colTotal;
    }
}
