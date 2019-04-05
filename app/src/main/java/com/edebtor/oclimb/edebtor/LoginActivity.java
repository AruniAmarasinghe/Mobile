package com.edebtor.oclimb.edebtor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.edebtor.oclimb.edebtor.Common.commo;
import com.edebtor.oclimb.edebtor.Model.APIRespose;
//import com.edebtor.oclimb.edebtor.Model.credit_report;
import com.edebtor.oclimb.edebtor.Remote.IMyAPI;
import com.edebtor.oclimb.edebtor.utill.Database;

//import org.w3c.dom.Text;
//import javax.sql.CommonDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.app.ProgressDialog;


public class LoginActivity extends AppCompatActivity {

    public static String uid;
    EditText et_uname,et_pass;
    Button btn_login;
    IMyAPI mServicw;

    String lo_result;


    Database db;

    Context context=this;

    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mServicw = commo.getAPI();
        db=new Database(LoginActivity.this);

        mServicw = commo.getAPI();
        et_uname = (EditText) findViewById(R.id.et_uname);
        et_pass = (EditText) findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);

        lo_result ="0";

        //progress bar
        mProgress = new ProgressDialog(context);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(LoginActivity.this, crdit_manage.class);
                //startActivity(i);
                mProgress.show();
                try {

                    lo_result = db.user_login(et_uname.getText().toString(),et_pass.getText().toString());

                }catch (Exception e){

                }

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                android.net.NetworkInfo wifi = cm
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                android.net.NetworkInfo datac = cm
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if ((wifi != null & datac != null)
                        && (wifi.isConnected() | datac.isConnected())) {

                    authenticaUser(et_uname.getText().toString(),et_pass.getText().toString());

                }else if(lo_result != "0"){

                    //Toast.makeText(LoginActivity.this, "Login Success.", Toast.LENGTH_SHORT).show();
                    mProgress.dismiss();
                    uid = lo_result;
                    Intent i = new Intent(LoginActivity.this, loading.class);
                    startActivity(i);


                }else{

                    mProgress.dismiss();
                    AlertDialog.Builder altdial= new AlertDialog.Builder(LoginActivity.this);
                    altdial.setMessage("No Internet Connection. Please check your connection and try again.").setCancelable(false)

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                    AlertDialog alert= altdial.create();
                    alert.show();
                }


                /* authenticaUser(et_uname.getText().toString(),et_pass.getText().toString());*/


            }
        });
    }
    private void authenticaUser(String uname, String pass) {

        mServicw.loginUser(uname,pass)
                .enqueue(new Callback<APIRespose>() {
                    @Override
                    public void onResponse(Call<APIRespose> call, Response<APIRespose> response) {
                        APIRespose result = response.body();
                        if(result.isError()){
                            mProgress.dismiss();
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(LoginActivity.this);

                            dlgAlert.setMessage("Wrong Username or Password. Please try again.");
                            dlgAlert.setTitle("Error");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();

                            dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                            //Toast.makeText(LoginActivity.this, result.getError_msg(), Toast.LENGTH_SHORT).show();
                        }else{
                            mProgress.dismiss();
                            //Toast.makeText(LoginActivity.this, "Login Success.", Toast.LENGTH_SHORT).show();
                            uid = result.getIdUser();
                            Intent i = new Intent(LoginActivity.this, loading.class);
                            startActivity(i);
                        }

                    }

                    @Override
                    public void onFailure(Call<APIRespose> call, Throwable t) {

                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
