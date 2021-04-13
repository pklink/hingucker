package net.einself.sicherheitswerkzeug.tools.subdomain;

import net.einself.sicherheitswerkzeug.observer.Result;

public class SubdomainResult extends Result {

    private final String hostName;
    private final String hostAddress;
    private final String fqdn;

    public SubdomainResult(String hostName, String hostAddress, String fqdn) {
        super(String.format("Found %s / %s", hostName, hostAddress));
        this.hostName = hostName;
        this.hostAddress = hostAddress;
        this.fqdn = fqdn;
    }

    public String getHostName() {
        return hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public String getFqdn() {
        return fqdn;
    }

}
