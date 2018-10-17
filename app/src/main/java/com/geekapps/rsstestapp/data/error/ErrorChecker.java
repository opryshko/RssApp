package com.geekapps.rsstestapp.data.error;

public abstract class ErrorChecker<T> {

    protected T objectToCheck;

    public ErrorChecker(T objectToCheck) {
        this.objectToCheck = objectToCheck;
    }

    public abstract boolean isSuccessful();

    public abstract Exception createException();

}