package com.geekapps.rsstestapp.data.error;

import com.brilliatnbrains.markchat.data.error.ErrorUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class NetworkErrorChecker extends ErrorChecker<Response> {


    public NetworkErrorChecker(Response<ResponseBody> response) {
        super(response);
    }

    public boolean isSuccessful() {
        return objectToCheck.isSuccessful();
    }

    public FailedRequestException createException() {
        try {
            String errorJson = objectToCheck.errorBody().string();
            String result = ErrorUtils.INSTANCE.convertError(errorJson);
            JSONObject errorObject = new JSONObject(errorJson);
            if (objectToCheck.code() == 422) return buildValidationException(errorJson);

            if (errorObject.has("Message") && !errorObject.getString("Message").contains("null")) {
                return new FailedRequestException(errorObject.getString("Message"));
            }

            if (errorObject.has("errors") && errorObject.getJSONArray("errors") != null
                    && errorObject.getJSONArray("errors") != null) {
                return new FailedRequestException(getAllErrors(errorObject.getJSONArray("errors")));
            }

            return new FailedRequestException(result);
        } catch (JSONException jsonException) {
            throw new FailedRequestException(jsonException.getMessage());
        } catch (IOException e) {
            throw new FailedRequestException("Unknown IO error");
        }
    }

    private String getAllErrors(JSONArray errors) throws JSONException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < errors.length(); ++i) {
            String error = errors.getString(i);
            sb.append(error);
            if (i != errors.length() - 1)
                sb.append("; ");
        }
        return sb.toString();
    }

    private FailedRequestException buildValidationException(String json) throws JSONException {
        ValidationException validationException = new ValidationException();
        validationException.parseFromJson(json);
        return validationException;
    }
}