package net.einself.sicherheitswerkzeug.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.net.InetAddress;

public class Subdomain {

    @JSONField(ordinal = 100)
    private final String hostname;

    @JSONField(ordinal = 110)
    private final String ip;

    @JSONField(ordinal = 120)
    private final String fqdn;

    public Subdomain(InetAddress inetAddress) {
        hostname = inetAddress.getHostName();
        ip = inetAddress.getHostAddress();
        fqdn = inetAddress.getCanonicalHostName();
    }

    public String getHostname() {
        return hostname;
    }

    public String getIp() {
        return ip;
    }

    public String getFqdn() {
        return fqdn;
    }
}
