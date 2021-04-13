package net.einself.sicherheitswerkzeug.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class Subdomain {

    @JSONField(ordinal = 100)
    private final String hostname;

    @JSONField(ordinal = 110)
    private final String ip;

    @JSONField(ordinal = 120)
    private final String fqdn;

    public Subdomain(String hostname, String ip, String fqdn) {
        this.hostname = hostname;
        this.ip = ip;
        this.fqdn = fqdn;
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
