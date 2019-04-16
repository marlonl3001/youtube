package br.com.mdr.youtube.model;

/**
 * Created by ${USER_NAME} on 15/04/2019.
 */
public class Item {
    private ItemId id;
    private Snippet snippet;

    public ItemId getId() {
        return id;
    }

    public void setId(ItemId id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
}