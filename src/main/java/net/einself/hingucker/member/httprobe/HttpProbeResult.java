package net.einself.hingucker.member.httprobe;

import net.einself.hingucker.core.message.Message;

import java.util.List;

public class HttpProbeResult implements Message {

    private final String method;
    private final String url;
    private final List<Header> headers;

    public HttpProbeResult(String method, String url, List<Header> headers) {
        this.method = method;
        this.url = url;
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public static class Header {

        private final String name;
        private final String value;

        public Header(String name, String value) {
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
}
