package com.example.recyviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.recyviewdemo.adapter.RecyAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView re;
    private RecyAdapter adapter;
    private String[] str;
    private Button button;

    /*条目局部刷新
    *
    *    https://blog.csdn.net/qq541976141/article/details/51485732
    *   adapt.notifyDataSetChanged();
    *   adapt.notifyItemInserted(position);
    *   adapt.notifyItemMoved(position);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        re = (RecyclerView) findViewById(R.id.re);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        str = new String[50];
        for (int i = 0; i < 50; i++) {
            str[i] = "条目" + i;
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        re.setLayoutManager(manager);
        adapter = new RecyAdapter(this, str);
        re.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                adapter.str[0] = "0000000";
                adapter.str[10] = "2222222";
                adapter.notifyItemChanged(0);
                adapter.notifyItemChanged(10);
                break;
        }
    }
}
