package net.einself.hingucker.member.output.domain;

import com.alibaba.fastjson.annotation.JSONField;
import net.einself.hingucker.core.message.ResultMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {

    @JSONField(ordinal = 90)
    private final String name;

    @JSONField(ordinal = 100)
    private final Map<String, List<ResultMessage>> results = new HashMap<>();

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, List<ResultMessage>> getResults() {
        return results;
    }

}
