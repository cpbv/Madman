package com.example.urlopener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b=findViewById(R.id.button);
        Button b2=findViewById(R.id.button2);
        EditText et=findViewById(R.id.et);
        WebView wv=(WebView)findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAllowContentAccess(true);
        wv.getSettings().setAllowFileAccess(true);
        wv.setWebViewClient(new WebViewClient());
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv.loadUrl(et.getText().toString());
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String customHtml = "<html><body style="background-color:powderblue;"><body><h1>Hello</h1>" +
                        "<h3>Welcome to the <em>restaurant</em></h3>" +
                        "<p><mark>Buy 1 get 1 free</mark></p>" +
                        "<p>List of beverages</p>" +
                        "<p><ol><li><b>Tea</b></li><li><u>Coffee</u></li><li>Milk</li></ol></p>" +
                        "<p><i>Have a good day</i></p>" + "<p><sup>Come</sup> back <sub>again</sub></p>" + "</body></html>";
                wv.loadData(customHtml, "text/html", "UTF-8");
            }
        });

    }
}
