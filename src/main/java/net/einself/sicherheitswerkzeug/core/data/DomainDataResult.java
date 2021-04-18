package net.einself.sicherheitswerkzeug.core.data;

public class DomainDataResult implements Data, Result {

    private final String domain;

    public DomainDataResult(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }
}
