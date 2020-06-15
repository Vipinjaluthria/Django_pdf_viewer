package com.example.djangopdfview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity implements Clicklistneronrecyclerview {
    RecyclerView recyclerView;
    AllpdfAdpater adpater;
    List<Model> modelArrayList = new ArrayList<>();
    String fileName;
    ProgressDialog progressDialog;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(MainActivity.this);
        recyclerView = findViewById(R.id.recyclerviewshowallpdf);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        permissions();



    }

    private void permissions() {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED  &&
             ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
            ShowAllpdf();
            }

        else {
            Toast.makeText(MainActivity.this, "else", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(MainActivity.this,new  String[] {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},12);
        }
    }


    private void ShowAllpdf() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.249:8000/").
                addConverterFactory(GsonConverterFactory.create()).build();
        JsonApi jsonApi = retrofit.create(JsonApi.class);
        Call<List<Model>> call = jsonApi.getPosts();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                if (response.body() != null) {
                    List<Model> models = response.body();
                    for (Model model : models) {
                        Model post = new Model(model.getUsername(), model.getLink(), model.getTeamname(), model.getEventname());

                        modelArrayList.add(post);
                    }
                    adpater = new AllpdfAdpater(modelArrayList,MainActivity.this);
                    adpater.notifyDataSetChanged();
                    recyclerView.setAdapter(adpater);



                } else {
                    Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.e("vipin", "" + t);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        //SearchView searchView = (SearchView) menuItem.getActionView();
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.249:8000/").
                        addConverterFactory(GsonConverterFactory.create()).build();


                JsonApi jsonApi = retrofit.create(JsonApi.class);
                Call<List<Model>> call = jsonApi.getseacrchPosts(query);
                call.enqueue(new Callback<List<Model>>() {
                    @Override
                    public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                        if (response.body() != null) {
                            List<Model> models = response.body();
                            modelArrayList.clear();
                            for (Model model : models) {
                                Model post = new Model(model.getUsername(), model.getLink(), model.getTeamname(), model.getEventname());

                                modelArrayList.add(post);
                            }
                            adpater = new AllpdfAdpater(modelArrayList,MainActivity.this);
                            adpater.notifyDataSetChanged();
                            recyclerView.setAdapter(adpater);



                        } else {
                            Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Model>> call, Throwable t) {
                        Log.e("vipin", "" + t);

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.249:8000/").
                        addConverterFactory(GsonConverterFactory.create()).build();


                JsonApi jsonApi = retrofit.create(JsonApi.class);
                Call<List<Model>> call = jsonApi.getseacrchPosts(newText);
                call.enqueue(new Callback<List<Model>>() {
                    @Override
                    public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                        if (response.body() != null) {
                            List<Model> models = response.body();
                            modelArrayList.clear();
                            for (Model model : models) {
                                Model post = new Model(model.getUsername(), model.getLink(), model.getTeamname(), model.getEventname());

                                modelArrayList.add(post);
                            }
                            adpater = new AllpdfAdpater(modelArrayList,MainActivity.this);
                            adpater.notifyDataSetChanged();
                            recyclerView.setAdapter(adpater);



                        } else {
                            Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Model>> call, Throwable t) {
                        Log.e("vipin", "" + t);

                    }
                });
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void click(int position) {
        String link=modelArrayList.get(position).getLink();
        Download(link);
        Intent intent=new Intent(getApplicationContext(),showpdf.class);
        intent.putExtra("NAME",fileName);
        intent.putExtra("LINK",link);
        progressDialog.setTitle("Loading.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "countdown", Toast.LENGTH_SHORT).show();

                startActivity(intent);

            }
        }.start()
        ;

    }
    private void Download(String link) {
        fileName = URLUtil.guessFileName(link, null, MimeTypeMap.getFileExtensionFromUrl(link));

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(link));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName);
        DownloadManager downloadManager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        request.setMimeType("application/pdf");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadManager.enqueue(request);



    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==12)
        {
            if(grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)
            {

            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        super.onBackPressed();
    }



}