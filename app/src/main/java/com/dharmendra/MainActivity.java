package com.dharmendra;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Creating a List of superheroes
    private List<IssuePojo> listIssuePojo;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);


        //Initializing our IssuePojo  list
        listIssuePojo = new ArrayList<>();

        //Calling method to get data
        getData();
    }

    //This method will get data from the web api
    private void getData() {
        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Loading Data", "Please wait...", false, false);

        //Creating a json array request


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(NetResponseConfig.DATA_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing progress dialog
                        loading.dismiss();

                        //calling method to parse json array
                        parseData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            IssuePojo issuePojo = new IssuePojo();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                issuePojo.setIssueTitle(json.getString(NetResponseConfig.TAG_NAME));
                issuePojo.setDescriptions(json.getString(NetResponseConfig.TAG_DESCRIPTION));
                issuePojo.setReporterName(json.getJSONObject("user").getString(NetResponseConfig.TAG_CREATED_BY));
                issuePojo.setLatestUpdatedTime(json.getString(NetResponseConfig.TAG_UPDATED_TIME));


            } catch (JSONException e) {
                e.printStackTrace();
            }
            listIssuePojo.add(issuePojo);
            Collections.sort(listIssuePojo);

        }

        //Finally initializing our adapter
        adapter = new IssueAdapter(listIssuePojo, this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
