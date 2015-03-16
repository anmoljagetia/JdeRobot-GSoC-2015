package com.example.anmoljagetia.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anmol Jagetia on 15-03-2015.
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    ArrayList <Item> itemList;
    int resource;
    Context context;
    LayoutInflater vi;

    public ItemAdapter(Context context, int resource, ArrayList<Item> objects) {
        super(context, resource, objects);
        this.itemList = objects;
        this.resource = resource;
        this.context = context;

        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView =  vi.inflate(this.resource,null);
            holder = new ViewHolder();

            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
            holder.textView3 = (TextView) convertView.findViewById(R.id.textView3);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(itemList.get(position).getSku());
        holder.textView2.setText(itemList.get(position).getAmount());
        holder.textView3.setText(itemList.get(position).getCurrency());

        return convertView;
    }

    static class ViewHolder {
        public TextView textView;
        public TextView textView2;
        public TextView textView3;
    }
}
