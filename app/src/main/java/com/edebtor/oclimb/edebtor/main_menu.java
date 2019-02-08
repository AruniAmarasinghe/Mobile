package com.edebtor.oclimb.edebtor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
//import com.edebtor.oclimb.edebtor.main_menu.webkit.WebView;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
//import android.app.ProgressDialog;


public class main_menu extends AppCompatActivity {
    Button btn_crtManage,new_loan,new_cus,my_profile,sync,logout,url;
    private android.webkit.WebView mWebView;
   // private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        btn_crtManage=findViewById(R.id.crt_manage);
        //commented for additional functionality
        //new_loan=findViewById(R.id.new_loan);
        //new_cus=findViewById(R.id.new_cus);
        //my_profile=findViewById(R.id.my_profile);
        url =findViewById(R.id.url);
        sync=findViewById(R.id.sync);
        logout=findViewById(R.id.logout);

        //progress bar
        /*Context context=this;
        mProgress = new ProgressDialog(context);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);*/


        btn_crtManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Intent i = new Intent(crdit_manage.this, PrinterActivity.class);
                Intent i = new Intent(main_menu.this, crdit_manage.class);
                // i.putExtra("crdit_id", crdit_id);
                startActivity(i);


            }
        });

//        new_loan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                android.net.NetworkInfo wifi = cm
//                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//                android.net.NetworkInfo datac = cm
//                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//                if ((wifi != null & datac != null)
//                        && (wifi.isConnected() | datac.isConnected())) {
//
//
//                    // Intent i = new Intent(crdit_manage.this, PrinterActivity.class);
//                    Intent i = new Intent(main_menu.this, loan_apply.class);
//                    // i.putExtra("crdit_id", crdit_id);
//                    startActivity(i);
//
//
//                }else{
//
//                    AlertDialog.Builder altdial= new AlertDialog.Builder(main_menu.this);
//                    altdial.setMessage("No Internet Connection").setCancelable(false)
//
//                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    dialogInterface.cancel();
//                                }
//                            });
//
//                    AlertDialog alert= altdial.create();
//                    /*alert.setTitle("Credit");*/
//                    alert.show();
//                }
//
//
//            }
//        });


//        new_cus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                android.net.NetworkInfo wifi = cm
//                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//                android.net.NetworkInfo datac = cm
//                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//                if ((wifi != null & datac != null)
//                        && (wifi.isConnected() | datac.isConnected())) {
//
//
//                    // Intent i = new Intent(crdit_manage.this, PrinterActivity.class);
//                    Intent i = new Intent(main_menu.this, debitors_reg.class);
//                    // i.putExtra("crdit_id", crdit_id);
//                    startActivity(i);
//
//
//                }else{
//
//                    AlertDialog.Builder altdial= new AlertDialog.Builder(main_menu.this);
//                    altdial.setMessage("No Internet Connection").setCancelable(false)
//
//                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    dialogInterface.cancel();
//                                }
//                            });
//
//                    AlertDialog alert= altdial.create();
//                    /*alert.setTitle("Credit");*/
//                    alert.show();
//                }
//
//            }
//        });

        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                android.net.NetworkInfo wifi = cm
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                android.net.NetworkInfo datac = cm
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if ((wifi != null & datac != null)
                        && (wifi.isConnected() | datac.isConnected())) {

                    // Intent i = new Intent(crdit_manage.this, PrinterActivity.class);
                    Intent i = new Intent(main_menu.this, sync.class);
                    // i.putExtra("crdit_id", crdit_id);
                    startActivity(i);

                }else{

                    AlertDialog.Builder altdial= new AlertDialog.Builder(main_menu.this);
                    altdial.setMessage("No Internet Connection").setCancelable(false)

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                    AlertDialog alert= altdial.create();
                    alert.show();
                }



            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            AlertDialog.Builder altdial= new AlertDialog.Builder(main_menu.this);
               // mProgress.show();
            altdial.setMessage("Are you sure?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //mProgress.dismiss();

                        Intent intent = new Intent(main_menu.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //mProgress.dismiss();
                        dialogInterface.cancel();
                    }
                });

            AlertDialog alert= altdial.create();
            alert.show();

//                Intent intent = new Intent(main_menu.this, LoginActivity.class);
//                startActivity(intent);
//                finish();

            }
        });


//        my_profile.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//                // Intent i = new Intent(crdit_manage.this, PrinterActivity.class);
//                Intent i = new Intent(main_menu.this, daily_report.class);
//                // i.putExtra("crdit_id", crdit_id);
//                startActivity(i);
//        }
//    });

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uid = LoginActivity.uid;

                //Uri uri = Uri.parse("http://g5.creditlanka.com/index.php?login_token="+uid);
                Uri uri = Uri.parse("http://savimaga.com/app/index.php?login_token="+uid);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

}
