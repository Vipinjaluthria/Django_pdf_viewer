package com.example.djangopdfview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

public class showpdf extends AppCompatActivity {
    PDFView pdfView;
    String LINK;
    String filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpdf);
        pdfView = findViewById(R.id.pdf);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        LINK=intent.getStringExtra("LINK");
        filename=intent.getStringExtra("NAME");
      /*  pdfView.loadUrl(LINK);
        pdfView.setWebViewClient(new WebViewClient());
        pdfView.getSettings().setJavaScriptEnabled(true);
            Intent d = new Intent();

          d.setType("application/pdf");
        d.setAction(Intent.ACTION_GET_CONTENT);
          startActivityForResult(d,12);*/

        File file1=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+filename);
        pdfView.fromFile(file1).defaultPage(1).onLoad(new OnLoadCompleteListener() {
            @Override
            public void loadComplete(int nbPages) {
                Toast.makeText(showpdf.this, "Loded", Toast.LENGTH_SHORT).show();

            }
        }).onPageChange(new OnPageChangeListener() {
            @Override
            public void onPageChanged(int page, int pageCount) {

            }
        }).showMinimap(false).swipeVertical(true).enableSwipe(true).load();
        pdfView.getOptimalPageWidth();
        pdfView.getZoom();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        return super.onOptionsItemSelected(item);
    }
}
