package com.edebtor.oclimb.edebtor;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import com.edebtor.oclimb.edebtor.Common.ServiceHandler;
import com.edebtor.oclimb.edebtor.Common.commo;
import com.edebtor.oclimb.edebtor.Remote.IMyAPI;
import com.edebtor.oclimb.edebtor.utill.Database;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class daily_report extends AppCompatActivity {

    IMyAPI mServicw;


    Database db;

    Context context=this;

    Button btn_view,ex_confrom;
    TextView s_tot;
    EditText e_amount, e_details;
    String  g_amount, g_details;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_report);

        mServicw = commo.getAPI();
        db=new Database(daily_report.this);

        s_tot = (TextView) findViewById(R.id.sub_tot_all);
        btn_view=findViewById(R.id.bt_view);
        ex_confrom=findViewById(R.id.btn_confrom);
        e_amount = (EditText) findViewById(R.id.ex_amount);
        e_details = (EditText) findViewById(R.id.ex_details);



        try {

            //  int cre_id =collection.cr_id;
            int uid = Integer.parseInt(LoginActivity.uid);


            String stotal = db.getuserDily_Payment_tot(uid);

            s_tot.setText(stotal);



        }catch (Exception e){

        }



        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Intent i = new Intent(crdit_manage.this, PrinterActivity.class);
                Intent i = new Intent(daily_report.this, daily_report_view.class);
                // i.putExtra("crdit_id", crdit_id);
                startActivity(i);


            }
        });


        ex_confrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(daily_report.this);
                builder.setMessage("Submit Expenses").setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        g_amount = e_amount.getText().toString();
                        g_details = e_details.getText().toString();

                        if(g_amount.equals("")){
                            Toast.makeText(daily_report.this, "Please Enter Amount.", Toast.LENGTH_SHORT).show();
                        }else
                        if(g_details.equals("")){
                            Toast.makeText(daily_report.this, "Please Enter Details.", Toast.LENGTH_SHORT).show();
                        }else{

                            e_amount.setText("");
                            e_details.setText("");
                            new DownloadExprenses().execute();
                        }



                    }
                })
                        .setNegativeButton("Cancel",null) ;
                AlertDialog alt =   builder.create();
                alt.show();

            }



        });
    }





//==========================================================================================



    class DownloadExprenses extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;



        protected void onPreExecute () {




            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {
                // String uid = LoginActivity.uid;
                // System.out.println("area_id " + area_id);
                String token="525252";
                String merchantURL = commo.BASE_URL+"add_exprenses.php";
                String g_Date  = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                HashMap<String, String> params = new HashMap<>();

                String user_idUser = LoginActivity.uid;
                params.put("token", token);
                params.put("Amount", g_amount);
                params.put("Details", g_details);
                params.put("Date", g_Date);
                params.put("user_idUser", user_idUser);






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

                        Toast.makeText(daily_report.this, "Failed to register expenses.", Toast.LENGTH_SHORT).show();


                    }else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(daily_report.this);
                        builder.setMessage("Expenses successfully registered!").setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {





                                //Toast.makeText(debitors_reg.this, "Customer Register Success.", Toast.LENGTH_SHORT).show();

                                Intent in = new Intent(daily_report.this, main_menu.class);
                                startActivity(in);

                      /*  JSONArray arr = json_object.getJSONArray("get_debetor");
                        String[] debetors = new String[arr.length()];*/
                        /*crdit_ids = new String[arr.length()];
                        int[] ids = new int[arr.length()];
*/

                        /*for (int i = 0; i < arr.length(); i++) {
                            JSONObject getdebetors = arr.getJSONObject(i);
                            debetors[i] = getdebetors.getString("debetor");
                            crdit_ids[i] = getdebetors.getString("id");
                            //  Log.d("assss-----------",aria.getString("Area"));

                        }*/


                            }
                        }) ;
                        AlertDialog alt =   builder.create();
                        alt.show();


                    }








                } catch (JSONException e) {

                    e.printStackTrace();
                    System.out.println("Expenses download error " + e.getMessage());
                }

            }
        }



    }
}
