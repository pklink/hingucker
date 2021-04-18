package net.einself.hingucker.core.data;


import java.util.List;

public class HttpResponseDataResult implements Data, Result {

    private final String url;
    private final String method;
    private final List<HttpHeaderData> httpHeaders;

    public HttpResponseDataResult(String url, String method, List<HttpHeaderData> httpHeaders) {
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

    public List<HttpHeaderData> getHeaders() {
        return httpHeaders;
    }
}
