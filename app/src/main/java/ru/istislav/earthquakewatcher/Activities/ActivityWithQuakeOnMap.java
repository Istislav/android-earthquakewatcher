package ru.istislav.earthquakewatcher.Activities;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ru.istislav.earthquakewatcher.Model.EarthQuake;

abstract public class ActivityWithQuakeOnMap extends AppCompatActivity {
    abstract public void fillEarthQuake(ArrayList<EarthQuake> earthQuakes);
}
