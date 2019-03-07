package com.example.kcb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class DateActivity extends Activity {

    NumberPicker num_year = null;
    NumberPicker num_month = null;
    NumberPicker num_day = null;
    TextView tv_year = null;
    Button btn1;
    int  year = 2018;
    int month = 12;
    int day = 21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        initView();
    }

    private void initView(){
        // 实例化NumberPicker对象
        num_year = (NumberPicker)findViewById(R.id.number_np_year);
        num_month = (NumberPicker) findViewById(R.id.number_np_month);
        num_day = (NumberPicker) findViewById(R.id.number_np_day);
        btn1 = (Button)findViewById(R.id.btn1) ;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_year = (TextView)findViewById(R.id.number_tv_year);
        initYear();
        initMonth();
        initDay();
    }

    private void initYear(){
        num_year.setMaxValue(10000);
        num_year.setMinValue(1990);
        num_year.setValue(2018);
        // 为NumberPicker设置监听事件
        num_year.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal,
                                      int newVal) {
                year = newVal;
                showDate();
            }
        });
    }

    private void initMonth() {
        // 设置NumberPicker对象的相关属性
        num_month.setMaxValue(12);
        num_month.setMinValue(1);
        num_month.setValue(10);
        // 为NumberPicker设置监听事件
        num_month.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
                month = newVal;
                showDate();
            }
        });
    }

    private void initDay() {
        // 设置NumberPicker对象的相关属性
        num_day.setMaxValue(31);
        num_day.setMinValue(1);
        num_day.setValue(8);
        // 为NumberPicker设置监听事件
        num_day.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
                day = newVal;
                showDate();
            }
        });
    }
    void showDate() {
        tv_year.setText( year + "年" + month + "月" + day + "日");
        Toast.makeText(getApplicationContext(),
                tv_year.getText().toString(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.putExtra("ri_qi",tv_year.getText().toString());
        setResult(RESULT_OK,intent);
    }
}
