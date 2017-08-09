package com.example.mufasa.j_max;

import android.content.Intent;
import android.graphics.Paint;
import android.media.MediaPlayer;
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

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class movies extends AppCompatActivity {

    EditText txtSearch;
    TextView txtResult,txtCost;
    Button btnFind,btnPlace,btnCheckOut;

    //ListView lstView;

    RequestParams params;
    String result;

    public static String array[];
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtSearch=(EditText)findViewById(R.id.txtSearch);
        txtSearch.setPaintFlags(txtSearch.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtResult=(TextView) findViewById(R.id.txtResult);

        btnFind=(Button) findViewById(R.id.btnFind);
        btnPlace=(Button) findViewById(R.id.btnPlace);
        btnCheckOut=(Button) findViewById(R.id.btnCheckOut);

       // lstView=(ListView)findViewById(R.id.lstMovies);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncHttpClient sync= new AsyncHttpClient();
                 params=new RequestParams();
                params.put("search",txtSearch.getText().toString());


                sync.post("http://appbeesafrica.com/Dennis/jmovies.php",params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(movies.this, "Error in Connecting", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {

                        if (responseString.contains("found")){
                           // Toast.makeText(movies.this, "Connection Successful", Toast.LENGTH_SHORT).show();

                             result=responseString.toString();
                            txtResult.setText(result+" was found");
                            count++;

                        }else {
                            Toast.makeText(movies.this, "Movie not available in database.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

       btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                txtSearch.setText("");
                Toast.makeText(movies.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("smsto:" + "+254737030953");
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.putExtra("sms_body", result);
                startActivity(i);
            }
        });
    }
}
