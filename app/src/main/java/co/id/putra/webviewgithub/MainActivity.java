package co.id.putra.webviewgithub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import co.id.putra.webviewgithub.webview.PenampilanWebView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    SimpleAdapter adapter;

    HashMap<String, String> map;

    ArrayList<HashMap<String, String>> mylist;

    String[] music ={
        "Roxane (Arizona Zervas)",
        "History (Rich Brian)",
        "Falling (Trevor Daniel)",
        "Bad Liar (Imagine Dragons)",
        "Someone You Loved (Lewis Capaldi)",
        "It's You (Ali Gatie)",
        "Yummy (justin Bieber)",
        "Lite Brite (Shinigami)",
        "Sparkle (Op kimi No Nawa)",
        "Blue Bird (Op Naruto)"
    };

    String[] tanggalliris ={
        "13 February 2020",
        "10 October 2019",
        "16 January 2020",
        "24 January 2019",
        "30 August 2019",
        "18 July 2019",
        "5 January 2020",
        "14 March 2017",
        "5 June 2018",
        "27 February 2016"
    };

    String[] link ={
        "https://www.youtube.com/watch?v=16YnOUnbE6s",
        "https://www.youtube.com/watch?v=6DrNC-xQcGs",
        "https://www.youtube.com/watch?v=L7mfjvdnPno",
        "https://www.youtube.com/watch?v=I-QfPUz1es8",
        "https://www.youtube.com/watch?v=zABLecsR5UE",
        "https://www.youtube.com/watch?v=F-cO2CMue4Q",
        "https://www.youtube.com/watch?v=8EJ3zbKTWQ8",
        "https://www.youtube.com/watch?v=taEVyGmnwQo",
        "https://www.youtube.com/watch?v=a2GujJZfXpg",
        "https://www.youtube.com/watch?v=Dp8EuP_0gWI"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView =(ListView)findViewById(R.id.list_awal);

        mylist = new ArrayList<HashMap<String, String>>();

        for (int i=0; i<music.length; i++){
            map = new HashMap<String, String>();
            map.put("judul", music[i]);
            map.put("Keterangan", tanggalliris[i]);
            mylist.add(map);
        }
        adapter = new SimpleAdapter(this, mylist, R.layout.tampilanlist,
                new String[]{"judul", "Keterangan"}, new int[]{R.id.txt_judul,(R.id.txt_keterangan)});
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, "Data Siswa Yang Dipilih " + music[i], Toast.LENGTH_SHORT).show();
                Bundle abc = new Bundle();
                abc.putString("MUSIC", music[i]);
                abc.putString("RILIS", tanggalliris[i]);
                abc.putString("LINK", link[i]);
                Intent asd = new Intent(MainActivity.this, PenampilanWebView.class);
                asd.putExtras(abc);
                startActivity(asd);


            }
        });


    }
}