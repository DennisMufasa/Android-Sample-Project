package com.example.mufasa.j_max;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;


import cz.msebera.android.httpclient.Header;

public class series extends AppCompatActivity {


    EditText txtSearchS;
    TextView txtResultS,txtCostS;
    Button btnFindS,btnPlaceS,btnCheckOutS;
    //ListView lstView;

    AsyncHttpClient sync;
    RequestParams params;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtSearchS=(EditText)findViewById(R.id.txtSearchS);
        txtSearchS.setPaintFlags(txtSearchS.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtResultS=(TextView) findViewById(R.id.txtResultS);

        btnFindS=(Button) findViewById(R.id.btnFindS);
        btnPlaceS=(Button) findViewById(R.id.btnPlaceS);
        btnCheckOutS=(Button) findViewById(R.id.btnCheckOutS);

      //  lstView=(ListView)findViewById(R.id.lstSeries);

        btnFindS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sync= new AsyncHttpClient();

                params= new RequestParams();

                params.put("search",txtSearchS.getText().toString());

                sync.post("http://192.168.100.18/mufasa/jseries.php", params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(series.this, "Error in Connection", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {

                        if (responseString.toString().contains("found")){

                            result=txtSearchS.getText().toString();
                            txtResultS.setText(result+" was found");
                        }else{
                            Toast.makeText(series.this, "Series was not found.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnPlaceS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtSearchS.setText("");
                Toast.makeText(series.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        btnCheckOutS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("smsto:" + "+254737030953");
                Intent text = new Intent(Intent.ACTION_SENDTO, uri);
                text.putExtra("sms_body", result);
                startActivity(text);

            }
        });

    }

}
