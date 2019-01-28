package com.edebtor.oclimb.edebtor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.edebtor.oclimb.edebtor.printers.PrinterActivity;
import com.edebtor.oclimb.edebtor.utill.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class collection extends AppCompatActivity {
    public static String msg;
    public static int cr_id;
    int crdit_id;
    Button btn_print, btn_histry;
    EditText installment_text,last_payement_date,due_date,total_amount,sub_total,additional_amount,paid_amount,total_granted_amount,se_granted_amount,get_Pno1;
    Spinner getselectdate;
    //String installment_text,due_date,total_amount,sub_total,additional_amount,match_parent;
    String get_installment_text,get_last_payement_date,get_due_date,get_total_amount,get_sub_total,get_additional_amount,get_paid_amount,get_PayFor,get_GrantAmount,get_duration,get_PaidAmount,get_due;
    double instalment_payemet,need_payement;


    Context context=this;

    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        db=new Database(collection.this);



        installment_text = (EditText) findViewById(R.id.installment_text);
        due_date = (EditText) findViewById(R.id.due_date);
        last_payement_date = (EditText) findViewById(R.id.last_payement_date);
        total_amount = (EditText) findViewById(R.id.total_amount);
        additional_amount = (EditText) findViewById(R.id.additional_amount);
        // paid_amount = (EditText) findViewById(R.id.paid_amount);
        sub_total = (EditText) findViewById(R.id.sub_total);

        total_granted_amount = (EditText) findViewById(R.id.total_granted_amount);
        se_granted_amount = (EditText) findViewById(R.id.granted_amount);
        /*  get_Pno1 = (EditText) findViewById(R.id.cPno1);*/
        getselectdate = (Spinner) findViewById(R.id.select_days);

        Bundle bundle = getIntent().getExtras();

        // crdit_id =bundle.getString("crdit_id"); get_Pno1;

        crdit_id= Integer.parseInt(crdit_manage.crdit_id);
        cr_id = crdit_id;
        //  new crditDe().execute();

        crditDe(crdit_id);
        setdates();




        getselectdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                if(pos != 7) {

                    Double Dates = Double.parseDouble(String.valueOf(pos))+1;
                    //  get_installment_text = installment_text.getText().toString();
                    get_PayFor = String.valueOf(pos)+1;
                    sub_total.setText(String.valueOf(instalment_payemet * Dates));


                }else{
                    get_PayFor = "0";
                    sub_total.setText(String.valueOf(need_payement));
                }

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btn_histry=findViewById(R.id.btn_histry);
        btn_histry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Intent i = new Intent(crdit_manage.this, PrinterActivity.class);
                Intent i = new Intent(collection.this, cridit_histry.class);
                // i.putExtra("crdit_id", crdit_id);
                startActivity(i);


            }
        });

        btn_print=findViewById(R.id.btn_print);

        btn_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(collection.this);
                builder.setMessage("Do you want to save the payment?").setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String get_installment_text, get_last_payement_date, get_due_date, get_total_amount, get_sub_total, get_additional_amount, get_paid_amount;

               /* installment_text = (EditText) findViewById(R.id.installment_text);
                due_date = (EditText) findViewById(R.id.due_date);
                last_payement_date = (EditText) findViewById(R.id.last_payement_date);
                total_amount = (EditText) findViewById(R.id.total_amount);
                additional_amount = (EditText) findViewById(R.id.additional_amount);
              //  paid_amount = (EditText) findViewById(R.id.paid_amount);
                sub_total = (EditText) findViewById(R.id.sub_total);*/

                        // String idInvoice_Payments =arr_user.getString("idInvoice_Payments");

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Calendar cal = Calendar.getInstance();

                        String curent_date = dateFormat.format(cal.getTime());

                        //String DateTime =arr_user.getString("DateTime");
                        String idInvoice_Payments = "1";
                        String Amount = sub_total.getText().toString();
                        String AdditionalAmount = additional_amount.getText().toString();
                        String g_installment_text = installment_text.getText().toString();
                        //String g_sub_total =sub_total.getText().toString();
                        String g_total_amount = total_amount.getText().toString();

                        String DateTime = dateFormat.format(cal.getTime());

                        String PayFor = get_PayFor;
                        String Credit_Invoice_idCredit_Invoice = String.valueOf(crdit_id);
                        String user_idUser = LoginActivity.uid;
                        String Status = "1";
                        String sync_status = "1";


                        if (Amount.equals("")) {
                            Toast.makeText(collection.this, "Please Enter Sub Total.", Toast.LENGTH_SHORT).show();
                        } else if (AdditionalAmount.equals("")) {
                            Toast.makeText(collection.this, "Please Enter Additional Amount.", Toast.LENGTH_SHORT).show();
                        } else if (PayFor.equals("")) {
                            Toast.makeText(collection.this, "Please Enter PayFor.", Toast.LENGTH_SHORT).show();
                        } else{


                            db.invoice_payments_data(idInvoice_Payments, Amount, AdditionalAmount, DateTime, PayFor, Credit_Invoice_idCredit_Invoice, user_idUser, Status, sync_status);

                            Toast.makeText(collection.this, "Payment Success.", Toast.LENGTH_SHORT).show();

                            // String insallment_p ="200";
                            String getcrdit_cust= crdit_manage.crdit_cust;

                            // System.out.println("crdit_cust -download " + getcrdit_cust);

                            msg = "\n KDG micro credits    \n " + curent_date + " \n Loan No : " + Credit_Invoice_idCredit_Invoice + "\n"+ getcrdit_cust +" \n ----------------------------- \n Rental : Rs." + g_installment_text + " \n Loan Amount : Rs." + get_GrantAmount + "\n Duration : " + get_duration + " \n ----------------------------- \n" +
                                    " Total Paiyed : Rs." + get_PaidAmount + "\n Total Due : Rs." + get_due + "\n Today Paid : Rs." + g_total_amount + " \n ----------------------------- \n Hotline - 071 26 27 176 \n \n   Powerd by Oclimb Solution \n        071 122 68 18 \n";
                            // Intent i = new Intent(crdit_manage.this, PrinterActivity.class);
                            Intent ii = new Intent(collection.this, PrinterActivity.class);

                            startActivity(ii);


                        }


                    }
                }).setNegativeButton("cancel",null) ;
                AlertDialog alt =   builder.create();
                alt.show();


            }
        });


        additional_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(getCurrentFocus() == additional_amount) {
                    if (!charSequence.toString().isEmpty() || !charSequence.toString().equals("")) {

                        String Amount = sub_total.getText().toString();

                        double g_sub_total = Double.parseDouble(sub_total.getText().toString());

                        double g_interest_val = Double.parseDouble(charSequence.toString());

                        total_amount.setText(String.valueOf(g_sub_total + g_interest_val));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    //==========================================================================================

    private void setcrditDe(String installment,String lastDate,int dateDiff) {

        installment_text = (EditText) findViewById(R.id.installment_text);
        installment_text.setText(installment);

       /* if(dateDiff >1){

        }*/
        // Toast.makeText(collection.this, "Load getDebitor value", Toast.LENGTH_SHORT).show();
    }

    private void setdates() {

        String[] xs = new String[] {"1 Days","2 Days","3 Days","4 Days","5 Days","6 Days","7 Days","Full Amount"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, xs
        );


        //   spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        getselectdate.setAdapter(spinnerArrayAdapter);

        //  Toast.makeText(crdit_manage.this, "Load area", Toast.LENGTH_SHORT).show();
    }
    //==========================================================================================


   /* class crditDe extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;



        protected void onPreExecute () {




            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {
                String uid = LoginActivity.uid;
                //System.out.println("uid " + uid);
                String merchantURL = commo.BASE_URL+"GetDailyPayment.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("cid", crdit_id);


               // System.out.println("aaaaaaaaaaa"+crdit_id);

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
                System.out.println("ttttt download11 " + json_object.toString());


                try {
                    boolean error = json_object.getBoolean("error");
                if(error){

                  }else {

                      //  System.out.println("aaaaaaaaaaa"+crdit_id);
                        String installment = json_object.getString("amount");
                        String lastDate = json_object.getString("error_msg");
                        int dateDiff = json_object.getInt("dateDiff");
                     //System.out.println("aaaaaaaaaaa"+installment);
                        setcrditDe(installment,lastDate,dateDiff);

                    }



                } catch (JSONException e) {

                    e.printStackTrace();
                    System.out.println("ttttt download error " + e.getMessage());
                }

            }
        }



    }*/

    // ================================================================================

    private void  crditDe(int crdit_id){

        try {
            //   int uid = Integer.parseInt(LoginActivity.uid);
            String payment[] = db.getinvoice_payment(crdit_id);

           /* payment[0] = get_DailyEqualPayment;
            payment[1] = getTotal_amount;
            payment[2] = get_DateTime;
            payment[3] = due_date;
            payment[4] = get_allTotalAmount;
            payment[5] = get_GrantAmount;
            payment[6] = get_Days;
            payment[7] = String.valueOf(get_PaidAmount);*/

            String DailyPaymentAmount =  payment[0];
            String paid_total_amount =  payment[1];
            String last_payement =  payment[2];
            String payemet_due_date =  payment[3];
            String allTotalAmount =  payment[4];
            get_GrantAmount =  payment[5];
            get_duration =payment[6];
            get_PaidAmount =payment[7];


            installment_text.setText(DailyPaymentAmount);

            total_granted_amount.setText(allTotalAmount);
            se_granted_amount.setText(get_GrantAmount);

            due_date.setText(payemet_due_date);
            total_amount.setText(DailyPaymentAmount);
            last_payement_date.setText(last_payement);
            //get_Pno1.setText(c_Pno1);
            //sub_total.setText(DailyPaymentAmount);


            instalment_payemet = Double.parseDouble(DailyPaymentAmount);
            need_payement = Double.parseDouble(allTotalAmount) - instalment_payemet;

            get_due = String.valueOf(Double.parseDouble(allTotalAmount) - Double.parseDouble(get_PaidAmount) );


        }catch (Exception e){
        }

    }
}
