package net.einself.hingucker.core.message;

public class UrlMessage implements Message {

    private final String url;

    public UrlMessage(String url) {
        this.url = url;
    }

    public String get() {
        return url;
    }

}
