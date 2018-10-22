package com.geekapps.rsstestapp.data.error;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorUtils {

    public static String convertError(String json) throws JSONException {
        return json.contains("error_description") ?
                new JSONObject(json).getString("error_description") : json;
    }
}