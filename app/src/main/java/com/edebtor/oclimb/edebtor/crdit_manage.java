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
    //String crdit_id;

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
                //  System.out.println("crdit_ids[pos] download " +pos);
                // Toast.makeText(crdit_manage.this, area_ids[pos], Toast.LENGTH_SHORT).show();
                // new DownloadDebitor().execute();


            }
            public void onNothingSelected(AdapterView<?> parent) {
                //  Toast.makeText(crdit_manage.this, "Spinner1: unselected", Toast.LENGTH_SHORT).show();
            }
        });

        getDebitor_value.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                if(crdit_ids != null) {
                    crdit_id = crdit_ids[pos];
                    crdit_cust = debetors[pos];

                    //System.out.println("crdit_cust -download " + crdit_cust);
                }
                //System.out.println("crdit_ids[pos] download " + view);
                //    Toast.makeText(crdit_manage.this, crdit_ids[pos], Toast.LENGTH_SHORT).show();

            }
            public void onNothingSelected(AdapterView<?> parent) {
                //  Toast.makeText(crdit_manage.this, "Spinner1: unselected", Toast.LENGTH_SHORT).show();
            }
        });


        // new DownloadArea().execute();

        DownloadArea();

        btn_next=findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder altdial= new AlertDialog.Builder(crdit_manage.this);
//                altdial.setMessage("Are you sure?").setCancelable(false)
//                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {

                                // Intent i = new Intent(crdit_manage.this, PrinterActivity.class);
                                //System.out.println("aaaaaaaaaaaa"+getArea_value.getSelectedItem());

                                if(getArea_value.getSelectedItem()==("Select Area") || getDebitor_value.getSelectedItem()==("Select Customer")) {

                                    Toast.makeText(crdit_manage.this, "Please Select Area and Customer.", Toast.LENGTH_SHORT).show();

                                }else{
                                    Intent in = new Intent(crdit_manage.this, collection.class);
                                    // i.putExtra("crdit_id", crdit_id);
                                    startActivity(in);
                                }

