package com.geekapps.rsstestapp.data.error;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ValidationException extends FailedRequestException {

    private final Map<String, List<String>> invalidFields = new HashMap<>();

    public ValidationException() {
        super("Validation error");
    }

    private void addInvalidField(String key, final String value) {
        if (invalidFields.containsKey(key)) {
            invalidFields.get(key).add(value);
        } else {
            invalidFields.put(key, new ArrayList<String>() {{
                add(value);
            }});
        }
    }

    @Override
    public String getMessage() {
        if (invalidFields.isEmpty())
            return super.getMessage();
        else return invalidFields.values().iterator().next().get(0);

    }

    public Map<String, List<String>> getInvalidFields() {
        return invalidFields;
    }

    public void parseFromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        Iterator<String> keys = jsonObject.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) instanceof JSONArray) {
                for (int i = 0; i < jsonObject.getJSONArray(key).length(); i++) {
                    addInvalidField(key, jsonObject.getJSONArray(key).getString(i));
                }
            }
        }
    }
}