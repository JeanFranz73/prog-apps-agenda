package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Model<T> {

    public String toJson() {
        Gson json = new GsonBuilder().setPrettyPrinting().create();
        return json.toJson(this);
    }
}
