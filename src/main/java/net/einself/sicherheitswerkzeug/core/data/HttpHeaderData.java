package net.einself.sicherheitswerkzeug.core.data;

public class HttpHeaderData implements Data {

    private final String name;
    private final String value;

    public HttpHeaderData(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
