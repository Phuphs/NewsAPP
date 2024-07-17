package com.example.newsapppractice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class MySingleton {
    private static MySingleton INSTANCE;
    private RequestQueue requestQueue;
    private static Context context;

    private MySingleton(Context context)
    {
        MySingleton.context=context;
        requestQueue=getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context){
        if(INSTANCE==null)
        {
            INSTANCE=new MySingleton(context);
        }
        return INSTANCE;
    }
    public RequestQueue getRequestQueue(){
        if(requestQueue==null)
        {
            requestQueue=Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }


}
