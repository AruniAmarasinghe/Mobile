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

            //  int cre_id =collection.cr_id;
            int uid = Integer.parseInt(LoginActivity.uid);
            //System.out.println("crrrrrrrrrrrrr"+cre_id);

            ArrayList<credit_report> list = db.getuserDily_Payment_report(uid);
            //System.out.println("pppppppppp");

            if(list.size() > 0) {

                String[] area = new String[list.size()];
                //area_ids = new String[list.size()];
                int i = 0;

                cr_item = new ArrayList<>();

                for (credit_report t : list) {
                    // System.out.println("--aaaaaa---" + t.getCrdit());

                    int sid = Integer.parseInt(t.getId());

                    cr_item.add(new crdit_histry_item(sid,t.getDe_date(),t.getDe_name(),t.getCrdit()));
                    //     System.out.println("--aaaaaa---"+i);
                    // area_ids[i] = t.getId();
                    // area[i] = t.getValue();
                    // System.out.println("-1-aaaaaa---"+i);
                    i++;
                    // Log.i("Value of element "+i, String.valueOf(list.get(i)));
                }




            /*cr_item.add(new crdit_histry_item(1,"2018-11-10","Naveen Damitha","200"));
            cr_item.add(new crdit_histry_item(2,"2018-11-11","Naveen Damitha","200"));
            cr_item.add(new crdit_histry_item(3,"2018-11-12","Naveen Damitha","200"));
            cr_item.add(new crdit_histry_item(4,"2018-11-13","Naveen Damitha","200"));
            cr_item.add(new crdit_histry_item(5,"2018-11-14","Naveen Damitha","200"));
            cr_item.add(new crdit_histry_item(6,"2018-11-15","Naveen Damitha","200"));
*/
                adapter = new crdit_histry_item_Adapter(getApplicationContext(),cr_item);
                daily_reports.setAdapter(adapter);

                // setAreaValue(area);
            }else{
                //String[] area = new String[]{"Area can't Assing "};
                //setDebitorValue(debetors);
                // setAreaValue(area);
            }
            //System.out.println("zzzzz-");


        }catch (Exception e){

        }
       /* cr_item = new ArrayList<>();
        cr_item.add(new crdit_histry_item(1,"2018-11-10","Naveen Damitha","200"));
        cr_item.add(new crdit_histry_item(2,"2018-11-11","Naveen Damitha","200"));
        cr_item.add(new crdit_histry_item(3,"2018-11-12","Naveen Damitha","200"));
        cr_item.add(new crdit_histry_item(4,"2018-11-13","Naveen Damitha","200"));
        cr_item.add(new crdit_histry_item(5,"2018-11-14","Naveen Damitha","200"));
        cr_item.add(new crdit_histry_item(6,"2018-11-15","Naveen Damitha","200"));

        adapter = new crdit_histry_item_Adapter(getApplicationContext(),cr_item);
        daily_reports.setAdapter(adapter);*/

        daily_reports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }
}
