package com.teknodate.volleyjsonparse;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MySingleTon {
    private Context context;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
    public MySingleTon(Context context) {
        this.context = context;
        this.requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        return Volley.newRequestQueue(context);
    }

    //synchronized : bu anahtar kelime ile senkronize işlemi yapılır
    public static synchronized MySingleTon getInstance(Context context) {
        return new MySingleTon(context);
    }

    public <T> void addToRequestGueue(Request<T> request) {
        requestQueue.add(request);
    }
}
