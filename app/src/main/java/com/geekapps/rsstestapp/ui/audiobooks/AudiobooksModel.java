package com.geekapps.rsstestapp.ui.audiobooks;

import com.geekapps.rsstestapp.data.network.pojo.Audiobooks;

import java.util.HashMap;

import io.reactivex.Observable;

public interface AudiobooksModel {

    Observable<Audiobooks> getTop25Audiobooks();
}
