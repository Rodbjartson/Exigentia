package com.example.koenraad.Exigentia;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Koenraad on 20.01.2017.
 *
 * This class handles requests to web server by using Google Volley API
 * Google Volley API is very powerful and abstracts many low-level details when establishing
 * connection with a web server.
 * Volley API does not run on the main thread, which is the correct way of doing it in android.
 * If it was not doing work in a background thread, the main thread would be blocked(perhaps).
 * This is all done in an asynchronous way, which means that methods may behave somewhat
 * different than you would expect. A method which returns a string for example
 * may return a null object, before it is actually done waiting on the response from server
 * This means that we have to introduce callback methods with for instance interfaces.
 *
 * Useful links:
 * http://stackoverflow.com/questions/28120029/how-can-i-return-value-from-function-onresponse-of-volley
 * http://stackoverflow.com/questions/41794503/method-returns-before-finished-executing/41794725#41794725
 */

public class Server_interaction
{
    String server_url = "http://10.0.0.126/update_location.php";
    String response_string;
    //RequestQueue queue;
    Context context;

    public Server_interaction(Context context)
    {
         this.context = context;
        Server_singleton.getInstance(context).getRequestQueue();
    }


    public static final String TAG = Server_interaction.class.getSimpleName();

    public void post_request()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        //response_string = response;
                        //callback.onSuccess(response_string);
                        //requestQueue.stop();
                        Log.i(TAG, "the response is: "+ response);

                    }
                }
                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        response_string = "Something went wrong";
                        Log.i(TAG, "onerrormsg: "+ error.getMessage());
                        //requestQueue.stop();
                        Log.i(TAG, "something went wrong. Is the server up and running?");
                    }

                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String the_name = "olaf";
                String the_mail = "lalalal";
                String the_country = "Norway";
                String the_latitude = "33";
                String the_longitude = "99";


                Map<String, String> params = new HashMap<String, String>();
                params.put("email", "wha");
                params.put("phonenumber", the_mail);
                params.put("country", the_country);
                params.put("latitude", String.valueOf(the_latitude));
                params.put("longitude", String.valueOf(the_longitude));

                Log.i(TAG, "inside getparams : "+params.toString());
                return params;
            }

        };//stringrequest parameter end

        //add request to requestqueue
        //Log.i(TAG, "the stringrequest: "+ stringRequest);

        Server_singleton.getInstance(context).addToRequestQueue(stringRequest);
        Log.i(TAG, "the response again:: "+ response_string);


    }

}



