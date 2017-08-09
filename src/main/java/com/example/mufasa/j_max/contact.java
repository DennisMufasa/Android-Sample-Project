package com.example.mufasa.j_max;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class contact extends AppCompatActivity {

    ListView lstView;

    String[] names={"Josephine","Sylvia","Debrah","Carolyne","Jante"};

    String[]positions={"Manager","Assistant Manager","Human Resource","Sales","Marketing"};

    int[]logo={R.mipmap.jos,
            R.mipmap.sly,
            R.mipmap.deby,
            R.mipmap.caro,
            R.mipmap.jante
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        lstView=(ListView)findViewById(R.id.lstView);


        List<HashMap<String,String>> aList= new ArrayList<HashMap<String, String>>();

        for (int i=0;i<5;i++){
            HashMap<String,String> hashMap= new HashMap<String, String>();

            hashMap.put("txtHeading",names[i]);
            hashMap.put("subHeading",positions[i]);
            hashMap.put("img",Integer.toString(logo[i]));

            aList.add(hashMap);

            //keys used in the hashmap above
            String[]from={"img","txtHeading","subHeading"};

            //the correspondents for the keys in the support layout xml file
            int[]to={R.id.img,R.id.txtHeading,R.id.subHeading};

            SimpleAdapter adapter= new SimpleAdapter(getApplicationContext(),aList,R.layout.support,from,to);

            lstView.setAdapter(adapter);

            lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i==0){
                       Snackbar.make(view, "Phone-0796868549, email- jay@gmail.com", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    String num="+254796868549";
                      caller(num);
                    }else if(i==1){
                        Snackbar.make(view, "Phone-0737030953, email- sly@gmail.com", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        String num="+254722263148";
                        caller(num);
                    }else if (i==2){
                        Snackbar.make(view, "Phone-0722263146, email- deby@gmail.com", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        String num="+254737030953";
                        caller(num);
                    }else if (i==3){
                        Snackbar.make(view, "Phone-0787053624, email- caro@gmail.com", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        String num="+254754632145";
                        caller(num);
                    }else   if (i==4){
                       Snackbar.make(view, "Phone-0757412635, email- jante@gmail.com", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        String num="+254723456789";
                        caller(num);
                    }
                }
            });
        }
    }
    public void caller(String phone){
        String uri = "tel:" + phone ;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
}
