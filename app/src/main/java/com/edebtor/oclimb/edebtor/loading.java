package com.edebtor.oclimb.edebtor;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.edebtor.oclimb.edebtor.Common.ServiceHandler;
import com.edebtor.oclimb.edebtor.Common.commo;
import com.edebtor.oclimb.edebtor.Model.payment_de;
import com.edebtor.oclimb.edebtor.Remote.IMyAPI;
import com.edebtor.oclimb.edebtor.utill.ConnectionDetector;
import com.edebtor.oclimb.edebtor.utill.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class loading extends AppCompatActivity {


    String   uid,dailyPay,payFor,addAmount,paidAmount,cid;
    private ProgressBar progressbar;
    private int prograssStatus =0;
    private TextView prograsseTimer;

    private Handler handler = new Handler();

    Database db;
    Context context=this;
    ConnectionDetector cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        db=new Database(loading.this);

        cd = new ConnectionDetector(context);

        boolean isconnect = cd.isConnectingToInternet();
        if(isconnect) {

            connection();

        }else{

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(2000);

                        Intent i = new Intent(loading.this, main_menu.class);
                        startActivity(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }).start();



        }

    }

    private  void connection(){

        new Download_userde().execute();
        new Download_debitors_data().execute();
        new Download_invoice_payments_data().execute();
        new Download_credit_invoice_data().execute();
        new Download_collection_area_user().execute();
        new Download_collectionarea().execute();
        new Download_exprenses().execute();

        int syncCount = db.checkSync();

        if(syncCount > 0){

            String re_val =  uploadPayement();

            if(re_val == "succes" ){

                db.removeTablevalue();
                new Download_userde().execute();
                new Download_debitors_data().execute();
                new Download_invoice_payments_data().execute();
                new Download_credit_invoice_data().execute();
                new Download_collection_area_user().execute();
                new Download_collectionarea().execute();
                new Download_exprenses().execute();
            }

        }else {


            db.removeTablevalue();


            new Download_userde().execute();
            new Download_debitors_data().execute();
            new Download_invoice_payments_data().execute();
            new Download_credit_invoice_data().execute();
            new Download_collection_area_user().execute();
            new Download_collectionarea().execute();
            new Download_exprenses().execute();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);

                    Intent i = new Intent(loading.this, main_menu.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }).start();

    }

    class Download_userde extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;

        protected void onPreExecute () {

            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {

                String token="525252";

                String merchantURL = commo.BASE_URL+"user_de.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("token", token);

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
                    JSONArray arr=json_object.getJSONArray("user_de");
                    int[] ids = new int[arr.length()];

                    for (int i = 0; i <arr.length() ; i++) {
                        JSONObject arr_user=arr.getJSONObject(i);

                        String idUser =arr_user.getString("idUser");
                        String Uname =arr_user.getString("Uname");
                        String Pass =arr_user.getString("Pass");
                        String User_Type_idUser_Type =arr_user.getString("User_Type_idUser_Type");
                        String User_Details_idUser_Details =arr_user.getString("User_Details_idUser_Details");
                        String Status =arr_user.getString("Status");

                        db.user_data(idUser,Uname,Pass,User_Type_idUser_Type,User_Details_idUser_Details,Status);


                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                    System.out.println("ttttt download error " + e.getMessage());
                }

            }
        }



    }


    class Download_collection_area_user extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;



        protected void onPreExecute () {

            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {

                String token="525252";
                String merchantURL = commo.BASE_URL+"collection_area_user_new.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("token", token);

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
                    JSONArray arr=json_object.getJSONArray("area_user_de");

                    int[] ids = new int[arr.length()];
                    Log.d("assss-A-----------",ids.toString());
                    for (int i = 0; i <arr.length() ; i++) {
                        JSONObject arr_user=arr.getJSONObject(i);

                        String idCollection_Area_User =arr_user.getString("idCollection_Area_User");
                        String CollectionArea_idCollectionArea =arr_user.getString("CollectionArea_idCollectionArea");
                        String User_idUser =arr_user.getString("User_idUser");

                        db.collection_area_user_data(idCollection_Area_User,CollectionArea_idCollectionArea,User_idUser);

                        Log.d("assss1-----------",User_idUser);

                    }


                } catch (JSONException e) {

                    e.printStackTrace();

                }

            }
        }



    }


    class Download_credit_invoice_data extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;



        protected void onPreExecute () {
            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {

                String token="525252";

                String merchantURL = commo.BASE_URL+"credit_invoice.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("token", token);

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
                    JSONArray arr=json_object.getJSONArray("crdit_de");
                    int[] ids = new int[arr.length()];
                    Log.d("assss-ci-----------",ids.toString());
                    for (int i = 0; i <arr.length() ; i++) {
                        JSONObject arr_user=arr.getJSONObject(i);

                        String idCredit_Invoice =arr_user.getString("idCredit_Invoice");
                        String TotalAmount =arr_user.getString("TotalAmount");
                        String GrantAmount =arr_user.getString("GrantAmount");
                        String InterestRate =arr_user.getString("InterestRate");
                        String DailyEqualPayment =arr_user.getString("DailyEqualPayment");
                        String Days =arr_user.getString("Days");
                        String PaidAmount =arr_user.getString("PaidAmount");
                        String PenaltyPaid =arr_user.getString("PenaltyPaid");
                        String Settled =arr_user.getString("Settled");
                        String DateTime =arr_user.getString("DateTime");
                        String Debitors_idDebitors =arr_user.getString("Debitors_idDebitors");
                        String CollectionArea_idCollectionArea =arr_user.getString("CollectionArea_idCollectionArea");
                        String user_idUser =arr_user.getString("user_idUser");
                        String Status =arr_user.getString("Status");
                        String sync_status ="0";


                        db.credit_invoice_data(idCredit_Invoice,TotalAmount,GrantAmount,InterestRate,DailyEqualPayment,Days,PaidAmount,PenaltyPaid,
                                Settled,DateTime,Debitors_idDebitors,CollectionArea_idCollectionArea,user_idUser,Status,sync_status);


                    }

                } catch (JSONException e) {

                    e.printStackTrace();

                }

            }
        }



    }



