package br.com.mdr.youtube.model;

/**
 * Created by ${USER_NAME} on 15/04/2019.
 */
public class Snippet {
    private String title;
    private String description;
    private Thumbnail thumbnails;
    private String channelTitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnail thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }
}
