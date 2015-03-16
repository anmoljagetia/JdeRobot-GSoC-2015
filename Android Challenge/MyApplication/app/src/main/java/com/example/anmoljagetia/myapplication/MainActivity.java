package com.example.anmoljagetia.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ListView list;
    public static ArrayList <Item> itemList;
    Toast t;
    String data;

    String URI = "http://quiet-stone-2094.herokuapp.com/transactions.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.listView);
        itemList = new ArrayList<Item>();
        new ItemsAsyncTask().execute(URI);
        }

    public class ItemsAsyncTask extends AsyncTask<String , Void, Boolean > {

        protected void onPreExecute() {
            super.onPreExecute();

        }
        
           @Override
           protected Boolean doInBackground(String... params) {
               Log.d("Starting HTTP request", "1");
               try {
                   Log.d("Starting HTTP request", "trying for HTTP Request");
                   HttpClient client = new DefaultHttpClient();
                   HttpGet get = new HttpGet(URI);
                   HttpResponse response = client.execute(get);
                   int status = response.getStatusLine().getStatusCode();

                   if (status == 200) {
                       Log.d("Starting HTTP request", "status code 200");
                       HttpEntity entity = response.getEntity();
                       data = EntityUtils.toString(entity);

                       try {
                           JSONArray jArray = new JSONArray(data);
                           Log.d("data size", String.valueOf(data.length()));
                           for (int i = 0; i < jArray.length(); i++) {

                               Item item = new Item();
                               JSONObject jobj = jArray.getJSONObject(i);

                               item.setSku(jobj.getString("sku"));
                              item.setAmount(jobj.getString("amount"));
                               item.setCurrency(jobj.getString("currency"));
//                                Log.e("item : ", jobj.getString("sku") + jobj.getString("amount") +jobj.getString("currency"));
                               itemList.add(item);
                           }
                       } catch (Exception e) {
                           e.printStackTrace();
                           Log.d(" exception error", "oh god, why?");
                       }
                       return true;
                   } else {
                       Log.d(String.valueOf(status), "Error, but reason unknown");
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }

               return false;
           }

           @Override
           protected void onPostExecute(Boolean aBoolean) {
               super.onPostExecute(aBoolean);

               if (aBoolean == false) {
                   // "Shoot! ","Download error! Check your internet connection";
               } else {
                   Log.d("Pushing into a listview", "Calling adapter");
                   ItemAdapter adapter =  new ItemAdapter(getApplicationContext(), R.layout.row, itemList);
                   list.setAdapter(adapter);
                   list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           Intent i = new Intent(MainActivity.this, Activity2.class);
                           i.putExtra("id", String.valueOf(position));
                           startActivity(i);
                       }
                   });

               }
           }

       }
}
