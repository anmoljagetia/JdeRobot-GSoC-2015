package com.example.anmoljagetia.myapplication;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Activity2 extends ActionBarActivity {

    ListView list2;
    ArrayList<Item> b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        list2 = (ListView)findViewById(R.id.listView2);
        Context context = this.getApplicationContext();
        ArrayList<Item> a = MainActivity.itemList;
        b = new ArrayList<Item>();

        String passedValue = getIntent().getStringExtra("id");
        int id = Integer.valueOf(passedValue);
        String initial = a.get(id).getSku();

        for (int i = 0; i < a.size(); i++) {
 /*               Toast toast = Toast.makeText(context,a.get(i).getSku() + "===" + a.get(id).getSku(), Toast.LENGTH_SHORT);
                toast.show();
*/
                if (a.get(i).getSku().matches(a.get(id).getSku())) {
                        Item item = new Item();
                        item.setSku(a.get(i).getSku());
                        item.setAmount(a.get(i).getAmount());
                        item.setCurrency(a.get(i).getCurrency());
                        b.add (item);
                }
                ItemAdapter adapter =  new ItemAdapter(getApplicationContext(), R.layout.row, b);
                list2.setAdapter(adapter);
            }
        }
 }

