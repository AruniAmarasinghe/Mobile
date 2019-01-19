package com.edebtor.oclimb.edebtor.Common;


import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;


public class ServiceHandler {
    public final static int GET = 1;
    public final static int POST = 2;


    boolean isSSLConnection;

    int respondCode=0;

    String charset = "UTF-8";
    HttpURLConnection conn;
    HttpsURLConnection sslConn;
    DataOutputStream wr;
    StringBuilder result;
    URL urlObj;
    String respond = null;
    StringBuilder sbParams;
    String paramsString;
    Context context;


    int timeOut=20;
    int readTimeOut=10;

    public ServiceHandler(Context context) {
        this.context = context;
    }

    public String makeHttpRequest(String url, int method,
                                  HashMap<String, String> params) {
        respond="";

        if(url.contains("https:")){
            isSSLConnection=true;
        }else{
            isSSLConnection=false;
        }


        try {

            if(isSSLConnection) {






            }





            sbParams = new StringBuilder();
            int i = 0;
            for (String key : params.keySet()) {
                try {
                    if (i != 0){
                        sbParams.append("&");
                    }
                    String value = "";
                    if(params.get(key) != null){
                        value = params.get(key);
                    }
                    sbParams.append(key).append("=")
                            .append(URLEncoder.encode(value, charset));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                i++;
            }






            if(isSSLConnection) {

                if (method == POST) {

                    urlObj = new URL(url);

                    sslConn = (HttpsURLConnection) urlObj.openConnection();


                    sslConn.setDoOutput(true);

                    sslConn.setRequestMethod("POST");

                    sslConn.setRequestProperty("Accept-Charset", charset);

                    int connectionTimeOut = timeOut - readTimeOut;
                    sslConn.setReadTimeout(readTimeOut * 1000);
                    sslConn.setConnectTimeout(connectionTimeOut * 1000);

                    sslConn.setHostnameVerifier(new HostnameVerifier() {

                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            /** if it necessarry get url verfication */
                            //return HttpsURLConnection.getDefaultHostnameVerifier().verify("your_domain.com", session);
                            return true;
                        }
                    });
                    sslConn.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
                    sslConn.connect();
                    paramsString = sbParams.toString();
                    wr = new DataOutputStream(sslConn.getOutputStream());



                    wr.writeBytes(paramsString);
                    wr.flush();
                    wr.close();


                } else if (method == GET) {
                    // request method is GET

                    if (sbParams.length() != 0) {
                        url += "?" + sbParams.toString();
                    }


                    urlObj = new URL(url);

                    sslConn = (HttpsURLConnection) urlObj.openConnection();

                    sslConn.setDoOutput(false);
                    sslConn.setRequestMethod("GET");
                    sslConn.setRequestProperty("Accept-Charset", charset);
                    sslConn.setConnectTimeout(timeOut);
                    sslConn.connect();


                }

                conn=sslConn;
            }else{

                if (method == POST) {

                    urlObj = new URL(url);

                    conn = (HttpURLConnection) urlObj.openConnection();



                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Accept-Charset", charset);

                    int connectionTimeOut = timeOut - readTimeOut;
                    conn.setReadTimeout(readTimeOut * 1000);
                    conn.setConnectTimeout(connectionTimeOut * 1000);



                    conn.connect();
                    paramsString = sbParams.toString();
                    wr = new DataOutputStream(conn.getOutputStream());

                    wr.writeBytes(paramsString);
                    wr.flush();
                    wr.close();


                } else if (method == GET) {
                    // request method is GET

                    if (sbParams.length() != 0) {
                        url += "?" + sbParams.toString();
                    }


                    urlObj = new URL(url);


                    conn = (HttpURLConnection) urlObj.openConnection();


                    conn.setDoOutput(false);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept-Charset", charset);
                    conn.setConnectTimeout(timeOut);
                    conn.connect();


                }



            }
            respondCode = conn.getResponseCode();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            Log.d("JSON Parser", "result : " + result.toString());
            respond=result.toString();



            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ttttttt ssl: " + e.getMessage());
        }









        // return JSON Object
        return respond;
    }
    public int getRespondCode() {
        return respondCode;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

//https://stackoverflow.com/questions/34293757/how-to-use-httpsurlconnection-instead-of-defaulthttpclient
//answer 3


}
