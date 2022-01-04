package com.example.weathermap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle1 =getIntent().getExtras();
        String tempfl = bundle1.getString("tem");
        TextView temp_txt = (TextView)findViewById(R.id.temp_txt);
        temp_txt.setText(tempfl);

        String desfl = bundle1.getString("des");
        TextView des_txt = (TextView)findViewById(R.id.statusfinal);
        des_txt.setText(desfl);

        String humfl = bundle1.getString("hum");
        TextView hum_txt = (TextView)findViewById(R.id.humidity);
        hum_txt.setText(humfl);

        String twnfl = bundle1.getString("twn");
        TextView twn_txt = (TextView)findViewById(R.id.finaltown);
        twn_txt.setText(twnfl);



        String iconfl = bundle1.getString("temicon");
        ImageView icon_txt = (ImageView) findViewById(R.id.dayicon);
        Picasso.get().load("http://openweathermap.org/img/w/"+iconfl+".png").into(icon_txt);

    }
}