package com.example.dell.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import com.example.dell.popularmovies.PostersAdapter.onPosterClickListener;

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
        startActivity(i);
    }
}
