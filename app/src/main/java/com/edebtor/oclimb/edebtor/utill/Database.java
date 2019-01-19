package com.edebtor.oclimb.edebtor.utill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.edebtor.oclimb.edebtor.Model.credit_report;
import com.edebtor.oclimb.edebtor.Model.payment_de;
import com.edebtor.oclimb.edebtor.Model.two_item;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Database extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "oclimb_CreditSys";

    private static final String IdUsera = "idUser";
    private static final String Status = "Status";
    private static final String sync_status = "sync_status";
    private static final String CollectionArea_idCollectionArea = "CollectionArea_idCollectionArea";




    //table
    private static final String User = "User";
    private static final String collection_area_user = "collection_area_user";
    private static final String collection_area = "collectionarea";
    private static final String sync_data = "sync_data";
    private static final String credit_invoice = "credit_invoice";
    private static final String invoice_payments = "invoice_payments";
    private static final String debitors = "debitors";
    private static final String CollectorExpenses = "CollectorExpenses";

    // USER TABLE
    private static final String Uname = "Uname";
    private static final String Pass = "Pass";
    private static final String User_Type_idUser_Type = "User_Type_idUser_Type";
    private static final String User_Details_idUser_Details = "User_Details_idUser_Details";

    //Debitors TABLE
    private static final String idDebitors = "idDebitors";
    private static final String NIC = "NIC";
    private static final String Fname = "Fname";
    private static final String Lname = "Lname";
    private static final String Address1 = "Address1";
    private static final String Address2 = "Address2";
    private static final String Pno1 = "Pno1";
    private static final String Pno2 = "Pno2";
    private static final String Email = "Email";




    // collection_area_user TABLE
    private static final String idCollection_Area_User = "idCollection_Area_User";

    //collectionarea TABLE
    private static final String idCollectionArea = "idCollectionArea";
    private static final String CollectionArea = "CollectionArea";


    //sync_data TABLE
    private static final String idsync = "idsync";

    //credit_invoice TABLE
    private static final String idCredit_Invoice = "idCredit_Invoice";
    private static final String TotalAmount = "TotalAmount";
    private static final String GrantAmount = "GrantAmount";
    private static final String InterestRate = "InterestRate";
    private static final String DailyEqualPayment = "DailyEqualPayment";
    private static final String Days = "Days";
    private static final String PaidAmount = "PaidAmount";
    private static final String PenaltyPaid = "PenaltyPaid";
    private static final String Settled = "Settled";
    private static final String DateTime = "DateTime";



    //invoice_payments TABLE

    private static final String idInvoice_Payments = "idInvoice_Payments";
    private static final String Amount = "Amount";
    private static final String AdditionalAmount = "AdditionalAmount";
    private static final String PayFor = "PayFor";
    private static final String payement_DateTime = "payement_DateTime";


    //CollectorExpenses Table

    private static final String idCollectorExpenses = "idCollectorExpenses";
    private static final String Details = "Details";
    private static final String Date = "Date";




    String Creat_User= "CREATE TABLE IF NOT EXISTS "+ User +
            "("+IdUsera+" INTEGER PRIMARY KEY,"+Uname+" TEXT,"+Pass+" TEXT,"+User_Type_idUser_Type+" INTEGER,"+
            User_Details_idUser_Details+" INTEGER,"+Status+" INTEGER );";



    String Create_collection_area_user = "CREATE TABLE IF NOT EXISTS "+ collection_area_user +
            "("+idCollection_Area_User+" INTEGER PRIMARY KEY,"+CollectionArea_idCollectionArea+" TEXT,"+IdUsera+" INTEGER );";

    String Create_collection_area = "CREATE TABLE IF NOT EXISTS "+ collection_area +
            "("+idCollectionArea+" INTEGER PRIMARY KEY,"+CollectionArea+" TEXT,"+Status+" INTEGER );";


    String Create_sync_data = "CREATE TABLE IF NOT EXISTS "+ sync_data +
            "("+idsync+" INTEGER PRIMARY KEY,"+sync_status+" INTEGER );";


    String Create_credit_invoice = "CREATE TABLE IF NOT EXISTS "+ credit_invoice +
            "("+idCredit_Invoice+" INTEGER PRIMARY KEY,"+TotalAmount+" TEXT,"
            +GrantAmount+" TEXT,"+InterestRate+" TEXT,"+DailyEqualPayment+" TEXT,"
            +Days+" TEXT,"+PaidAmount+" TEXT,"+PenaltyPaid+" TEXT,"
            +Settled+" TEXT,"+DateTime+" TEXT,"+idDebitors+" INTEGER,"
            +idCollection_Area_User+" INTEGER,"+IdUsera+" INTEGER,"+Status+" INTEGER,"+sync_status+" INTEGER );";

    String Creat_invoice_payments= "CREATE TABLE IF NOT EXISTS "+ invoice_payments +
            "("+idInvoice_Payments+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Amount+" TEXT,"+AdditionalAmount+" TEXT,"
            +payement_DateTime+" TEXT,"+idCredit_Invoice+" INTEGER,"+PayFor+" INTEGER,"+
            IdUsera+" INTEGER,"+Status+" INTEGER,"+sync_status+" INTEGER );";

    String Create_debitors = "CREATE TABLE IF NOT EXISTS "+ debitors +
            "("+idDebitors+" INTEGER PRIMARY KEY,"+NIC+" TEXT,"
            +Fname+" TEXT,"+Lname+" TEXT,"+Address1+" TEXT,"
            +Address2+" TEXT,"+Pno1+" TEXT,"+Pno2+" TEXT,"
            +Email+" TEXT,"+Status+" INTEGER,"+sync_status+" INTEGER );";


    String Creat_CollectorExpenses= "CREATE TABLE IF NOT EXISTS "+ CollectorExpenses +
            "("+idCollectorExpenses+" INTEGER PRIMARY KEY,"+Amount+" TEXT,"+Details+" TEXT,"+Date+" TEXT,"+
            IdUsera+" INTEGER,"+Status+" INTEGER );";





    //String CREATE_COMMON_DATA_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_COMMON_DATA
    //        + "(" + KEY_ID + " INTEGER PRIMARY KEY,"+KEY_TIME+" TEXT,"  + COMMON_DATA_NAME
     //       + " TEXT,"+COMMON_DATA_VALUE + " TEXT," +IS_BAKUP+" INTEGER DEFAULT 0);";

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    db.execSQL(Creat_User);
    db.execSQL(Create_collection_area_user);
    db.execSQL(Create_collection_area);
    db.execSQL(Create_sync_data);
    db.execSQL(Create_credit_invoice);
    db.execSQL(Creat_invoice_payments);
    db.execSQL(Create_debitors);
    db.execSQL(Creat_CollectorExpenses);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void removeTablevalue(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM User");
        db.execSQL("DELETE FROM collection_area_user");
        db.execSQL("DELETE FROM collectionarea");
        db.execSQL("DELETE FROM sync_data");
        db.execSQL("DELETE FROM credit_invoice");
        db.execSQL("DELETE FROM invoice_payments");
        db.execSQL("DELETE FROM debitors");
        //db.execSQL("DELETE FROM CollectorExpenses");






       // System.out.println("aaaaaaaaaaaaaaaaaaaa111");

    }

   /* public void createTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(Creat_User);
        db.execSQL(Create_collection_area_user);
        db.execSQL(Create_sync_data);
        db.execSQL(Create_credit_invoice);
        db.execSQL(Creat_invoice_payments);
        db.execSQL(Create_debitors);
        db.execSQL(Create_collection_area);


    }*/
   public void CollectorExpenses_data(String id, String amount, String details, String date,String idUser,  String status){

       SQLiteDatabase db = this.getWritableDatabase();
      // db.execSQL("DELETE FROM CollectorExpenses");
       // Log.d("assss4-----------",uname);
       ContentValues values = new ContentValues();
       values.put(idCollectorExpenses,id);
       values.put(Amount,amount);
       values.put(Details,details);
       values.put(Date,date);
       values.put(Status,status);
       values.put(IdUsera,idUser);
        //Log.d("assss511-----------",details);

       db.insertWithOnConflict(CollectorExpenses, null, values,SQLiteDatabase.CONFLICT_REPLACE);
       db.close();
   }

    public void user_data(String id, String uname, String pass, String user_Type_idUser_Type,String user_Details_idUser_Details,  String status){

        SQLiteDatabase db = this.getWritableDatabase();
       // db.execSQL("DELETE FROM CollectorExpenses");
       // Log.d("assss4-----------",uname);
        ContentValues values = new ContentValues();
        values.put(IdUsera,id);
        values.put(Uname,uname);
        values.put(Pass,pass);
        values.put(User_Type_idUser_Type,user_Type_idUser_Type);
        values.put(User_Details_idUser_Details,user_Details_idUser_Details);
        values.put(Status,status);
       // Log.d("assss5-----------",pass);

        db.insertWithOnConflict(User, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }


    public void collection_area_user_data(String d_idCollection_Area_User, String d_CollectionArea_idCollectionArea, String d_IdUsera){

        SQLiteDatabase db = this.getWritableDatabase();

      //  System.out.println("000000");
        ContentValues values = new ContentValues();
        values.put(idCollection_Area_User,d_idCollection_Area_User);
        values.put(CollectionArea_idCollectionArea,d_CollectionArea_idCollectionArea);
        values.put(IdUsera,d_IdUsera);



        db.insertWithOnConflict(collection_area_user, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

   /* String Create_collection_area = "CREATE TABLE IF NOT EXISTS "+ collectionarea +
            "("+idCollection_Area_User+" INTEGER PRIMARY KEY,"+CollectionArea+" TEXT,"+Status+" INTEGER );";

*/

    public void collection_area_data(String d_idCollectionArea, String d_collectionarea, String d_Status){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(idCollectionArea,d_idCollectionArea);
        values.put(CollectionArea,d_collectionarea);
        values.put(Status,d_Status);



        db.insertWithOnConflict(collection_area, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void sync_data_data(String d_idsync, String d_sync_status){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(idsync,d_idsync);
        values.put(sync_status,d_sync_status);

       db.insertWithOnConflict(sync_data, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }




    public void credit_invoice_data(String d_idCredit_Invoice, String d_TotalAmount, String d_GrantAmount, String d_InterestRate,
     String d_DailyEqualPayment, String d_Days, String d_PaidAmount, String d_PenaltyPaid, String d_Settled, String d_DateTime, String d_idDebitors
            , String d_idCollection_Area_User, String d_IdUsera, String d_Status, String d_sync_status){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(idCredit_Invoice,d_idCredit_Invoice);
        values.put(TotalAmount,d_TotalAmount);
        values.put(GrantAmount,d_GrantAmount);
        values.put(InterestRate,d_InterestRate);
        values.put(DailyEqualPayment,d_DailyEqualPayment);
        values.put(Days,d_Days);
        values.put(PaidAmount,d_PaidAmount);
        values.put(PenaltyPaid,d_PenaltyPaid);
        values.put(Settled,d_Settled);
        values.put(DateTime,d_DateTime);
        values.put(idDebitors,d_idDebitors);
        values.put(idCollection_Area_User,d_idCollection_Area_User);
        values.put(IdUsera,d_IdUsera);
        values.put(Status,d_Status);
        values.put(sync_status,d_sync_status);

        db.insertWithOnConflict(credit_invoice, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }





    public void invoice_payments_data(String d_idInvoice_Payments, String d_Amount, String d_AdditionalAmount, String d_DateTime,
                                      String d_PayFor, String d_idCredit_Invoice, String d_IdUsera, String d_Status, String d_sync_status){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
      //  values.put(idInvoice_Payments,d_idInvoice_Payments);
        values.put(Amount,d_Amount);
        values.put(AdditionalAmount,d_AdditionalAmount);
        values.put(payement_DateTime,d_DateTime);
        values.put(PayFor,d_PayFor);
        values.put(idCredit_Invoice,d_idCredit_Invoice);
        values.put(IdUsera,d_IdUsera);
        values.put(Status,d_Status);
        values.put(sync_status,d_sync_status);

        db.insertWithOnConflict(invoice_payments, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();


    }



    public void debitors_data(String d_idDebitors, String d_NIC, String d_Fname, String d_Lname, String d_Address1,
                              String d_Address2, String d_Pno1, String d_Pno2, String d_Email, String d_Status, String d_sync_status){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(idDebitors,d_idDebitors);
        values.put(NIC,d_NIC);
        values.put(Fname,d_Fname);
        values.put(Lname,d_Lname);
        values.put(Address1,d_Address1);
        values.put(Address2,d_Address2);
        values.put(Pno1,d_Pno1);
        values.put(Pno2,d_Pno2);
        values.put(Email,d_Email);
        values.put(Status,d_Status);
        values.put(sync_status,d_sync_status);

        db.insertWithOnConflict(debitors, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

//===== get value




    public ArrayList<two_item> getUserArea(int uid) {
        SQLiteDatabase db = this.getWritableDatabase();
       // HashMap<String, String> map = new HashMap<String, String>();

        ArrayList<two_item> list=new ArrayList<two_item>();




        //  String query = "SELECT * FROM " + collection_area ;
        String query = "SELECT * FROM "+collection_area_user+" u JOIN "+ collection_area+" c ON u. "+CollectionArea_idCollectionArea+" =c. "+idCollectionArea+" WHERE "+IdUsera+"="+ uid ;

        //System.out.println("ddddooooooooo-"+ query);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do{
                String id = cursor.getString(cursor.getColumnIndex(idCollectionArea));
                String value =cursor.getString(cursor.getColumnIndex(CollectionArea));
                System.out.println("vallll"+value);

                two_item setitem = new two_item(id,value);
                list.add(setitem);
               // map.put(id,value);

              //  map.put("id", id);
               // map.put("value", value);
            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return list;
    }





    public ArrayList<two_item> getUserAreaDebetor(int areaid) {
        SQLiteDatabase db = this.getWritableDatabase();
        // HashMap<String, String> map = new HashMap<String, String>();

        ArrayList<two_item> list=new ArrayList<two_item>();




        // String query = "SELECT * FROM " + collection_area ;
        String query = "SELECT d."+Fname+",d."+Lname+", i."+idCredit_Invoice+" FROM "+ credit_invoice +" i JOIN "+ debitors +" d ON i."+idDebitors+" = d. "+idDebitors+" WHERE i."+idCollection_Area_User+" = "+areaid+" AND i."+Status+" ='1' AND i."+Settled+"='0'";

         // System.out.println("ddddooooooooo");

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do{
                String id = cursor.getString(cursor.getColumnIndex(idCredit_Invoice));
                String value =cursor.getString(cursor.getColumnIndex(Fname))+" "+cursor.getString(cursor.getColumnIndex(Lname));
                //   System.out.println("vallll"+value);

                two_item setitem = new two_item(id,value);
                list.add(setitem);
                // map.put(id,value);

                //  map.put("id", id);
                // map.put("value", value);
            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return list;
    }


    public int checkSync(){

        int syncCount=0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query ="SELECT * FROM "+invoice_payments+" WHERE "+sync_status +" ='1' ";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            syncCount = cursor.getCount();
        }

        cursor.close();
        db.close();
        return syncCount;
    }



    public ArrayList<two_item> getAlDebetor() {
        SQLiteDatabase db = this.getWritableDatabase();
        // HashMap<String, String> map = new HashMap<String, String>();

        ArrayList<two_item> list=new ArrayList<two_item>();




        // String query = "SELECT * FROM " + collection_area ;
        String query = "SELECT "+Fname+", "+Lname+", "+idDebitors+" FROM "+ debitors +" WHERE "+Status+" ='1' ";

       //  System.out.println("ddddooooooooo");

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do{
                String id = cursor.getString(cursor.getColumnIndex(idDebitors));
                String value =cursor.getString(cursor.getColumnIndex(Fname))+" "+cursor.getString(cursor.getColumnIndex(Lname));
                //   System.out.println("vallll"+value);

                two_item setitem = new two_item(id,value);
                list.add(setitem);
                // map.put(id,value);

                //  map.put("id", id);
                // map.put("value", value);
            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<two_item> getAllArea() {
        SQLiteDatabase db = this.getWritableDatabase();
        // HashMap<String, String> map = new HashMap<String, String>();

        ArrayList<two_item> list=new ArrayList<two_item>();




        //  String query = "SELECT * FROM " + collection_area ;
        String query = "SELECT * FROM "+ collection_area+" WHERE "+Status+"= '1'" ;

        // System.out.println("ddddooooooooo");

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do{
                String id = cursor.getString(cursor.getColumnIndex(idCollectionArea));
                String value =cursor.getString(cursor.getColumnIndex(CollectionArea));
             //   System.out.println("vallll"+value);

                two_item setitem = new two_item(id,value);
                list.add(setitem);
                // map.put(id,value);

                //  map.put("id", id);
                // map.put("value", value);
            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return list;
    }



    public String[] getinvoice_payment(int crid) {
        SQLiteDatabase db = this.getWritableDatabase();
        // HashMap<String, String> map = new HashMap<String, String>();

       // ArrayList<two_item> list=new ArrayList<two_item>();


        //String query2 = "SELECT * FROM "+ debitors+" WHERE "+idDebitors+"= '1'" ;

         /* String query1 = "SELECT * FROM "+credit_invoice+
                " WHERE "+idCredit_Invoice+"="+crid ;*/
        String query1 = "SELECT * FROM "+ credit_invoice +" i JOIN "+ debitors +" d ON i."+idDebitors+" = d. "+idDebitors+" WHERE i."+idCredit_Invoice+" = "+crid;;

        //  String query1 = "SELECT * FROM "+debitors+" i JOIN "+credit_invoice+" c ON i."+idDebitors+" = c."+idDebitors+ " WHERE c."+idCredit_Invoice+"="+crid;

        // String query = "SELECT * FROM " + collection_area ;
        String query = "SELECT * FROM "+invoice_payments+" i LEFT JOIN "+credit_invoice+" c ON i."+idCredit_Invoice+" = c."+idCredit_Invoice+" \n" +
                "WHERE c."+idCredit_Invoice+"="+crid+"  AND c."+Status+"='1' AND i."+Status+" ='1' ORDER BY i."+payement_DateTime+" DESC LIMIT 1; " ;

       // System.out.println("get_DailyEqualPayment "+query1);

        String payment[] = new String[8];

        Cursor cursor = db.rawQuery(query, null);
        Cursor cursor1 = db.rawQuery(query1, null);




        if (cursor1.getCount() > 0) {
            cursor1.moveToFirst();

            do{
                /*String get_allTotalAmount = cursor1.getString(cursor1.getColumnIndex(TotalAmount));
                String get_GrantAmount =cursor1.getString(cursor1.getColumnIndex(GrantAmount));
                String get_DailyEqualPayment =cursor1.getString(cursor1.getColumnIndex(DailyEqualPayment));

                String get_Pno1 =cursor1.getString(cursor1.getColumnIndex(Pno1));*/

                String get_allTotalAmount = cursor1.getString(cursor1.getColumnIndex(TotalAmount));
                String get_GrantAmount =cursor1.getString(cursor1.getColumnIndex(GrantAmount));
                String get_DailyEqualPayment =cursor1.getString(cursor1.getColumnIndex(DailyEqualPayment));

               // String get_Pno1 =cursor1.getString(cursor1.getColumnIndex(Pno1));

              // System.out.println("get_DailyEqualPayment"+get_Pno1);

                payment[0] = get_DailyEqualPayment;
                payment[4] = get_allTotalAmount;
                payment[5] = get_GrantAmount;

                // payment[8] = "1233";

            } while (cursor1.moveToNext());


        }


        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do{
               /* String get_allTotalAmount = cursor.getString(cursor.getColumnIndex(TotalAmount));
                String get_GrantAmount =cursor.getString(cursor.getColumnIndex(GrantAmount));
                String get_DailyEqualPayment =cursor.getString(cursor.getColumnIndex(DailyEqualPayment));*/

                String get_DateTime =cursor.getString(cursor.getColumnIndex(payement_DateTime));
                Double get_PaidAmount =cursor.getDouble(cursor.getColumnIndex(PaidAmount));
                Double get_PenaltyPaid =cursor.getDouble(cursor.getColumnIndex(PenaltyPaid));
                String get_Days =cursor.getString(cursor.getColumnIndex(Days));
               // String get_PaidAmount =cursor.getString(cursor.getColumnIndex(PaidAmount));

                String getTotal_amount = String.valueOf(get_PaidAmount + get_PenaltyPaid);


                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                String curent_date=  dateFormat.format(cal.getTime());

               // System.out.println("opopopo-"+get_DateTime +"  "+curent_date);

                try {
                    Date last_pament = dateFormat.parse(get_DateTime);
                    Date paset_curent_date = dateFormat.parse(curent_date);



                    long diffInMillies = Math.abs(paset_curent_date.getTime() - last_pament.getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                    String due_date = String.valueOf(diff);

                   // payment[0] = get_DailyEqualPayment;
                    payment[1] = getTotal_amount;
                    payment[2] = get_DateTime;
                    payment[3] = due_date;
                    //payment[4] = get_allTotalAmount;
                   // payment[5] = get_GrantAmount;
                    payment[6] = get_Days;
                    payment[7] = String.valueOf(get_PaidAmount);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //   System.out.println("vallll"+value);

               // two_item setitem = new two_item(id,value);
               // list.add(setitem);
                // map.put(id,value);

                //  map.put("id", id);
                // map.put("value", value);



            } while (cursor.moveToNext());


        }

        String query2 = "SELECT * FROM "+ debitors+" WHERE "+idDebitors+"= '1'" ;


        cursor.close();
        cursor1.close();
        db.close();
        return payment;
    }



    public ArrayList<payment_de> SetnewPayment() {
        SQLiteDatabase db = this.getWritableDatabase();
        // HashMap<String, String> map = new HashMap<String, String>();

        ArrayList<payment_de> list=new ArrayList<payment_de>();


        //String query = "SELECT * FROM "+ invoice_payments+" WHERE "+sync_status+"= '1' AND "+Status+"= '1'" ;

        String query = "SELECT * FROM "+invoice_payments+" i LEFT JOIN "+credit_invoice+" c ON i."+idCredit_Invoice+" = c."+idCredit_Invoice+" \n" +
                "WHERE i."+sync_status+"= '1'  AND c."+Status+"='1' AND i."+Status+" ='1' " ;


        //System.out.println("ddddooooooooo-"+query);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do{
                String id = cursor.getString(cursor.getColumnIndex(idInvoice_Payments));
                String userid =cursor.getString(cursor.getColumnIndex(IdUsera));
                String dailyPay =cursor.getString(cursor.getColumnIndex(DailyEqualPayment));
                String payFor =cursor.getString(cursor.getColumnIndex(PayFor));
                String addAmount =cursor.getString(cursor.getColumnIndex(AdditionalAmount));
                String paidAmount =cursor.getString(cursor.getColumnIndex(AdditionalAmount));
                String cid =cursor.getString(cursor.getColumnIndex(idCredit_Invoice));
                 // System.out.println("vallll"+paidAmount);

                payment_de setitem = new payment_de( id,  userid,  dailyPay,  payFor,  addAmount,  paidAmount,  cid);
                list.add(setitem);
                // map.put(id,value);

                //  map.put("id", id);
                // map.put("value", value);
            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return list;
    }



    public String[] getinvoice_payment_debetor(int crid) {
        SQLiteDatabase db = this.getWritableDatabase();
        // HashMap<String, String> map = new HashMap<String, String>();

        // ArrayList<two_item> list=new ArrayList<two_item>();






        //  String query = "SELECT * FROM " + collection_area ;
        String query = "SELECT * FROM "+invoice_payments+" i LEFT JOIN "+credit_invoice+" c ON i."+idCredit_Invoice+" = c."+idCredit_Invoice+" \n" +
                "WHERE c."+idCredit_Invoice+"="+crid+"  AND c."+Status+"='1' AND i."+Status+" ='1' ORDER BY i."+payement_DateTime+" DESC LIMIT 1; " ;

        // System.out.println("ddddooooooooo");

        String payment[] = new String[8];

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do{
                String get_allTotalAmount = cursor.getString(cursor.getColumnIndex(TotalAmount));
                String get_GrantAmount =cursor.getString(cursor.getColumnIndex(GrantAmount));
                String get_DailyEqualPayment =cursor.getString(cursor.getColumnIndex(DailyEqualPayment));
                String get_DateTime =cursor.getString(cursor.getColumnIndex(payement_DateTime));
                Double get_PaidAmount =cursor.getDouble(cursor.getColumnIndex(PaidAmount));
                Double get_PenaltyPaid =cursor.getDouble(cursor.getColumnIndex(PenaltyPaid));
                String get_Days =cursor.getString(cursor.getColumnIndex(Days));
                // String get_PaidAmount =cursor.getString(cursor.getColumnIndex(PaidAmount));

                String getTotal_amount = String.valueOf(get_PaidAmount + get_PenaltyPaid);


                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                String curent_date=  dateFormat.format(cal.getTime());

                // System.out.println("opopopo-"+get_DateTime +"  "+curent_date);

                try {
                    Date last_pament = dateFormat.parse(get_DateTime);
                    Date paset_curent_date = dateFormat.parse(curent_date);



                    long diffInMillies = Math.abs(paset_curent_date.getTime() - last_pament.getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                    String due_date = String.valueOf(diff);

                    payment[0] = get_DailyEqualPayment;
                    payment[1] = getTotal_amount;
                    payment[2] = get_DateTime;
                    payment[3] = due_date;
                    payment[4] = get_allTotalAmount;
                    payment[5] = get_GrantAmount;
                    payment[6] = get_Days;
                    payment[7] = String.valueOf(get_PaidAmount);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //   System.out.println("vallll"+value);

                // two_item setitem = new two_item(id,value);
                // list.add(setitem);
                // map.put(id,value);

                //  map.put("id", id);
                // map.put("value", value);



            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return payment;
    }


    public ArrayList<credit_report> getuserPayment_report(int get_cid) {
        SQLiteDatabase db = this.getWritableDatabase();
        // HashMap<String, String> map = new HashMap<String, String>();

        ArrayList<credit_report> list=new ArrayList<credit_report>();


        //String query = "SELECT * FROM "+ invoice_payments+" WHERE "+sync_status+"= '1' AND "+Status+"= '1'" ;

        String query = "SELECT * FROM "+invoice_payments+" \n" +
                "WHERE "+idCredit_Invoice+" ="+ get_cid+" AND "+Status+" ='1' ORDER BY "+payement_DateTime+" DESC" ;


      //  System.out.println("ddddooooooooo1-"+query);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do{




                String id = cursor.getString(cursor.getColumnIndex(idInvoice_Payments));



                Double getAdditionalAmount =cursor.getDouble(cursor.getColumnIndex(AdditionalAmount));
                Double get_Amount =cursor.getDouble(cursor.getColumnIndex(Amount));

                String get_payement_DateTime =cursor.getString(cursor.getColumnIndex(payement_DateTime));

                String all_amount = String.valueOf(get_Amount+getAdditionalAmount);

              //  System.out.println(getAdditionalAmount+"vallllo"+get_Amount);

               // payment_de setitem = new payment_de( id,  userid,  dailyPay,  payFor,  addAmount,  paidAmount,  cid);

                credit_report setitem = new credit_report(id,all_amount,"",get_payement_DateTime);
                list.add(setitem);

            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<credit_report> getuserDily_Payment_report(int get_uid) {
        SQLiteDatabase db = this.getWritableDatabase();



        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        String todate = date+" 00:00:00";
        String fromrdate = date+" 23:59:59";
        credit_report setitem;

        ArrayList<credit_report> list=new ArrayList<credit_report>();


      //  String query = "SELECT * FROM "+ invoice_payments+" WHERE "+Status+"= '1'" ;

        String query = "SELECT * FROM "+invoice_payments +" WHERE "+IdUsera+" ="+ get_uid+" AND "+Status+" ='1' AND " + payement_DateTime+" BETWEEN '"+ todate +"' AND '"+fromrdate+"'" ;


        //System.out.println("ddddooooooooo2-"+query);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {


                String id = cursor.getString(cursor.getColumnIndex(idInvoice_Payments));


                Double getAdditionalAmount = cursor.getDouble(cursor.getColumnIndex(AdditionalAmount));
                Double get_Amount = cursor.getDouble(cursor.getColumnIndex(Amount));

                String get_payement_DateTime = cursor.getString(cursor.getColumnIndex(payement_DateTime));

                String all_amount = String.valueOf(get_Amount + getAdditionalAmount);

                int cr_id = cursor.getInt(cursor.getColumnIndex(idCredit_Invoice));
                String get_name = "";

                String query2 = "SELECT * FROM " + debitors + " d  JOIN " + credit_invoice + " c ON d." + idDebitors + " = c." + idDebitors + " \n" +
                        "WHERE c." + idCredit_Invoice + "= " + cr_id;

                //  System.out.println("ddddooooooooo2-"+query2);

                Cursor cursor2 = db.rawQuery(query2, null);


                if (cursor2.getCount() > 0) {
                    cursor2.moveToFirst();

                    do {
                        get_name = cursor2.getString(cursor2.getColumnIndex(Fname)) + " " + cursor2.getString(cursor2.getColumnIndex(Lname));
                        // get_name = cursor2.getString(cursor2.getColumnIndex(Fname)) ;

                    } while (cursor2.moveToNext());
                }
                //System.out.println("vallllo12"+cursor.getColumnIndex(IdUsera));

                // payment_de setitem = new payment_de( id,  userid,  dailyPay,  payFor,  addAmount,  paidAmount,  cid);

                 setitem = new credit_report(id, all_amount, get_name, get_payement_DateTime);
                  list.add(setitem);

            } while (cursor.moveToNext());

        }

            String query1 = "SELECT * FROM "+CollectorExpenses +" WHERE "+IdUsera+" ="+get_uid+" AND "+Status+" ='1' AND " + Date +" = '"+ date +"' ";


       //     System.out.println("ddddooooooooo12-"+query1);

            Cursor cursor1 = db.rawQuery(query1, null);

            if (cursor1.getCount() > 0) {
                cursor1.moveToFirst();

                do {


                    Double get_amount = cursor1.getDouble(cursor1.getColumnIndex(Amount));


                    // System.out.println("ddddooddooooooo12-"+get_extot);
                    String all_amount = "-" + String.valueOf(get_amount);
                    String id = cursor1.getString(cursor1.getColumnIndex(idCollectorExpenses));
                    String get_name = cursor1.getString(cursor1.getColumnIndex(Details));


                     setitem = new credit_report(id, all_amount, get_name, date);

                    list.add(setitem);
                } while (cursor1.moveToNext());
            }




        cursor.close();
        db.close();
        return list;
    }






    public String getuserDily_Payment_tot(int get_uid) {
        SQLiteDatabase db = this.getWritableDatabase();



        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        String todate = date+" 00:00:00";
        String fromrdate = date+" 23:59:59";

        String tot="0";

        Double getAdditionalAmount ;
        Double get_Amount ;
        Double get_exAmount ;
        Double get_tot = 0.0;
        Double get_extot = 0.0;

        ArrayList<credit_report> list=new ArrayList<credit_report>();


        //  String query = "SELECT * FROM "+ invoice_payments+" WHERE "+Status+"= '1'" ;

        String query = "SELECT * FROM "+invoice_payments +" WHERE "+IdUsera+" ="+ get_uid+" AND "+Status+" ='1' AND " + payement_DateTime+" BETWEEN '"+ todate +"' AND '"+fromrdate+"'" ;


        //System.out.println("ddddooooooooo2-"+query);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do{








                 getAdditionalAmount =cursor.getDouble(cursor.getColumnIndex(AdditionalAmount));
                 get_Amount =cursor.getDouble(cursor.getColumnIndex(Amount));

                get_tot =  (getAdditionalAmount + get_Amount)+get_tot;

                //System.out.println("ddddooddooooooo-"+get_tot);
            } while (cursor.moveToNext());

          //  tot = "Rs. "+String.valueOf(get_tot);
        }



   //     String query1 = "SELECT * FROM "+CollectorExpenses +" WHERE "+IdUsera+" ="+get_uid+" AND "+Status+" ='1' AND " + Date+" BETWEEN '"+ todate +"' AND '"+fromrdate+"'" ;
        String query1 = "SELECT * FROM "+CollectorExpenses +" WHERE "+IdUsera+" ="+get_uid+" AND "+Status+" ='1' AND " + Date +" = '"+ date +"' ";


       // System.out.println("ddddooooooooo12-"+query1);

        Cursor cursor1 = db.rawQuery(query1, null);

        if (cursor1.getCount() > 0) {
            cursor1.moveToFirst();

            do{

                get_exAmount =cursor1.getDouble(cursor1.getColumnIndex(Amount));

                get_extot =   get_exAmount + get_extot;

                //System.out.println("ddddooddooooooo12-"+get_extot);
            } while (cursor1.moveToNext());


        }
        tot = "Rs. "+String.valueOf(get_tot-get_extot);
        cursor.close();
        db.close();
        return tot;
    }




    public String user_login(String un,String pass) {
        SQLiteDatabase db = this.getWritableDatabase();

        String login_result ="0";
        un = un.replace(" ", "");
        pass = pass.replace(" ", "");
        //  String query = "SELECT * FROM "+ invoice_payments+" WHERE "+Status+"= '1'" ;

       // String query = "SELECT * FROM "+User +" WHERE "+Uname+" ='"+un+"' AND "+Pass+" ='"+pass+"' "+Status+"= '1'  " ;

        String query = "SELECT * FROM "+User+" WHERE "+Uname+" ='"+un+"' AND "+Pass+" ='"+pass+"' "  ;

       // System.out.println("ddddooooooooo2-"+query);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            do{

                login_result = cursor.getString(cursor.getColumnIndex(IdUsera));
               // System.out.println("ddddooooooooo2-"+cursor.getString(cursor.getColumnIndex(IdUsera)));






            } while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return login_result;
    }


}
