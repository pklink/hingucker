package net.einself.hingucker.core.message;


import java.util.List;

public class HttpResponseResultMessage implements ResultMessage {

    private final String url;
    private final String method;
    private final List<HttpHeaderMessage> httpHeaders;

    public HttpResponseResultMessage(String url, String method, List<HttpHeaderMessage> httpHeaders) {
        this.url = url;
        this.method = method;
        this.httpHeaders = httpHeaders;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public List<HttpHeaderMessage> getHeaders() {
        return httpHeaders;
    }
}
