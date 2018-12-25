package com.example.dell.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.popularmovies.PostersAdapter.onPosterClickListener;
import com.example.dell.popularmovies.utils.JsonUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements onPosterClickListener {
    RecyclerView posters;
    PostersAdapter postersAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        posters = (RecyclerView)findViewById(R.id.rv_posters);
        postersAdapter = new PostersAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this ,2);
        posters.setHasFixedSize(true);
        posters.setLayoutManager(gridLayoutManager);
        posters.setAdapter(postersAdapter);



        makeDataSearchQuery();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            /*
             * When you click the reset menu item, we want to start all over
             * and display the pretty gradient again. There are a few similar
             * ways of doing this, with this one being the simplest of those
             * ways. (in our humble opinion)
             */
            case R.id.popular_movies:
                return true;

            case R.id.top_rate:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPosterClick(int posterIndex) {
        Intent i = new Intent(MainActivity.this , MovieDetails.class);
        i.putExtra(MovieDetails.EXTRA_POSITION, posterIndex);
        startActivity(i);
    }



    private void makeDataSearchQuery() {
        URL dataUrl = JsonUtils.buildUrl();
        new DataQueryTask().execute(dataUrl);
    }

    public class DataQueryTask extends AsyncTask<URL, Void, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String dataResults = null;
            try {
                dataResults = JsonUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dataResults;
        }

        @Override
        protected void onPostExecute(String dataResults) {
            if (dataResults != null && !dataResults.equals("")) {
//                dataResults
            }
            else
                Toast.makeText(MainActivity.this,"there is a connection error" ,Toast.LENGTH_LONG).show();
        }
    }
}
