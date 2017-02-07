package com.example.koenraad.Exigentia;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Koenraad on 27.01.2017.
 */

public class Server_singleton
{
    private static Server_singleton anInstance;
    private RequestQueue requestQueue;
    private static Context aCtx;

    private Server_singleton(Context context)
    {
        aCtx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized Server_singleton getInstance(Context context)
    {
        if(anInstance == null)
        {
            anInstance = new Server_singleton(context);
        }
        return anInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(aCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request)
    {
        requestQueue.add(request);
    }

}