//==========================================================================================


    class Download_invoice_payments_data extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;



        protected void onPreExecute () {

            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {

                String token="525252";
                String merchantURL = commo.BASE_URL+"invoice_payments.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("token", token);

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
                    JSONArray arr=json_object.getJSONArray("invoice_de");
                    int[] ids = new int[arr.length()];
                    Log.d("assss-ip-----------",ids.toString());
                    for (int i = 0; i <arr.length() ; i++) {
                        JSONObject arr_user=arr.getJSONObject(i);

                        String idInvoice_Payments =arr_user.getString("idInvoice_Payments");
                        String Amount =arr_user.getString("Amount");
                        String AdditionalAmount =arr_user.getString("AdditionalAmount");
                        String DateTime =arr_user.getString("DateTime");
                        String PayFor =arr_user.getString("PayFor");
                        String Credit_Invoice_idCredit_Invoice =arr_user.getString("Credit_Invoice_idCredit_Invoice");
                        String user_idUser =arr_user.getString("user_idUser");
                        String Status =arr_user.getString("Status");
                        String sync_status ="0";

                        db.invoice_payments_data(idInvoice_Payments,Amount,AdditionalAmount,DateTime,PayFor,Credit_Invoice_idCredit_Invoice,user_idUser,Status,sync_status);

                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }



    }


//==========================================================================================


    class Download_debitors_data extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;



        protected void onPreExecute () {

            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {

                String token="525252";
                String merchantURL = commo.BASE_URL+"debitors.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("token", token);

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
                    JSONArray arr=json_object.getJSONArray("debitors_de");
                    int[] ids = new int[arr.length()];
                    Log.d("assss-d-----------",ids.toString());
                    for (int i = 0; i <arr.length() ; i++) {
                        JSONObject arr_user=arr.getJSONObject(i);

                        String idDebitors =arr_user.getString("idDebitors");
                        String NIC =arr_user.getString("NIC");
                        String Fname =arr_user.getString("Fname");
                        String Lname =arr_user.getString("Lname");
                        String Address1 =arr_user.getString("Address1");
                        String Address2 =arr_user.getString("Address2");
                        String Pno1 =arr_user.getString("Pno1");
                        String Pno2 =arr_user.getString("Pno2");
                        String Email ="-";
                        String Status =arr_user.getString("Status");
                        String sync_status ="0";

                        db.debitors_data(idDebitors,NIC,Fname,Lname,Address1,Address2,Pno1,Pno2,Email,Status,sync_status);


                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }



    }


//==========================================================================================


    class Download_collectionarea extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;



        protected void onPreExecute () {

            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {

                String token="525252";
                String merchantURL = commo.BASE_URL+"collectionarea.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("token", token);

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
                    JSONArray arr=json_object.getJSONArray("area_de");

                    int[] ids = new int[arr.length()];
                    Log.d("assss-area-----------",ids.toString());
                    for (int i = 0; i <arr.length() ; i++) {
                        JSONObject arr_user=arr.getJSONObject(i);

                        String idCollectionArea =arr_user.getString("idCollectionArea");
                        String CollectionArea =arr_user.getString("CollectionArea");

                        String Status =arr_user.getString("Status");

                        db.collection_area_data(idCollectionArea,CollectionArea,Status);


                    }


                } catch (JSONException e) {

                    e.printStackTrace();

                }

            }
        }



    }


