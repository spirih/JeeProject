package com.example.testspringmaven.utilitary;




import com.example.testspringmaven.object.Activity;
import com.example.testspringmaven.persistant.ActivitiesEntity;
import com.example.testspringmaven.repository.ActivitiesRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ActivityReader {
    public static ArrayList<ActivitiesEntity> analyseString(String path) throws FileNotFoundException {
        File f = new File(path);
        System.out.println(f.getAbsolutePath());
        JSONArray array =  new JSONArray(new JSONTokener(new FileInputStream(f)));
        ArrayList<ActivitiesEntity> list = new ArrayList<>();
        for(int i = 0; i < array.length(); i++){
            JSONObject jso = array.getJSONObject(i);
            list.add(activityAnalyzer(jso));

        }
        return list;

    }
    static ActivitiesEntity activityAnalyzer(JSONObject jso){
        Activity act = new Activity();
        act.setName(jso.getString("Name"));
        act.setDescription(jso.getString("Description"));
        act.setPathologies(jso.getString("Pathologies / Prévention"));
        act.setId(jso.getInt("id"));
        act.setUrl(jso.getString("url"));
        act.setAddress(jso.getString("address"));
        act.setLat(jso.getFloat("lat"));
        act.setLng(jso.getFloat("lng"));
        act.setDiscipline(jso.getString("Discipline"));
        return act.generateRepository();
    }
}
