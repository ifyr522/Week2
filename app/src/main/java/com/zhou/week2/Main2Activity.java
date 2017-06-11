package com.zhou.week2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class Main2Activity extends AppCompatActivity {

    private TextView name;
    private ImageView imageView;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name = (TextView) findViewById(R.id.text2);
        imageView = (ImageView) findViewById(R.id.imageView1);
        content = (TextView) findViewById(R.id.textView1);

        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name");
        String content1 = intent.getStringExtra("content");
        String img = intent.getStringExtra("img");

        name.setText(name1+"的信息");
        ImageLoader.getInstance().displayImage(img,imageView);
        content.setText(content1);
    }
}
