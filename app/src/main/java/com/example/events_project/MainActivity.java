package com.example.events_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText e1,e2;
Button b;
String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.username);
        e2=findViewById(R.id.password);
        b=findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=e1.getText().toString();
                password=e2.getText().toString();
                new get_admin(MainActivity.this).execute();
            }
        });

    }











    class insert extends AsyncTask<String, String, String> {

        Context ccc;
        String url="";
        insert(Context c) {

            ccc = c;
        }

        String g="error";

        @Override
        protected String doInBackground(String... arg0) {
            try {


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username",username));
                nameValuePairs.add(new BasicNameValuePair("password",password));

                /*$title=$_REQUEST["title"];
                $notice=$_REQUEST["notice"];
                $date=$_REQUEST["date"];
                $create_date=$_REQUEST["c_date"];
                $teacherID=$_REQUEST["teacherID"];
                $classesID=$_REQUEST["classID"];*/
                //hedder_no=Integer.parseInt(arg0[1]);

                    url="https://androidtest2019.000webhostapp.com/co_register.php";


                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                g = httpClient.execute(httpPost, responseHandler);

                // HttpEntity entity = response.getEntity();


            } catch (NullPointerException e) {
                //Toast.makeText(ccc, e.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
//		Toast.makeText(ccc,e.toString(), Toast.LENGTH_LONG).show();
                return e.toString();
            }
            return g;

        }

        @Override


        protected void onPostExecute(String result) {
            //  hlist.removeAllViewsInLayout();
            Log.e("MAN",result);

        }
    }

    class get_admin extends AsyncTask<String, String, String> {

        Context ccc;
        String url="";
        get_admin(Context c) {

            ccc = c;
        }

        String g="error";

        @Override
        protected String doInBackground(String... arg0) {
            try {


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username",username));
                nameValuePairs.add(new BasicNameValuePair("password",password));

                /*$title=$_REQUEST["title"];
                $notice=$_REQUEST["notice"];
                $date=$_REQUEST["date"];
                $create_date=$_REQUEST["c_date"];
                $teacherID=$_REQUEST["teacherID"];
                $classesID=$_REQUEST["classID"];*/
                //hedder_no=Integer.parseInt(arg0[1]);

                url="https://androidtest2019.000webhostapp.com/get_admin.php";


                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                g = httpClient.execute(httpPost, responseHandler);

                // HttpEntity entity = response.getEntity();


            } catch (NullPointerException e) {
                //Toast.makeText(ccc, e.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
//		Toast.makeText(ccc,e.toString(), Toast.LENGTH_LONG).show();
                return e.toString();
            }
            return g;

        }

        @Override


        protected void onPostExecute(String result) {
            //  hlist.removeAllViewsInLayout();

            try {
                JSONArray jsonarray = new JSONArray(result);

                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    e1.setText(jsonobject.getString("username"));
                    e2.setText(jsonobject.getString("password"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
