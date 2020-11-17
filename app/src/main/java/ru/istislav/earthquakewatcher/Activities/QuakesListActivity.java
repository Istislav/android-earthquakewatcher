package ru.istislav.earthquakewatcher.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ru.istislav.earthquakewatcher.Data.EarthQuakeFiller;
import ru.istislav.earthquakewatcher.Model.EarthQuake;
import ru.istislav.earthquakewatcher.R;

public class QuakesListActivity extends ActivityWithQuakeOnMap {

    private ArrayList<String> arrayList;
    private ListView listView;
    private RequestQueue queue;
    private ArrayAdapter arrayAdapter;
    private List<EarthQuake> quakeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quakes_list);

        quakeList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview);

        queue = Volley.newRequestQueue(this);

        arrayList = new ArrayList<>();

        EarthQuakeFiller earthQuakeFiller = new EarthQuakeFiller(this);
        earthQuakeFiller.getEarthQuake();
    }


    @Override
    public void fillEarthQuake(ArrayList<EarthQuake> earthQuakes) {

    }
}