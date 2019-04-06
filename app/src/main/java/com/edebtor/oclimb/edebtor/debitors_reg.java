package com.edebtor.oclimb.edebtor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edebtor.oclimb.edebtor.Common.ServiceHandler;
import com.edebtor.oclimb.edebtor.Common.commo;
import com.edebtor.oclimb.edebtor.Remote.IMyAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class debitors_reg extends AppCompatActivity {
    EditText nic, fname, lname, email, address1, address2, pno1, pno2;
    Button btn_add;
    IMyAPI mServicw;

    String  g_nic, g_fname, g_lname, g_email, g_address1, g_address2, g_pno1, g_pno2;


    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debitors_reg);

        mServicw = commo.getAPI();

        btn_add = findViewById(R.id.btn_customer);

        nic = (EditText) findViewById(R.id.nic);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        address1 = (EditText) findViewById(R.id.addres1);
        address2 = (EditText) findViewById(R.id.addres2);
        pno1 = (EditText) findViewById(R.id.phno1);
        pno2 = (EditText) findViewById(R.id.phno2);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(debitors_reg.this);
                builder.setMessage("Customer Register").setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        g_nic = nic.getText().toString();
                        g_fname = fname.getText().toString();
                        g_lname = lname.getText().toString();
                        g_email = email.getText().toString();
                        g_address1 = address1.getText().toString();
                        g_address2 = address2.getText().toString();
                        g_pno1 = pno1.getText().toString();
                        g_pno2 = pno2.getText().toString();

                        if(g_nic.equals("")){
                            Toast.makeText(debitors_reg.this, "Please enter the NIC.", Toast.LENGTH_SHORT).show();
                        }else
                        if(g_fname.equals("")){
                            Toast.makeText(debitors_reg.this, "Please enter the First Name.", Toast.LENGTH_SHORT).show();
                        }else
                        if(g_lname.equals("")){
                            Toast.makeText(debitors_reg.this, "Please enter the Last Name.", Toast.LENGTH_SHORT).show();
                        }else

                        if(g_address1.equals("")){
                            Toast.makeText(debitors_reg.this, "Please enter the Address.", Toast.LENGTH_SHORT).show();
                        }else

                        if(g_pno1.equals("")){
                            Toast.makeText(debitors_reg.this, "Please enter the Phone No.", Toast.LENGTH_SHORT).show();
                        }else {

                            if(g_email.equals("")){

                                g_email = " ";
                            }
                            if(g_address2.equals("")){

                                g_address2 = " ";
                            }

                            if(g_pno1.equals("")){

                                g_pno1 = " ";
                            }

                            new DownloadDebitor().execute();
                        }

                    }
                })
                        .setNegativeButton("Cancel",null) ;
                AlertDialog alt =   builder.create();
                alt.show();


            }
        });


    }


    class DownloadDebitor extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;

        protected void onPreExecute () {
            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {

                String token="525252";
                String merchantURL = commo.BASE_URL+"debitors_reg.php";
                HashMap<String, String> params = new HashMap<>();


                params.put("token", token);
                params.put("nic", g_nic);
                params.put("fname", g_fname);
                params.put("lname", g_lname);
                params.put("email", g_email);
                params.put("address1", g_address1);
                params.put("address2", g_address2);
                params.put("pno1", g_pno1);
                params.put("pno2", g_pno2);

                ServiceHandler sh = new ServiceHandler(context);
                json = sh.makeHttpRequest(merchantURL, ServiceHandler.POST, params);

                jObj = new JSONObject(json);
            } catch (Exception e) {

            }

            return jObj;

        }

        protected void onPostExecute (JSONObject json_object){


            if (json_object != null) {


                try {

                    boolean error = json_object.getBoolean("error");

                    if(error){
                        String errormsg = json_object.getString("error_msg");
                        //  String[] debetors = new String[]{"No debitor in area"};

                        Toast.makeText(debitors_reg.this, "Customer registration failed!", Toast.LENGTH_SHORT).show();


                    }else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(debitors_reg.this);
                        builder.setMessage("Customer registered successfully!").setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                        Intent in = new Intent(debitors_reg.this, main_menu.class);
                        startActivity(in);

                            }
                        }) ;
                        AlertDialog alt =   builder.create();
                        alt.show();


                    }


                } catch (JSONException e) {

                    e.printStackTrace();

                }

            }
        }



    }
}
