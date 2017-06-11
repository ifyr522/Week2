package com.zhou.week2;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.zhou.week2.bean.StudentrBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private GridView gv;
    private ProgressBar pb;
    private List<StudentrBean.StudentsBean.StudentBean> list = new ArrayList<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                RequestParams requestParams = new RequestParams("http://result.eolinker.com/Ikx4fDc398b93bfd0c8a146468e1f8cddfe9166719cba13?uri=zhoukao");
                x.http().get(requestParams, new org.xutils.common.Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        Gson gson = new Gson();
                        StudentrBean bean = gson.fromJson(result, StudentrBean.class);
                        StudentrBean.StudentsBean students = bean.getStudents();
                        List<StudentrBean.StudentsBean.StudentBean> slist = students.getStudent();

                        list.addAll(slist);

                        MyAdapter adapter = new MyAdapter(StudentActivity.this,list);
                        gv.setAdapter(adapter);

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {
                        pb.setVisibility(View.GONE);
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        gv = (GridView) findViewById(R.id.gv);
        pb = (ProgressBar) findViewById(R.id.pb);

        handler.sendEmptyMessageDelayed(0,3000);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StudentActivity.this,Main2Activity.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("img",list.get(position).getImg());
                intent.putExtra("content",list.get(position).getContent());
                startActivity(intent);
            }
        });
    }


}
