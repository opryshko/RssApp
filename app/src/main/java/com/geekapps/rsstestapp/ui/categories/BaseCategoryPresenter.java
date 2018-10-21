package com.geekapps.rsstestapp.ui.categories;

import com.geekapps.rsstestapp.data.db.categories.CategoryTableHelper;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaContent;
import com.geekapps.rsstestapp.data.network.pojo.category.MediaItem;
import com.geekapps.rsstestapp.mvp.BaseMvpPresenter;
import com.geekapps.rsstestapp.mvp.BaseMvpView;
import com.geekapps.rsstestapp.ui.categories.audiobooks.AudiobooksModel;
import com.geekapps.rsstestapp.ui.categories.audiobooks.AudiobooksView;

import java.util.List;

public abstract class BaseCategoryPresenter extends BaseMvpPresenter {
    protected CategoryTableHelper categoryTableHelper;
    public static CallBack callBack;

    public BaseCategoryPresenter(BaseMvpView view) {
        super(view);
    }

    private List<MediaItem> getMediasWithFavouriteMarks(List<MediaItem> medias) {
        MediaItem localMedia;

        if (categoryTableHelper.getMediasCount() > 0) {
            for (MediaItem media : medias) {
                localMedia = findMedia(categoryTableHelper.getAllMedias(), media.getId());
                if (localMedia != null)
                    media.setFavourite(localMedia.isFavourite());
            }
        }

        return medias;
    }

    private MediaItem findMedia(List<MediaItem> medias, Integer id) {
        for (MediaItem media : medias)
            if (media.getId().equals(id))
                return media;

        return null;
    }

    private List<MediaItem> initMediaPositions(List<MediaItem> medias) {
        for (int i = 0; i < medias.size(); i++)
            medias.get(i).setPosition(i + 1);
        return medias;
    }

    protected List<MediaItem> getPreparedMediasToDisplay(MediaContent mediaContent) {
        List<MediaItem> medias = initMediaPositions(mediaContent.getFeed().getResults());

        return getMediasWithFavouriteMarks(medias);
    }

    public interface CallBack {
        void onUpdateFavourites();
    }
}
