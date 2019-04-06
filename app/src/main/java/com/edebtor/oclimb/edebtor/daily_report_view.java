package com.edebtor.oclimb.edebtor;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.edebtor.oclimb.edebtor.Common.commo;
import com.edebtor.oclimb.edebtor.Model.credit_report;
import com.edebtor.oclimb.edebtor.Remote.IMyAPI;
import com.edebtor.oclimb.edebtor.utill.Database;

import java.util.ArrayList;
import java.util.List;

public class daily_report_view extends AppCompatActivity {

    private ListView daily_reports;
    private crdit_histry_item_Adapter adapter;
    private List<crdit_histry_item> cr_item;

    IMyAPI mServicw;


    Database db;

    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_report_view);

        mServicw = commo.getAPI();
        db=new Database(daily_report_view.this);

        daily_reports= (ListView)findViewById(R.id.daily_report);

        try {

            int uid = Integer.parseInt(LoginActivity.uid);

            ArrayList<credit_report> list = db.getuserDily_Payment_report(uid);


            if(list.size() > 0) {

                String[] area = new String[list.size()];

                int i = 0;

                cr_item = new ArrayList<>();

                for (credit_report t : list) {

                    int sid = Integer.parseInt(t.getId());

                    cr_item.add(new crdit_histry_item(sid,t.getDe_date(),t.getDe_name(),t.getCrdit()));
                    i++;
                }


                adapter = new crdit_histry_item_Adapter(getApplicationContext(),cr_item);
                daily_reports.setAdapter(adapter);

            }else{

            }



        }catch (Exception e){

        }

        daily_reports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }
}
