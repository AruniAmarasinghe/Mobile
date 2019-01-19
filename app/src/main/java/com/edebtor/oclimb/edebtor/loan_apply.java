package com.edebtor.oclimb.edebtor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.edebtor.oclimb.edebtor.Common.ServiceHandler;
import com.edebtor.oclimb.edebtor.Common.commo;
import com.edebtor.oclimb.edebtor.Model.two_item;
import com.edebtor.oclimb.edebtor.utill.Database;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class loan_apply extends AppCompatActivity {

    EditText apply_amount, interest_rate, days, daily_payment, interest_total, g1_nic, g1_faname, g1_laname, g1_phno1, g1_phno2, g1_address1, g1_address2, g2_nic, g2_faname, g2_laname, g2_phno1, g2_phno2, g2_address1, g2_address2;
    Spinner Area_value, Debitor_value;
    Button btn_add;

    String[] area_ids;
    String[] Debetor_ids;
    String  get_g1_nic, get_g1_faname, get_g1_laname, get_g1_phno1, get_g1_phno2, get_g1_address1, get_g1_address2, get_g2_nic, get_g2_faname, get_g2_laname, get_g2_phno1, get_g2_phno2, get_g2_address1, get_g2_address2;
    String getArea_value, getDebitor_value, getapply_amount, getinterest_rate, getdays, getdaily_payment, getinterest_total;
    Database db;

    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_apply);

        btn_add = findViewById(R.id.btn_apply_loan);

        apply_amount = (EditText) findViewById(R.id.apply_amount);
        interest_rate = (EditText) findViewById(R.id.interest_rate);
        days = (EditText) findViewById(R.id.days);
        daily_payment = (EditText) findViewById(R.id.daily_payment);
        interest_total = (EditText) findViewById(R.id.interest_total);

        Area_value = (Spinner) findViewById(R.id.select_area);
        Debitor_value = (Spinner) findViewById(R.id.select_debetor);

        g1_nic = (EditText) findViewById(R.id.g1_nic);
        g1_faname = (EditText) findViewById(R.id.g1_faname);
        g1_laname = (EditText) findViewById(R.id.g1_laname);
        g1_phno1 = (EditText) findViewById(R.id.g1_phno1);
        g1_phno2 = (EditText) findViewById(R.id.g1_phno2);
        g1_address1 = (EditText) findViewById(R.id.g1_address1);
        g1_address2 = (EditText) findViewById(R.id.g1_address2);

        g2_nic = (EditText) findViewById(R.id.g2_nic);
        g2_faname = (EditText) findViewById(R.id.g2_faname);
        g2_laname = (EditText) findViewById(R.id.g2_laname);
        g2_phno1 = (EditText) findViewById(R.id.g2_phno1);
        g2_phno2 = (EditText) findViewById(R.id.g2_phno2);
        g2_address1 = (EditText) findViewById(R.id.g2_address1);
        g2_address2 = (EditText) findViewById(R.id.g2_address2);


        Area_value.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                if(area_ids != null) {
                    getArea_value = area_ids[pos];

                }

                // System.out.println("crdit_ids[pos] download " + crdit_ids[pos]);
                // Toast.makeText(crdit_manage.this, crdit_ids[pos], Toast.LENGTH_SHORT).show();

            }
            public void onNothingSelected(AdapterView<?> parent) {
                //  Toast.makeText(crdit_manage.this, "Spinner1: unselected", Toast.LENGTH_SHORT).show();
            }
        });

        Debitor_value.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                if(Debetor_ids != null) {
                    getDebitor_value = Debetor_ids[pos];


                }


                // System.out.println("crdit_ids[pos] download " + crdit_ids[pos]);
                // Toast.makeText(crdit_manage.this, crdit_ids[pos], Toast.LENGTH_SHORT).show();

            }
            public void onNothingSelected(AdapterView<?> parent) {
                //  Toast.makeText(crdit_manage.this, "Spinner1: unselected", Toast.LENGTH_SHORT).show();
            }
        });



        db=new Database(loan_apply.this);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(loan_apply.this);
                builder.setMessage("Loan Apply").setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        getapply_amount = apply_amount.getText().toString();
                        getinterest_rate = interest_rate.getText().toString();
                        getdays = days.getText().toString();
                        getdaily_payment = daily_payment.getText().toString();
                        getinterest_total = interest_total.getText().toString();

                        get_g1_nic = g1_nic.getText().toString();
                        get_g1_faname = g1_faname.getText().toString();
                        get_g1_laname = g1_laname.getText().toString();
                        get_g1_phno1 = g1_phno1.getText().toString();
                        get_g1_phno2 = g1_phno2.getText().toString();
                        get_g1_address1 = g1_address1.getText().toString();
                        get_g1_address2 = g1_address2.getText().toString();

                        get_g2_nic = g2_nic.getText().toString();
                        get_g2_faname = g2_faname.getText().toString();
                        get_g2_laname = g2_laname.getText().toString();
                        get_g2_phno1 = g2_phno1.getText().toString();
                        get_g2_phno2 = g2_phno2.getText().toString();
                        get_g2_address1 = g2_address1.getText().toString();
                        get_g2_address2 = g2_address2.getText().toString();



                        if(getapply_amount.equals("")){
                            Toast.makeText(loan_apply.this, "Please Enter Amount.", Toast.LENGTH_SHORT).show();
                        }else
                        if(getinterest_rate.equals("")){
                            Toast.makeText(loan_apply.this, "Please Enter Interest Rate.", Toast.LENGTH_SHORT).show();
                        }else
                        if(getdays.equals("")){
                            Toast.makeText(loan_apply.this, "Please Enter Days.", Toast.LENGTH_SHORT).show();
                        }else

                        if(getdaily_payment.equals("")){
                            Toast.makeText(loan_apply.this, "Please Enter Daily Payment.", Toast.LENGTH_SHORT).show();
                        }else
                        if(getinterest_total.equals("")){
                            Toast.makeText(loan_apply.this, "Please Enter Total with Interest.", Toast.LENGTH_SHORT).show();
                        }else



                        if(get_g1_nic.equals("")){
                            Toast.makeText(loan_apply.this, "Please Enter NIC.", Toast.LENGTH_SHORT).show();
                        }else
                        if(get_g1_faname.equals("")){
                            Toast.makeText(loan_apply.this, "Please Enter First Name.", Toast.LENGTH_SHORT).show();
                        }else
                        if(get_g1_laname.equals("")){
                            Toast.makeText(loan_apply.this, "Please Enter Last Name.", Toast.LENGTH_SHORT).show();
                        }else

                        if(get_g1_address1.equals("")){
                            Toast.makeText(loan_apply.this, "Please Enter Address.", Toast.LENGTH_SHORT).show();
                        }else

                        if(get_g1_phno1.equals("")){
                            Toast.makeText(loan_apply.this, "Please Enter Phone No.", Toast.LENGTH_SHORT).show();
                        }else


                        {


                            if(get_g1_address2.equals("")){
                                get_g1_address2 = " ";
                            }
                            if(get_g1_phno2.equals("")){
                                get_g1_phno2 = " ";
                            }

                            if(get_g2_phno2.equals("")){
                                get_g2_phno2 = " ";
                            }
                            if(get_g2_address2.equals("")){
                                get_g2_address2 = " ";
                            }

                            if(get_g2_nic.equals("")){
                                get_g2_nic = "no";
                            }
                            if(get_g2_faname.equals("")){
                                get_g2_faname = "no";
                            }
                            if(get_g2_laname.equals("")){
                                get_g2_laname = "no";
                            }
                            if(get_g2_address1.equals("")){
                                get_g2_address1 = "no";
                            }

                            if(get_g2_phno1.equals("")){
                                get_g2_phno1 = "no";
                            }

                            new DownloadApply_loan().execute();
                        }

                    }
                }).setNegativeButton("cancel",null) ;
                AlertDialog alt =   builder.create();
                alt.show();

            }
        });

        DownloadDebitor();
        DownloadArea();

        interest_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence interest_val, int i, int i1, int i2) {

                if(getCurrentFocus() == interest_rate) {
                    if (!interest_val.toString().equals("")) {

                        double g_apply_amount = Double.parseDouble(apply_amount.getText().toString());
                        double g_days = Double.parseDouble(days.getText().toString());
                        double g_interest_val = Double.parseDouble(interest_val.toString());


                        double total_interest = ((((g_apply_amount / 100) * g_interest_val) / 30) * g_days) + g_apply_amount;
                        double daily_payment_val = (((((g_apply_amount / 100) * g_interest_val) / 30) * g_days) + g_apply_amount) / g_days;

                        // System.out.println("qqqqw1--"+daily_payment_val);

                        String st_daily_payment_val = String.valueOf(daily_payment_val);
                        String st_total_interest = String.valueOf(total_interest);

                        daily_payment.setText(st_daily_payment_val);
                        interest_total.setText(st_total_interest);

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable interest_val) {



            }
        });


        //-------------------------------------------------------------------------------

        daily_payment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence daily_payment_val, int i, int i1, int i2) {

                if(getCurrentFocus() == daily_payment)
                {

                    if (!daily_payment_val.toString().equals("")) {

                        double g_apply_amount = Double.parseDouble(apply_amount.getText().toString());
                        double g_days = Double.parseDouble(days.getText().toString());
                        double g_daily_payment_val = Double.parseDouble(daily_payment_val.toString());

                        double interest_val = (((((g_daily_payment_val * g_days) - g_apply_amount) / g_days) * 30) * 100) / g_apply_amount;

                        double total_interest = ((((g_apply_amount / 100) * interest_val) / 30) * g_days) + g_apply_amount;

                        // System.out.println("qqqqw1--" + String.valueOf(interest_val));

                        String st_interest_val = String.valueOf(interest_val);
                        String st_total_interest = String.valueOf(total_interest);

                        interest_rate.setText(st_interest_val);
                        interest_total.setText(st_total_interest);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable daily_payment_val) {



            }
        });

    }


    private void setAreaValue(String[] areac) {

        // System.out.println("oooook");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, areac
        );


        //  spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Area_value.setAdapter(spinnerArrayAdapter);

        Toast.makeText(loan_apply.this, "Load area", Toast.LENGTH_SHORT).show();
    }

    //==========================================================================================

    private void setDebitorValue(String[] debetors) {

        System.out.println(debetors);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, debetors
        );


        //     spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Debitor_value.setAdapter(spinnerArrayAdapter);

        Toast.makeText(loan_apply.this, "Load getDebtor value", Toast.LENGTH_SHORT).show();
    }

    //==========================================================================================


    private void DownloadArea(){

        try {
            //  int uid = Integer.parseInt(LoginActivity.uid);
            // System.out.println("pppppppppp");
            // ArrayList<two_item> list = db.getAllArea();
            //   System.out.println("pppppppppp");

            int uid = Integer.parseInt(LoginActivity.uid);
            //System.out.println("pppppppppp");
            ArrayList<two_item> list = db.getUserArea(uid);

            String[] area = new String[list.size()];
            area_ids= new String[list.size()];
            int i=0;

            for(two_item t:list) {
                //   System.out.println("--aaaaaa---"+t.getValue());


                //     System.out.println("--aaaaaa---"+i);
                area_ids[i]=t.getId();
                area[i]=t.getValue();
                //  System.out.println("--aaaaaa---"+i);
                i++;
                // Log.i("Value of element "+i, String.valueOf(list.get(i)));
            }

            //System.out.println("zzzzz-");
            setAreaValue(area);

        }catch (Exception e){

        }

    }

    private void DownloadDebitor(){

        try {
            //   int uid = Integer.parseInt(LoginActivity.uid);
            //System.out.println("pppppppppp1");
            ArrayList<two_item> list = db.getAlDebetor();
            //  System.out.println("pppppppppp");

            // System.out.println("pppppppppp1---"+list.size());



            if(list.size() > 0) {
                String[] debetors = new String[list.size()];
                Debetor_ids = new String[list.size()];
                int i=0;
                for (two_item t : list) {
                    //   System.out.println("--aaaaaa1---"+t.getValue());


                    //     System.out.println("--aaaaaa---"+i);
                    Debetor_ids[i] = t.getId();
                    debetors[i] = t.getValue();
                    //  System.out.println("--aaaaaa---"+i);
                    i++;
                    // Log.i("Value of element "+i, String.valueOf(list.get(i)));
                }
                setDebitorValue(debetors);
            }else{
                String[] debetors = new String[]{"No debtor in area"};
                setDebitorValue(debetors);
            }

            //System.out.println("zzzzz-");


        }catch (Exception e){
        }

    }




