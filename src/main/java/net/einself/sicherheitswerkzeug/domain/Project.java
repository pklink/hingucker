package net.einself.sicherheitswerkzeug.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private final String domain;
    private final List<Subdomain> subdomains = new ArrayList<>();

    public Project(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public List<Subdomain> getSubdomains() {
        return subdomains;
    }

}
