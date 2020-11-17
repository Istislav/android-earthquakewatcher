package ru.istislav.earthquakewatcher.Data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import ru.istislav.earthquakewatcher.Activities.ActivityWithQuakeOnMap;
import ru.istislav.earthquakewatcher.Activities.MapsActivity;
import ru.istislav.earthquakewatcher.Model.EarthQuake;
import ru.istislav.earthquakewatcher.Util.Constants;

public class EarthQuakeFiller {
    private ActivityWithQuakeOnMap activity;
    private RequestQueue queue;

    public EarthQuakeFiller(ActivityWithQuakeOnMap activity) {
        this.activity = activity;

        queue = Volley.newRequestQueue(activity);
    }

    public void getEarthQuake(final int limit) {

        final ArrayList<EarthQuake> earthQuakes = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.URL,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                try {
                    JSONArray features = response.getJSONArray("features");
                    //for (int i = 0; i < features.length(); i++) {
                    int cycleLimit = limit > 0 ? limit : features.length();
                    for (int i = 0; i < cycleLimit; i++) {
                        JSONObject properties = features.getJSONObject(i).getJSONObject("properties");
                        String place = properties.getString("place");
                        Log.d("Properties:", place);

                        // getting geometry object
                        JSONObject geometry = features.getJSONObject(i).getJSONObject("geometry");
                        // longitude latitude location array
                        JSONArray coordinates = geometry.getJSONArray("coordinates");

                        double lon = coordinates.getDouble(0);
                        double lat = coordinates.getDouble(1);
                        EarthQuake earthQuake = new EarthQuake();

                        earthQuake.setPlace(place);
                        earthQuake.setLon(lon);
                        earthQuake.setLat(lat);
                        earthQuake.setType(properties.getString("type"));
                        earthQuake.setTime(properties.getLong("time"));
                        earthQuake.setMagnitude(properties.getDouble("mag"));
                        earthQuake.setDetailLink(properties.getString("detail"));

                        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                        String formattedDate = dateFormat.format(new Date(Long.valueOf(properties.getLong("time"))).getTime());
                        earthQuake.setFormattedDate(formattedDate);

                        earthQuakes.add(earthQuake);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                activity.fillEarthQuake(earthQuakes);

                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);
    }

}
