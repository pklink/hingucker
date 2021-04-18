package net.einself.sicherheitswerkzeug.core.data;

public class UrlData implements Data {

    private final String url;

    public UrlData(String url) {
        this.url = url;
    }

    public String get() {
        return url;
    }

}