//                            }
//                        })
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.cancel();
//                            }
//                        });

                AlertDialog alert= altdial.create();
                /*alert.setTitle("Credit");*/
                alert.show();



            }
        });




    }

    private void setAreaValue(String[] areac) {

        // System.out.println("oooook");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, areac
        );


        //   spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        getArea_value.setAdapter(spinnerArrayAdapter);

        Toast.makeText(crdit_manage.this, "Load area", Toast.LENGTH_SHORT).show();
    }

    //==========================================================================================

    private void setDebitorValue(String[] debetors) {


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, debetors
        );


        //spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        getDebitor_value.setAdapter(spinnerArrayAdapter);

        Toast.makeText(crdit_manage.this, "Load getDebitor value", Toast.LENGTH_SHORT).show();
    }

    //==========================================================================================
   /* class DownloadArea extends AsyncTask<String, Void, JSONObject>

    {
       *//* private String json;
        private JSONObject jObj;



        protected void onPreExecute () {




            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {
                String uid = LoginActivity.uid;
                //System.out.println("uid " + uid);
                String merchantURL = commo.BASE_URL+"collection_area_user.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("uid", uid);


                ServiceHandler sh = new ServiceHandler(context);
                json = sh.makeHttpRequest(merchantURL, ServiceHandler.POST, params);
                System.out.println("ttttt download " + json);

                jObj = new JSONObject(json);
            } catch (Exception e) {
                System.out.println("ttttt download " + e.getMessage());
            }

            return jObj;

        }

        protected void onPostExecute (JSONObject json_object){


            if (json_object != null) {
                System.out.println("ttttt download " + json_object.toString());


                try {
                    JSONArray arr=json_object.getJSONArray("Area");
                    //String error = json_object.getString();
                    String[] area = new String[arr.length()];
                    area_ids= new String[arr.length()];
                    int[] ids = new int[arr.length()];
                    Log.d("assss-----------",ids.toString());
                    for (int i = 0; i <arr.length() ; i++) {
                        JSONObject aria=arr.getJSONObject(i);
                        area[i]=aria.getString("Area");
                        area_ids[i]=aria.getString("id");
                      //  Log.d("assss-----------",aria.getString("Area"));

                    }
                    setAreaValue(area);





                } catch (JSONException e) {

                    e.printStackTrace();
                    System.out.println("ttttt download error " + e.getMessage());
                }

            }
        }*//*



    }
*/

    private void DownloadArea(){

        try {
            int uid = Integer.parseInt(LoginActivity.uid);
            //System.out.println("pppppppppp");
            ArrayList<two_item> list = db.getUserArea(uid);
            //System.out.println("pppppppppp");

            if(list.size() > 0) {

                String[] area = new String[list.size()];
                area_ids = new String[list.size()];
                int i = 0;

                for (two_item t : list) {
                    //System.out.println("--aaaaaa---" + t.getValue());


                    //     System.out.println("--aaaaaa---"+i);
                    area_ids[i] = t.getId();
                    area[i] = t.getValue();
                    //  System.out.println("--aaaaaa---"+i);
                    i++;
                    // Log.i("Value of element "+i, String.valueOf(list.get(i)));
                }
                setAreaValue(area);
            }else{
                String[] area = new String[]{"Select Area"};
                //setDebitorValue(debetors);
                setAreaValue(area);
            }
            //System.out.println("zzzzz-");


        }catch (Exception e){

        }

    }

    private void DownloadDebitor(int areaid){

        try {
            //   int uid = Integer.parseInt(LoginActivity.uid);
            //System.out.println("pppppppppp1");
            ArrayList<two_item> list = db.getUserAreaDebetor(areaid);
            //  System.out.println("pppppppppp");

            // System.out.println("pppppppppp1---"+list.size());



            if(list.size() > 0) {
                debetors = new String[list.size()];
                crdit_ids = new String[list.size()];
                int i=0;
                for (two_item t : list) {
                    //    System.out.println("--aaaaaa---"+t.getValue());


                    //     System.out.println("--aaaaaa---"+i);
                    crdit_ids[i] = t.getId();
                    debetors[i] = t.getValue();
                    //  System.out.println("--aaaaaa---"+i);
                    i++;
                    // Log.i("Value of element "+i, String.valueOf(list.get(i)));
                }
                setDebitorValue(debetors);
            }else{
                String[] debetors = new String[]{"Select Customer"};
                setDebitorValue(debetors);
            }

            //System.out.println("zzzzz-");


        }catch (Exception e){
        }

    }


    //==========================================================================================



   /* class DownloadDebitor extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;



        protected void onPreExecute () {




            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {
               // String uid = LoginActivity.uid;
                System.out.println("area_id " + area_id);
                String merchantURL = commo.BASE_URL+"collection_debitor.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("area_id", area_id);


                ServiceHandler sh = new ServiceHandler(context);
                json = sh.makeHttpRequest(merchantURL, ServiceHandler.POST, params);
                System.out.println("ttttt download " + json);

                jObj = new JSONObject(json);
            } catch (Exception e) {
                System.out.println("ttttt download " + e.getMessage());
            }

            return jObj;

        }

        protected void onPostExecute (JSONObject json_object){


            if (json_object != null) {
                System.out.println("ttttt download " + json_object.toString());


                try {

                    boolean error = json_object.getBoolean("error");

                    if(error){
                        String errormsg = json_object.getString("error_msg");
                        String[] debetors = new String[]{"No debitor in area"};
                        setDebitorValue(debetors);
                    }else {
                        //Log.d("error_val",error+" "+errormsg);

                        JSONArray arr = json_object.getJSONArray("get_debetor");
                        String[] debetors = new String[arr.length()];
                        crdit_ids = new String[arr.length()];
                        int[] ids = new int[arr.length()];


                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject getdebetors = arr.getJSONObject(i);
                            debetors[i] = getdebetors.getString("debetor");
                            crdit_ids[i] = getdebetors.getString("id");
                            //  Log.d("assss-----------",aria.getString("Area"));

                        }
                        setDebitorValue(debetors);

                    }








                } catch (JSONException e) {

                    e.printStackTrace();
                    System.out.println("ttttt download error " + e.getMessage());
                }

            }
        }



    }
*/

}
