package com.geekapps.rsstestapp.ui.categories;

import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpPresenter;
import com.geekapps.rsstestapp.mvp.BaseMvpView;

import java.util.List;

public abstract class BaseCategoryPresenter extends BaseMvpPresenter {

    public static CallBack callBack;

    public BaseCategoryPresenter(BaseMvpView view) {
        super(view);
    }

    protected MediaItem findMedia(List<MediaItem> medias, Integer id) {
        for (MediaItem media : medias)
            if (media.getId().equals(id))
                return media;

        return null;
    }

    protected List<MediaItem> initMediaPositions(List<MediaItem> medias) {
        for (int i = 0; i < medias.size(); i++)
            medias.get(i).setPosition(i + 1);

        return medias;
    }

    public interface CallBack {
        void onUpdateFavourites();
    }
}
