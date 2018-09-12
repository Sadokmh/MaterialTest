package com.example.sadokmm.materialtest.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sadokmm.materialtest.R;

import org.json.JSONArray;
import org.json.JSONException;

import network.VolleySingleton;

public class myhomeFrag extends Fragment {


    VolleySingleton volleySingleton;
    RequestQueue requestQueue;
    TextView tt;

    public myhomeFrag() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_search, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getContext(),"Hello from home Frag",Toast.LENGTH_SHORT).show();

        tt=(TextView)getView().findViewById(R.id.tt);
        /*volleySingleton= VolleySingleton.getsInstance();
        requestQueue=volleySingleton.getRequestQueue();
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, "http://192.168.3.175:8080/profile", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {



                for (int i=0 ; i<response.length();i++){
                    try {
                        tt.setText(response.getJSONObject(i).get("nom").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(request);
        */

    }

    public static myhomeFrag newInstance() {

        Bundle args = new Bundle();

        myhomeFrag fragment = new myhomeFrag();
        fragment.setArguments(args);
        return fragment;
    }
}
