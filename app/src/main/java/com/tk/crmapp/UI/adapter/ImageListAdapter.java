package com.tk.crmapp.UI.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tk.crmapp.R;
import com.tk.crmapp.models.Customer;

import java.util.List;

public class ImageListAdapter extends ArrayAdapter<Customer> {
    private int recourceId;
    public ImageListAdapter(Context context, int resource, List<Customer> objects) {
        super(context, resource, objects);
        recourceId = resource;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Customer customer = getItem(position); //得到集合中指定位置的一组数据，并且实例化
        View view = LayoutInflater.from(getContext()).inflate(recourceId,parent,false); //用布局裁剪器(又叫布局膨胀器)，将导入的布局裁剪并且放入到当前布局中
        ImageView imageView = (ImageView)view.findViewById(R.id.IamgeView_List);//从裁剪好的布局里获取ImageView布局ID
        TextView textView = (TextView)view.findViewById(R.id.TextView_List); //从裁剪好的布局里获取TextView布局Id
        switch ( customer.getTk_degree_tp().getName())
        {
            case "黑钻" :
                imageView.setImageResource(R.drawable.black_diamond_level);
                break;
            case "尊钻" :
                imageView.setImageResource(R.drawable.god_diamond_level);
                break;
            case "私钻":
                imageView.setImageResource(R.drawable.private_diamond_level);
                break;
            case "幸福":
                imageView.setImageResource(R.drawable.fu_level);
                break;

            case "铂金":
                imageView.setImageResource(R.drawable.platinum_level);
                break;
            case "黄金":
                imageView.setImageResource(R.drawable.gold_level);
                break;
            default:
                imageView.setImageResource(R.drawable.gold_level);
                break;

        }

        textView.setText(customer.getName()+"||"+customer.getTk_address());//将当前一组imageListArray类中的TextView内容导入到TextView布局中
        return view;
    }
}
