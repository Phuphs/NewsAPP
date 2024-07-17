package com.example.newsapppractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NewsAdapter.NewsItemClicked {




    private RecyclerView recyclerView;
    private TextView home,sports,technology,health,business,entertainment;
    private NewsAdapter newsAdapter;
    private ActionBar actionBar;
    private RelativeLayout relativeLayout;
    String currentField="home";
    String country="in";
    String[] fields={"general","sports","technology","health","business","entertainment"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        actionBar=getSupportActionBar();
        actionBar.setTitle("News Daily");
        recyclerView=findViewById(R.id.recyclerView);
        home=findViewById(R.id.home);
        sports=findViewById(R.id.sports);
        technology=findViewById(R.id.technology);
        health=findViewById(R.id.health);
        business=findViewById(R.id.business);
        entertainment=findViewById(R.id.entertainment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchData("https://saurav.tech/NewsAPI/top-headlines/category/general/"+country+".json");
        newsAdapter= new NewsAdapter(this);
        recyclerView.setAdapter(newsAdapter);
        home.setBackgroundResource(R.drawable.bg_scroll_view);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData("https://saurav.tech/NewsAPI/top-headlines/category/general/"+country+".json");
                recyclerView.setAdapter(newsAdapter);
                changeBack();
                home.setBackgroundResource(R.drawable.bg_scroll_view);
                currentField="home";
            }
        });
        //sports
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData("https://saurav.tech/NewsAPI/top-headlines/category/sports/"+country+".json");
                recyclerView.setAdapter(newsAdapter);
                changeBack();
                sports.setBackgroundResource(R.drawable.bg_scroll_view);
                currentField="sports";

            }
        });
        //health
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData("https://saurav.tech/NewsAPI/top-headlines/category/health/"+country+".json");
                recyclerView.setAdapter(newsAdapter);
                changeBack();
                health.setBackgroundResource(R.drawable.bg_scroll_view);
                currentField="health";
            }
        });
        //technology
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData("https://saurav.tech/NewsAPI/top-headlines/category/technology/"+country+".json");
                recyclerView.setAdapter(newsAdapter);
                changeBack();
                technology.setBackgroundResource(R.drawable.bg_scroll_view);
                currentField="technology";
            }
        });
        //entertainment
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData("https://saurav.tech/NewsAPI/top-headlines/category/entertainment/"+country+".json");
                recyclerView.setAdapter(newsAdapter);
                changeBack();
                entertainment.setBackgroundResource(R.drawable.bg_scroll_view);
                currentField="entertainment";
            }
        });
        //business
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData("https://saurav.tech/NewsAPI/top-headlines/category/business/"+country+".json");
                recyclerView.setAdapter(newsAdapter);
                changeBack();
                business.setBackgroundResource(R.drawable.bg_scroll_view);
                currentField="business";
            }
        });

        final GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
            private static final int SWIPE_THRESHOLD = 50;
            private static final int SWIPE_VELOCITY_THRESHOLD = 50;

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1 == null || e2 == null) {
                    return false;
                }

                float diffX = e2.getX() - e1.getX();
                float diffY = e2.getY() - e1.getY();

                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            // Right swipe detected
                            // Handle your logic here
                            int ind=0;
                            for(int i=0;i<fields.length;i++)
                            {
                                if(fields[i]==currentField)
                                {
                                    ind=i;
                                    break;
                                }
                            }
                            if(ind>0){
                                fetchData("https://saurav.tech/NewsAPI/top-headlines/category/"+fields[ind-1]+"/"+country+".json");
                                recyclerView.setAdapter(newsAdapter);
                                changeBack();
                               currentField=fields[ind-1];
                               switch (ind-1){
                                   case 0:
                                       home.setBackgroundResource(R.drawable.bg_scroll_view);
                                       break;
                                   case 1:
                                       sports.setBackgroundResource(R.drawable.bg_scroll_view);
                                       break;
                                   case 2:
                                       technology.setBackgroundResource(R.drawable.bg_scroll_view);
                                       break;
                                   case 3:
                                       health.setBackgroundResource(R.drawable.bg_scroll_view);
                                       break;
                                   case 4:
                                       business.setBackgroundResource(R.drawable.bg_scroll_view);
                                       break;
                               }

                            }
                        } else {
                            // Left swipe detected
                            // Handle your logic here
                            int ind=0;
                            for(int i=0;i<fields.length;i++)
                            {
                                if(fields[i]==currentField)
                                {
                                    ind=i;
                                    break;
                                }
                            }
                            if(ind<fields.length-1){
                                fetchData("https://saurav.tech/NewsAPI/top-headlines/category/"+fields[ind+1]+"/"+country+".json");
                                recyclerView.setAdapter(newsAdapter);
                                changeBack();
                                currentField=fields[ind+1];
                                switch (ind+1){
                                    case 1:
                                        sports.setBackgroundResource(R.drawable.bg_scroll_view);
                                        break;
                                    case 2:
                                        technology.setBackgroundResource(R.drawable.bg_scroll_view);
                                        break;
                                    case 3:
                                        health.setBackgroundResource(R.drawable.bg_scroll_view);
                                        break;
                                    case 4:
                                        business.setBackgroundResource(R.drawable.bg_scroll_view);
                                        break;
                                    case 5:
                                        entertainment.setBackgroundResource(R.drawable.bg_scroll_view);
                                        break;
                                }

                            }
                        }
                    }
                }

                return false;
            }
        });

        ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, 0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Swiped callback (if needed)
            }

            @Override
            public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return 0; // Disable swipe for all directions
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return gestureDetector.onTouchEvent(e);
            }
        });


    }
    private void reRun(){
        fetchData("https://saurav.tech/NewsAPI/top-headlines/category/general/"+country+".json");
        changeBack();
        recyclerView.setAdapter(newsAdapter);
        home.setBackgroundResource(R.drawable.bg_scroll_view);
    }


    private void fetchData(String url) {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray newsJSONArray= response.getJSONArray("articles");
                            ArrayList<News> newsArray=new ArrayList<>();
                            for(int i=0;i<newsJSONArray.length();i++){
                                JSONObject newsJSONObject=newsJSONArray.getJSONObject(i);
                                if(newsJSONObject.getString("publishedAt").contains("2023-05"));
                                {
                                    News news = new News(
                                            newsJSONObject.getString("title"),
                                            newsJSONObject.getString("author"),
                                            newsJSONObject.getString("url"),
                                            newsJSONObject.getString("urlToImage")
                                    );
                                    newsArray.add(news);
                                }
                            }
                            newsAdapter.updateNews(newsArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
        new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
    public void onItemClicked(News item) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.getUrl()));
    }
    private void changeBack()
    {
        home.setBackgroundResource(R.drawable.bg_norm);
        sports.setBackgroundResource(R.drawable.bg_norm);
        entertainment.setBackgroundResource(R.drawable.bg_norm);
        health.setBackgroundResource(R.drawable.bg_norm);
        technology.setBackgroundResource(R.drawable.bg_norm);
        business.setBackgroundResource(R.drawable.bg_norm);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_view,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.india:
                country="in";
                reRun();
                break;
            case R.id.usa:
                country="us";
                reRun();
                break;
            case R.id.australia:
                country="au";
                reRun();
                break;
            case R.id.france:
                country="fr";
                reRun();
                break;
            case R.id.russia:
                country="ru";
                reRun();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}


//api key
//9ef231c1fc7d43fc9a72d934871dc593