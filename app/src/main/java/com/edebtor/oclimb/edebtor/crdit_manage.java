package com.edebtor.oclimb.edebtor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.edebtor.oclimb.edebtor.Common.ServiceHandler;
import com.edebtor.oclimb.edebtor.Common.commo;
import com.edebtor.oclimb.edebtor.Model.two_item;
import com.edebtor.oclimb.edebtor.Remote.IMyAPI;
import com.edebtor.oclimb.edebtor.printers.PrinterActivity;
import com.edebtor.oclimb.edebtor.utill.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class crdit_manage extends AppCompatActivity {

    public static String crdit_id,crdit_cust;
    public static int area_id;
    Spinner getArea_value, getDebitor_value;
    IMyAPI mServicw;
    Button btn_next;
    String[] area_ids;
    String[] crdit_ids;
    String[] debetors;

    Database db;

    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crdit_manage);

        mServicw = commo.getAPI();
        db=new Database(crdit_manage.this);

        getArea_value = (Spinner) findViewById(R.id.select_area);
        getDebitor_value = (Spinner) findViewById(R.id.select_deetor);

        getArea_value.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                if(area_ids != null) {
                    area_id = Integer.parseInt(area_ids[pos]);


                }else{

                    area_id =pos;
                }
                DownloadDebitor(area_id);

            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getDebitor_value.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                if(crdit_ids != null) {
                    crdit_id = crdit_ids[pos];
                    crdit_cust = debetors[pos];

                }

            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        DownloadArea();

        btn_next=findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            if(getArea_value.getSelectedItem()==("Select Area") || getDebitor_value.getSelectedItem()==("Select Customer")) {

                Toast.makeText(crdit_manage.this, "Please Select Area and Customer.", Toast.LENGTH_SHORT).show();

            }else{
                Intent in = new Intent(crdit_manage.this, collection.class);
                // i.putExtra("crdit_id", crdit_id);
                startActivity(in);
            }

            }
        });


    }

    private void setAreaValue(String[] areac) {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, areac
        );


        getArea_value.setAdapter(spinnerArrayAdapter);

    }


    private void setDebitorValue(String[] debetors) {


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, debetors
        );

        getDebitor_value.setAdapter(spinnerArrayAdapter);

    }

    private void DownloadArea(){

        try {
            int uid = Integer.parseInt(LoginActivity.uid);

            ArrayList<two_item> list = db.getUserArea(uid);


            if(list.size() > 0) {

                String[] area = new String[list.size()];
                area_ids = new String[list.size()];
                int i = 0;

                for (two_item t : list) {

                    area_ids[i] = t.getId();
                    area[i] = t.getValue();

                    i++;

                }
                setAreaValue(area);
            }else{
                String[] area = new String[]{"Select Area"};
                //setDebitorValue(debetors);
                setAreaValue(area);
            }


        }catch (Exception e){

        }

    }

    private void DownloadDebitor(int areaid){

        try {

            ArrayList<two_item> list = db.getUserAreaDebetor(areaid);



            if(list.size() > 0) {
                debetors = new String[list.size()];
                crdit_ids = new String[list.size()];
                int i=0;
                for (two_item t : list) {

                    crdit_ids[i] = t.getId();
                    debetors[i] = t.getValue();

                    i++;
                }
                setDebitorValue(debetors);
            }else{
                String[] debetors = new String[]{"Select Customer"};
                setDebitorValue(debetors);
            }




        }catch (Exception e){
        }

    }


}
