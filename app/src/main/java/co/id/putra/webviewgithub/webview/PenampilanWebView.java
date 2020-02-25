package co.id.putra.webviewgithub.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import co.id.putra.webviewgithub.MainActivity;
import co.id.putra.webviewgithub.R;

public class PenampilanWebView extends AppCompatActivity {

    Button bt1, bt2;

    WebView wView ;

    WebSettings setWeb;

    String data1,data2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penampilan_web_view);

        bt1 =(Button)findViewById(R.id.bt_title);
        bt2 =(Button)findViewById(R.id.bt_back);

        wView = (WebView)findViewById(R.id.wv_penampil);

        Bundle getData = getIntent().getExtras();
        data1 = getData.getString("MUSIC");
        data2 = getData.getString("LINK");

        bt1.setText(String.valueOf(data1));
        setWeb = wView.getSettings();
        setWeb.setJavaScriptEnabled(true);
        wView.setWebViewClient(new WebViewClient());
        wView.loadUrl(String.valueOf(data2));


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abc = new Intent(PenampilanWebView.this, MainActivity.class);
                startActivity(abc);
                finish();
            }
        });

    }
}
