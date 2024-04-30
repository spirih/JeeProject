package com.example.testspringmaven.utilitary;




import com.example.testspringmaven.object.Activity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.util.ArrayList;

public class ActivityReader {
    static ArrayList<Activity> analyseString(String path){
        JSONArray array =  new JSONArray(path);
        ArrayList<Activity> list = new ArrayList<>();
        for(int i = 0; i < array.length(); i++){
            JSONObject jso = array.getJSONObject(i);
            list.add(activityAnalyzer(jso));
        }
        return list;

    }
    static Activity activityAnalyzer(JSONObject jso){
        Activity act = new Activity();
        act.setName(jso.getString("Name"));
        act.setDescription(jso.getString("Description"));
        act.setPathologies(jso.getString("Pathologies / Prévention"));
        act.setId(jso.getInt("id"));
        act.setUrl(jso.getString("url"));
        act.setAddress(jso.getString("address"));
        act.setLat(jso.getFloat("lat"));
        act.setLng(jso.getFloat("lng"));
        return act;
    }
}
