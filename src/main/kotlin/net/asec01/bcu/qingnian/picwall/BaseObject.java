package net.asec01.bcu.qingnian.picwall;

import com.google.gson.Gson;

public class BaseObject {

    public BaseObject() {
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toJson() {
        return toString();
    }

}
