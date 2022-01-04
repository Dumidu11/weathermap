package com.example.weathermap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogCity extends AppCompatDialogFragment {
    private EditText editCity;
    public  String cityName,getCityName;

  @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
      AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

      LayoutInflater inflater = getActivity().getLayoutInflater();
      View view = inflater.inflate(R.layout.layout_city,null);

      builder.setView(view).setTitle("set City").setNegativeButton("cencel", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int i) {
          }
      })
              .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int i) {
                     cityName = editCity.getText().toString();
                      Intent sendData = new Intent(getActivity(), MainActivity.class);
                              sendData.putExtra("CityName", cityName);
                      startActivity(sendData);
                  }
              });
      editCity = view.findViewById(R.id.inputcity);
      return  builder.create();
    }
}
