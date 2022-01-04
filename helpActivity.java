package com.example.weathermap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class helpActivity extends AppCompatActivity {
    ArrayList<HashMap<String,Object>> settingsList;
    ListView list2;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        test = (TextView)findViewById(R.id.test);
        settingsList = new ArrayList<>();
        String settings_Main[] = {"City", "Temprature Unit"};
        String settings_Second[] =  {"Select your city","Select your Temperature Unit"};

        for (int j=0; j<1;j++){
            HashMap<String,Object> settingsStrings = new HashMap<>();
            settingsStrings.put("ONE", settings_Main[j]);
            settingsStrings.put("SEC", settings_Second[j]);
            settingsList.add(settingsStrings);
        }
        ListAdapter adapter3 = new SimpleAdapter(helpActivity.this, settingsList,R.layout.row_layout_setting, new String[]{"ONE","SEC"},new int[] {R.id.textview4, R.id.textview5});
        list2 = (ListView) findViewById(R.id.listview2);
        list2.setAdapter(adapter3);

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              if(position ==0){
                  openDialogCity();
              }else if(position ==1){
                  openDialogTemp();
              }
            }
        });
    }
 public  void openDialogCity(){
        DialogCity dialogCity = new DialogCity();
        dialogCity.show(getSupportFragmentManager(),"dialog city");
 }
 public  void openDialogTemp(){
     Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
 }
}