package com.example.mudda.test1;

import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScoreTable extends AppCompatActivity {
    ListView lvfirstname;
    ListView lvlastname;
    ListView lvscore;
    String address = "http://192.168.1.24/users/v1/getScore.php";
    //String address = "http://192.168.1.24/users/v1/getScore.php";
    InputStream is = null;
    String line = null;
    String result = null;
    String[] datafirstname;
    String[] datalastname;
    String[] datascore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_table);
        lvfirstname = findViewById(R.id.viewFirstName);
        lvlastname = findViewById(R.id.viewLastName);
        lvscore = findViewById(R.id.viewScore);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        getData();
        ArrayAdapter<String> adapterfirstname = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datafirstname){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.RED);
                return view;
            }
        };
        lvfirstname.setAdapter(adapterfirstname);
        ArrayAdapter<String> adapterlastname = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datalastname){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.RED);
                return view;
            }
        };
        lvlastname.setAdapter(adapterlastname);
        ArrayAdapter<String> adapterscore = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datascore){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.RED);
                return view;
            }
        };
        lvscore.setAdapter(adapterscore);
    }

    private void getData() {
        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            datafirstname = new String[ja.length()];
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                datafirstname[i] = "   " + jo.getString("firstname");
            }
            datalastname = new String[ja.length()];
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                datalastname[i] = "   " + jo.getString("lastname");
            }
            datascore = new String[ja.length()];
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                datascore[i] = "   " + jo.getString("score");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}