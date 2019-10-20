package com.sherlocked.buildabit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Intent intent = getIntent() ;
        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        String site = intent.getStringExtra("Site") ;
        if(site.equals("Myntra")){
            webView.loadUrl("https://www.myntra.com/?gclid=Cj0KCQjw6KrtBRDLARIsAKzvQIF0N8krkng2CQuXcHQy6qwEQc2vq1H6E8xFrlyHEguKUMGzGEzKYBcaAiB7EALw_wcB&utm_source=google&utm_medium=cpc&utm_campaign=&utm_term=myntra&utm_content=&matchtype=e");
        }else if(site.equals("Jabong")){
            webView.loadUrl("https://www.jabong.com/");
        }else if(site.equals("Vogue")){
            webView.loadUrl("https://www.vogue.in/?international");
        }else if(site.equals("Elle")){
            webView.loadUrl("https://www.elle.com/");
        }else if(site.equals("Harbour")){
            webView.loadUrl("https://www.harpersbazaar.com/");
        }else if(site.equals("Cosmo")){
            webView.loadUrl("https://www.cosmopolitanlasvegas.com/?utm_medium=cpc&utm_source=google&utm_term=cosmopolitan-_&utm_content=Brand-IntlRoN&utm_campaign=DigitalAlwaysOn2016&gclid=Cj0KCQjw6KrtBRDLARIsAKzvQIHQcrbqM04pZaePeBDGNMQb4T9d9JSZE1aMxbeaBUiNrvwMx8lSC_IaAocnEALw_wcB");
        }else if(site.equals("BOF")){
            webView.loadUrl("https://www.businessoffashion.com/");
        }else if(site.equals("W")){
            webView.loadUrl("https://www.wmagazine.com/");
        }
    }
}
