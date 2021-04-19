package net.einself.hingucker.core.message;

public class DomainResultMessage implements ResultMessage {

    private final String domain;

    public DomainResultMessage(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }
}