//==========================================================================================

    private String uploadPayement(){

        try {

            ArrayList<payment_de> list = db.SetnewPayment();

            if(list.size() > 0) {

                int i=0;
                for (payment_de t : list) {

                    uid = t.getUserid();
                    dailyPay = t.getDailyPay();
                    payFor = t.getPayFor();
                    addAmount = t.getAddAmount();
                    paidAmount = t.getPaidAmount();
                    cid = t.getCid();

                    new uploadPayement().execute();

                    i++;

                }

            }else{

            }



        }catch (Exception e){
        }

        return "succes";
    }

    //==========================================================================================



    class uploadPayement extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;

        protected void onPreExecute () {
            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {

                String token="525252";
                String merchantURL = commo.BASE_URL+"invoice_payment_add.php";
                HashMap<String, String> params = new HashMap<>();


                params.put("token", token);
                params.put("uid", uid);
                params.put("dailyPay", dailyPay);
                params.put("payFor", payFor);
                params.put("addAmount", addAmount);
                params.put("paidAmount", paidAmount);
                params.put("cid", cid);

                ServiceHandler sh = new ServiceHandler(context);
                json = sh.makeHttpRequest(merchantURL, ServiceHandler.POST, params);
                System.out.println("aaaaaaaaaaaaaaaaaaaa download " + json);

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

                    }else {

                        String errormsg = json_object.getString("payement_de");


                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                    System.out.println("ttttt download error " + e.getMessage());
                }

            }
        }



    }
//==========================================================================================


    //===========================================================================================
    class Download_exprenses extends AsyncTask<String, Void, JSONObject>

    {
        private String json;
        private JSONObject jObj;

        protected void onPreExecute () {
            super.onPreExecute();

        }

        protected JSONObject doInBackground (String...args){

            try {
                String token="525252";
                String merchantURL = commo.BASE_URL+"se_exprenses.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("token", token);

                ServiceHandler sh = new ServiceHandler(context);
                json = sh.makeHttpRequest(merchantURL, ServiceHandler.POST, params);
                // System.out.println("ttttt download " + json);

                jObj = new JSONObject(json);
            } catch (Exception e) {
            }

            return jObj;

        }

        protected void onPostExecute (JSONObject json_object){


            if (json_object != null) {

                try {
                    JSONArray arr=json_object.getJSONArray("exprences_de");

                    int[] ids = new int[arr.length()];
                    for (int i = 0; i <arr.length() ; i++) {
                        JSONObject arr_user=arr.getJSONObject(i);

                        String id =arr_user.getString("idCollectorExpenses");
                        String amount =arr_user.getString("Amount");
                        String details =arr_user.getString("Details");
                        String date =arr_user.getString("Date");
                        String idUser =arr_user.getString("user_idUser");
                        String Status =arr_user.getString("Status");

                        db.CollectorExpenses_data(id,amount,details,date,idUser,Status);


                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                    System.out.println("exprenses download error " + e.getMessage());
                }

            }
        }



    }

}
