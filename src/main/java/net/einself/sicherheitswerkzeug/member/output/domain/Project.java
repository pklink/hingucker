package net.einself.sicherheitswerkzeug.member.output.domain;

import com.alibaba.fastjson.annotation.JSONField;
import net.einself.sicherheitswerkzeug.core.data.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {

    @JSONField(ordinal = 90)
    private final String name;

    @JSONField(ordinal = 100)
    private final Map<String, List<Result>> results = new HashMap<>();

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, List<Result>> getResults() {
        return results;
    }

}
