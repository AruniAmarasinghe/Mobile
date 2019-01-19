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

public class main_menu extends AppCompatActivity {
    Button btn_crtManage,new_loan,new_cus,my_profile,sync,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        btn_crtManage=findViewById(R.id.crt_manage);
        //commented for additional functionality
        //new_loan=findViewById(R.id.new_loan);
        //new_cus=findViewById(R.id.new_cus);
        //my_profile=findViewById(R.id.my_profile);
        sync=findViewById(R.id.sync);
        logout=findViewById(R.id.logout);

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
                    /*alert.setTitle("Credit");*/
                    alert.show();
                }



            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(main_menu.this, LoginActivity.class);
                startActivity(intent);
                finish();
                //System.exit(0);


            }
        });

//        my_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                // Intent i = new Intent(crdit_manage.this, PrinterActivity.class);
//                Intent i = new Intent(main_menu.this, daily_report.class);
//                // i.putExtra("crdit_id", crdit_id);
//                startActivity(i);
//
//
//            }
//        });

    }

}
