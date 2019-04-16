package br.com.mdr.youtube.model;

/**
 * Created by ${USER_NAME} on 15/04/2019.
 */
public class Thumbnail {
    private ThumbnailType defaultThumbnailType;
    private ThumbnailType medium;
    private ThumbnailType high;

    public ThumbnailType getDefaultThumbnailType() {
        return defaultThumbnailType;
    }

    public void setDefaultThumbnailType(ThumbnailType defaultThumbnailType) {
        this.defaultThumbnailType = defaultThumbnailType;
    }

    public ThumbnailType getMedium() {
        return medium;
    }

    public void setMedium(ThumbnailType medium) {
        this.medium = medium;
    }

    public ThumbnailType getHigh() {
        return high;
    }

    public void setHigh(ThumbnailType high) {
        this.high = high;
    }
}