//==========================================================================================



    class DownloadApply_loan extends AsyncTask<String, Void, JSONObject>

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
                String uid =LoginActivity.uid;
                String merchantURL = commo.BASE_URL+"apply_loan.php";
                HashMap<String, String> params = new HashMap<>();


                params.put("token", token);
                params.put("TotalAmount", getinterest_total);
                params.put("GrantAmount", getapply_amount);
                params.put("InterestRate", getinterest_rate);
                params.put("DailyEqualPayment", getdaily_payment);
                params.put("Days", getdays);
                params.put("idDebitors", getDebitor_value);

                params.put("idArea", getArea_value);
                params.put("idUser", uid);




                params.put("g1fname", get_g1_faname);
                params.put("g1lname", get_g1_laname);
                params.put("g1phone1", get_g1_phno1);
                params.put("g1phone2", get_g1_phno2);
                params.put("g1address1", get_g1_address1);
                params.put("g1address2", get_g1_address2);
                params.put("g1nic", get_g1_nic);


                params.put("g2fname", get_g2_faname);
                params.put("g2lname", get_g2_laname);
                params.put("g2phone1", get_g2_phno1);
                params.put("g2phone2", get_g2_phno2);
                params.put("g2address1", get_g2_address1);
                params.put("g2address2", get_g2_address2);
                params.put("g2nic", get_g2_nic);




                ServiceHandler sh = new ServiceHandler(context);
                json = sh.makeHttpRequest(merchantURL, ServiceHandler.POST, params);
                //System.out.println("ttttt download " + json);


                jObj = new JSONObject(json);
            } catch (Exception e) {
                System.out.println("ttttt download " + e.getMessage());
            }

            return jObj;

        }

        protected void onPostExecute (JSONObject json_object){


            if (json_object != null) {
                System.out.println("ttttt de " + json_object.toString());


                try {

                    boolean error = json_object.getBoolean("error");

                    if(error){
                        String errormsg = json_object.getString("error_msg");
                        //  String[] debetors = new String[]{"No debitor in area"};

                        Toast.makeText(loan_apply.this, "Loan Apply Failed.", Toast.LENGTH_SHORT).show();

                    }else {
                        //Log.d("error_val",error+" "+errormsg);

                        String errormsg = json_object.getString("loan_de");

                        System.out.println("loan message " + errormsg);

                        Toast.makeText(loan_apply.this, "Loan Apply Success.", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(loan_apply.this, main_menu.class);
                        startActivity(i);

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








                } catch (JSONException e) {

                    e.printStackTrace();
                    System.out.println("ttttt download error " + e.getMessage());
                }

            }
        }



    }

}
