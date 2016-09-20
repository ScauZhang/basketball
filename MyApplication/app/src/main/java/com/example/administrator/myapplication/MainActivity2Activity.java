package com.example.administrator.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import org.apache.http.Header;
import org.json.JSONObject;


public class MainActivity2Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        asynGet();
    }

    private void asynGet(){
        AsyncHttpClient get = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        get.setCookieStore(myCookieStore);
        get.get("http://192.168.1.26:8000/zhang", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String response = new String(bytes, 0, bytes.length, "utf-8");
                    JSONObject json = new JSONObject(response);
                    Log.i("result", json.getJSONObject("msg").getString("zhang"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Toast.makeText(MainActivity.this,i,Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                Toast.makeText(MainActivity.this,i,Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
