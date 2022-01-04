package com.example.weathermap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String[] day_list = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    String[] Rearranged = {"","","","","","",""};
    int pos =0;
    int start = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;
    TextView textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int y =start; y<day_list.length;y++) {
            Rearranged[pos] = day_list[y];
            pos++;
        }
        for (int z = 0; z<start; z++) {
            Rearranged[pos] = day_list[z];
            pos++;
        }
        for (int k = 0; k<Rearranged.length; k++) {
            System.out.println(Rearranged[k] + "\n");
        }

        textInput = (TextView) findViewById(R.id.textInput1);
        Intent intent1 = getIntent();
        String CITYNAME = intent1.getStringExtra("CityName");
        textInput.setText(CITYNAME);

        FetchData task = new FetchData();
        task.execute();

        CustomerlistAdepter adepter = new CustomerlistAdepter(this,Rearranged, task.description, task.temperature, task.icon);
        ListView list = findViewById(R.id.listView);
        list.setAdapter(adepter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String SelectedItem = task.icon[+position];

                String tem_temp = task.getTemperature()[+position];
                String tem_Des = task.getDescription()[+position];
                String tem_icon = task.getWeathericon()[+position];
                String tem_hum = task.getHumidity()[+position];
                String tem_city = task.getTown()[+position];
                Intent open = new Intent(MainActivity.this,MainActivity2.class);
                Toast.makeText(getApplicationContext(), tem_temp,Toast.LENGTH_SHORT).show();

                Bundle bundle1 = new Bundle();
                bundle1.putString("tem",tem_temp);
                bundle1.putString("des",tem_Des);
                bundle1.putString("temicon",tem_icon);
                bundle1.putString("hum",tem_hum);
                bundle1.putString("twn",tem_city);
                open.putExtras(bundle1);
                startActivity(open);
            }
        });


        Log.d("my log", task.description[4]);
    }

    public class FetchData extends AsyncTask<String, Void, String>{

        HttpURLConnection urlConnection=null;
        String forecastJasonStr;
        BufferedReader reader;



        String[] temperature = {"", "", "", "", "", "", ""};
        String[] description = {"", "", "", "", "", "", ""};
        String[] icon = {"", "", "", "", "", "", ""};
        String[] humidity = {"", "", "", "", "", "", ""};
        String[] town={""};

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);

            try {
                //day0 data getopen

                JSONObject JSON = new JSONObject(forecastJasonStr);
                 JSONObject  City = JSON.getJSONObject("city");



                JSONArray   List = JSON.getJSONArray("list");

                JSONObject day0 =  List.getJSONObject(0);
                JSONArray weather0 = day0.getJSONArray("weather");
                JSONObject weather0_in=weather0.getJSONObject(0);
                JSONObject tempval0 = day0.getJSONObject("temp");

                String city =City.getString("name");
                town[0]=city;


                int temp0  =tempval0.getInt("day")-273;

                String tempstor =Integer.toString(temp0);
                String weatherstus= weather0_in.getString("main");
                String image =weather0_in.getString("icon");

                temperature[0] = tempstor;
                description[0] = weatherstus;
                icon[0] = image;

                int feels_like0 = day0.getInt("humidity");
                String feelsStor0 = Integer.toString(feels_like0);
                humidity[0]=feelsStor0;


                //day0 data getclose

                //day1 data getopen

                JSONObject day1 =  List.getJSONObject(1);
                JSONArray weather1 = day1.getJSONArray("weather");
                JSONObject weather1_in=weather1.getJSONObject(0);
                JSONObject tempval1 = day1.getJSONObject("temp");

                int temp1  =tempval1.getInt("day")-273;
                String tempstor1 =Integer.toString(temp1);


                int feels_like1 = day1.getInt("humidity");
                String feelsStor1 = Integer.toString(feels_like1);


                String weatherstus1= weather1_in.getString("main");

                String image1 =weather1_in.getString("icon");

                temperature[1] = tempstor1;
                description[1] = weatherstus1;
                icon[1] = image1;
                humidity[1]=feelsStor1;

                //day1 data getclose

                //da2 data getopen

                JSONObject day2 =  List.getJSONObject(2);
                JSONArray weather2 = day2.getJSONArray("weather");
                JSONObject weather2_in=weather2.getJSONObject(0);
                JSONObject tempval2 = day2.getJSONObject("temp");

                int temp2  =tempval2.getInt("day")-273;
                String tempstor2 =Integer.toString(temp2);


                int feels_like2 = day2.getInt("humidity");
                String feelsStor2 = Integer.toString(feels_like2);

                String weatherstus2= weather2_in.getString("main");

                String image2 =weather2_in.getString("icon");

                temperature[2] = tempstor2;
                description[2] = weatherstus2;
                icon[2] = image2;
                humidity[2]=feelsStor2;

                //day2 data

                //day3 data getopen

                JSONObject day3 =  List.getJSONObject(3);
                JSONArray weather3 = day3.getJSONArray("weather");
                JSONObject weather3_in=weather3.getJSONObject(0);
                JSONObject tempval3 = day3.getJSONObject("temp");

                int temp3  =tempval3.getInt("day")-273;
                String tempstor3 =Integer.toString(temp3);


                int feels_like3 = day3.getInt("humidity");
                String feelsStor3 = Integer.toString(feels_like3);


                String weatherstus3= weather3_in.getString("main");

                String image3 =weather3_in.getString("icon");

                temperature[3] = tempstor3;
                description[3] = weatherstus3;
                icon[3] = image3;
                humidity[3]=feelsStor3;

                //day3 data getclose

                //day4 data getopen

                JSONObject day4 =  List.getJSONObject(4);
                JSONArray weather4 = day4.getJSONArray("weather");
                JSONObject weather4_in=weather4.getJSONObject(0);
                JSONObject tempval4 = day4.getJSONObject("temp");

                int temp4  =tempval4.getInt("day")-273;
                String tempstor4 =Integer.toString(temp4);


                int feels_like4 = day4.getInt("humidity");
                String feelsStor4 = Integer.toString(feels_like4);


                String weatherstus4= weather4_in.getString("main");

                String image4 =weather4_in.getString("icon");

                temperature[4] = tempstor4;
                description[4] = weatherstus4;
                icon[4] = image4;
                humidity[4]=feelsStor4;

                //day4 data getclose

                //day5 data getopen

                JSONObject day5 =  List.getJSONObject(5);
                JSONArray weather5 = day4.getJSONArray("weather");
                JSONObject weather5_in=weather5.getJSONObject(0);
                JSONObject tempval5 = day5.getJSONObject("temp");

                int temp5  =tempval5.getInt("day")-273;
                String tempstor5 =Integer.toString(temp5);


                int feels_like5 = day5.getInt("humidity");
                String feelsStor5 = Integer.toString(feels_like5);


                String weatherstus5= weather5_in.getString("main");

                String image5 =weather5_in.getString("icon");

                temperature[5] = tempstor5;
                description[5] = weatherstus5;
                icon[5] = image5;
                humidity[5]=feelsStor5;
                //day5 data getclose

                //day6 data getopen

                JSONObject day6 =  List.getJSONObject(6);
                JSONArray weather6 = day6.getJSONArray("weather");
                JSONObject weather6_in=weather6.getJSONObject(0);
                JSONObject tempval6 = day6.getJSONObject("temp");

                int temp6  =tempval6.getInt("day")-273;
                String tempstor6 =Integer.toString(temp6);


                int feels_like6 = day6.getInt("humidity");
                String feelsStor6 = Integer.toString(feels_like6);

                String weatherstus6= weather6_in.getString("main");

                String image6 =weather6_in.getString("icon");

                temperature[6] = tempstor6;
                description[6] = weatherstus6;
                icon[6] = image6;
                humidity[6]=feelsStor6;

                //day6 data getclose

              // JSONObject[] day = {day0,day1,day2,day3,day4,day5,day6};
               // String[] temperature2= {tempstor,tempstor1,tempstor2,tempstor3,tempstor4,tempstor5,tempstor6};
               //String[] icon2 = {weatherstus, weatherstus1, weatherstus2, weatherstus3, weatherstus4, weatherstus5, weatherstus6};


            }catch (JSONException e){
                e.printStackTrace();
            }


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            Intent intent = getIntent();
            String CITYNAME = intent.getStringExtra("CityName");
            //System.out.println(CITYNAME);
            String z;

            String x = "https://api.openweathermap.org/data/2.5/forecast/daily?q=";
            String y = "&cnt=7&appid=a18b978603316d47c572d98d52a420f6";



            if(textInput.getText().toString().equals("") && textInput.getText().length() <= 0 ){
                z = x + "colombo" + y;

            } else {
                z = x + CITYNAME + y;
           }

            try {
                final String BASE_URL = z;
                URL url = new URL(BASE_URL);

                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line1;

                while ((line1 = reader.readLine()) != null) {
                    buffer.append(line1 + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }

                forecastJasonStr = buffer.toString();

            }catch (IOException e){
                Log.e("hi","Error",e);
                return  null;
            }finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
                if (reader !=null){
                    try {
                        reader.close();
                    }catch (final IOException e){
                    Log.e("hii","error closing stream", e);
                    }
                }
            }
            return null;
        }
        public String[] getDescription(){
            return description;
        }
        public String[] getTemperature(){
            return temperature;
        }
        public  String[] getWeathericon(){
            return icon;
        }
        public String[] getHumidity(){
            return humidity;
        }
        public  String[] getTown(){
            return town;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item1){
        int id = item1.getItemId();

        if (id == R.id.action_about){

        }
        if (id == R.id.action_setting){
            Intent profileIntent = new Intent(MainActivity.this, helpActivity.class);
            startActivity(profileIntent);
        }
        return  super.onOptionsItemSelected(item1);
    }
}