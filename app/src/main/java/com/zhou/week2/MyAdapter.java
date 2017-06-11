package com.zhou.week2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhou.week2.bean.StudentrBean;

import java.util.List;

/**
 * Created by r on 2017/6/11.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<StudentrBean.StudentsBean.StudentBean> list;

    public MyAdapter(Context context, List<StudentrBean.StudentsBean.StudentBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context,R.layout.lv_item,null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.name = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getName());
        ImageLoader.getInstance().displayImage(list.get(position).getImg(),holder.imageView);
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView name;
    }
}
