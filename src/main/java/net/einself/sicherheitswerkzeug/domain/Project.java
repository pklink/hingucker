package net.einself.sicherheitswerkzeug.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class Project {

    @JSONField(ordinal = 90)
    private final String target;

    @JSONField(ordinal = 100)
    private final List<Subdomain> subdomains = new ArrayList<>();

    public Project(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public List<Subdomain> getSubdomains() {
        return subdomains;
    }

}
