package com.teknodate.volleyjsonparse;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnParse, btnImage;
    TextView txtResult,txtTwo,txt_three,txt_four,txt_five,txt_six,txt_seven;
    Context context = this;
    RequestQueue mQueue;
    NetworkImageView networkImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnParse = findViewById(R.id.btn_parse2);
      //  btnImage = findViewById(R.id.btnImage);
        txtResult = findViewById(R.id.txtResult);
        txtTwo = findViewById(R.id.txt_two);
        txt_three = findViewById(R.id.txt_three);
        txt_four = findViewById(R.id.txt_four);
        txt_five = findViewById(R.id.txt_five);
        txt_six = findViewById(R.id.txt_six);
        txt_seven = findViewById(R.id.txt_seven);

        mQueue = Volley.newRequestQueue(context);
        btnParse.setOnClickListener(this);
//        btnImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_parse2){
            jsonParse();
        }
    }

    private void jsonParse() {
        String url = "https://corona.lmao.ninja/v2/countries/turkey/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                  //  JSONArray jsonarray = response.getJSONArray("contacts");
                   // for (int i = 0; i < jsonarray.length() ; i++) {
                      //  JSONObject contacts = jsonarray.getJSONObject(i);//json object
                    String cases = response.getString("cases");
                    String todayCases=response.getString("todayCases");
                    String deaths=response.getString("deaths");
                    String todayDeaths=response.getString("todayDeaths");
                    String recovered=response.getString("recovered");
                    String critical=response.getString("critical");
                    String tests  = response.getString("tests");


                         txtResult.setText(cases);
                         txtTwo.setText(todayCases);
                         txt_three.setText(deaths);
                         txt_four.setText(todayDeaths);
                         txt_five.setText(critical);
                        txt_six.setText(tests);
                        txt_seven.setText(recovered);

                    //\n"+todayCases+"\n"+deaths+"\n"+todayDeaths+"\n"+recovered+"\n"+critical+"\n"+tests}

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Hata", Toast.LENGTH_SHORT).show();
            }
        });
        //mQueue.add(jsonObjectRequest);
        MySingleTon.getInstance(context).addToRequestGueue(jsonObjectRequest);
    }

}
