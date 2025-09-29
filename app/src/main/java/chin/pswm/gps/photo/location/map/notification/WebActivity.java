package chin.pswm.gps.photo.location.map.notification;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;

import chin.pswm.gps.photo.location.map.MyApplication;
import chin.pswm.gps.photo.location.map_debug.R;


public class WebActivity extends AppCompatActivity {
    String link;
    SharedPreferences.Editor myEdit;
    public ProgressDialog pd;
    RelativeLayout rl_loadingscreen;
    SharedPreferences sharedPreferences;
    String type;
    public WebView webView;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_web);
        Log.e("getmetype", "web ");
        getWindow().addFlags(128);
        Log.e("getmetype", "next ");
        Intent intent = getIntent();
        if (intent != null) {
            this.link = intent.getStringExtra("link");
            this.type = getIntent().getStringExtra("type");
        }
        MyApplication.needToShow = true;
        WebView webView = (WebView) findViewById(R.id.webView1);
        this.webView = webView;
        webView.getSettings().setLoadWithOverviewMode(true);
        this.webView.getSettings().setUseWideViewPort(true);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_loadingscreen);
        this.rl_loadingscreen = relativeLayout;
        relativeLayout.setVisibility(0);
        SharedPreferences sharedPreferences = getSharedPreferences("bdcPref", 0);
        this.sharedPreferences = sharedPreferences;
        this.myEdit = sharedPreferences.edit();
        if (this.sharedPreferences.getBoolean("hasMatch", false) && this.type.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_3D)) {
            next();
        } else {
            next();
        }
    }

    void next() {
        showads();
    }

    public void showads() {
        onPreExecute();
        new Handler().postDelayed(new Runnable() { 


            @Override 
            public void run() {
                WebActivity.this.pd.hide();
                WebActivity.this.adsss();
            }
        }, 3000L);
    }

    private void loadwebview(String str) {
        this.rl_loadingscreen.setVisibility(8);
        this.webView.setWebViewClient(new WebViewClient() { 
            public ProgressDialog f6929a;

            @Override 
            public void onLoadResource(WebView webView, String str2) {
                if (this.f6929a == null) {
                    ProgressDialog progressDialog = new ProgressDialog(WebActivity.this);
                    this.f6929a = progressDialog;
                    progressDialog.setMessage("Loading...");
                    this.f6929a.setCancelable(true);
                    this.f6929a.show();
                }
            }

            @Override 
            public void onPageFinished(WebView webView, String str2) {
                this.f6929a.dismiss();
            }

            @Override 
            public boolean shouldOverrideUrlLoading(WebView webView, String str2) {
                webView.loadUrl(str2);
                return true;
            }
        });
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(str);
    }

    public void onPreExecute() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.pd = progressDialog;
        progressDialog.setTitle("Please Wait");
        this.pd.setMessage("Please wait...");
        this.pd.setCancelable(false);
    }

    public void adsss() {
        if (this.sharedPreferences.getString("colorfulldayfulls_show_atnotific", "0").equalsIgnoreCase("1")) {
            loadwebview(this.link);
        } else {
            loadwebview(this.link);
        }
        Log.d("TAG", "The interstitial ad wasn't ready yet.");
    }

    @Override 
    public void onBackPressed() {
        if (this.sharedPreferences.getString("colorfulldayfulls_show_onexitnotifi", "0").equalsIgnoreCase("1")) {
            finishAffinity();
        } else if (this.sharedPreferences.getString("colorfullday_show_exitinnotifi", "0").equalsIgnoreCase("1")) {
            finishAffinity();
        } else {
            finishAffinity();
        }
    }
}
