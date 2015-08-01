package com.dailyschedule;


import android.os.Bundle;
import core.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("i am in");
        initView();
    }
    
    private void initView() {

	}
}
