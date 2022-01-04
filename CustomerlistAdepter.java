package com.example.weathermap;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class CustomerlistAdepter extends ArrayAdapter<String> {

    private final Activity context;
    private  final String[] dayName;
    private final String[] descriptionName;
    private final String[] temperatureVal;
    private final String[] weatherIcon;

    public CustomerlistAdepter(Activity context, String[] dayName,String[] descriptionName, String[] temperatureVal, String[] weatherIcon) {


        super(context,R.layout.my_list, dayName);
        this.context = context;
        this.dayName = dayName;
        this.descriptionName = descriptionName;
        this.temperatureVal = temperatureVal;
        this.weatherIcon = weatherIcon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
       View rowView = inflater.inflate(R.layout.my_list, null, true);

       TextView txt1 = (TextView) rowView.findViewById(R.id.days);
       TextView txt2 = (TextView) rowView.findViewById(R.id.description);
       TextView txt3 = (TextView) rowView.findViewById(R.id.temp);
       ImageView Image1 =(ImageView) rowView.findViewById(R.id.weathericon);

       txt1.setText(dayName[position]);
       txt2.setText(descriptionName[position]);
       txt3.setText(temperatureVal[position]);
        Picasso.get().load("http://openweathermap.org/img/w/"+weatherIcon[position]+".png").into(Image1);
        return rowView;


    }
}
