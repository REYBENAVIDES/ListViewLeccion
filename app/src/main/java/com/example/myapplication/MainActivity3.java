package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity3 extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new
                WebService("https://revistas.uteq.edu.ec/ws/journals.php",
                datos, MainActivity3.this, MainActivity3.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        String lstRevista="";
        JSONArray JSONlista = new JSONArray(result);
        ArrayList dtID = new ArrayList();
        ArrayList dtName = new ArrayList();
        ArrayList dtFoto = new ArrayList();
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            dtID.add(banco.getString("journal_id").toString());
            dtName.add(banco.getString("name").toString());
            dtFoto.add(banco.getString("portada").toString());
        }


        TextView txtmensaje = (TextView)findViewById(R.id.txtMensaj);
        ListView listRevista = (ListView) findViewById(R.id.listRevista);


        ArrayAdapter<String> datosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dtName);
        listRevista.setAdapter(datosAdapter);
        listRevista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                String selectItem = (String)adapterView.getItemAtPosition(posicion);

            }
        });
    }
}