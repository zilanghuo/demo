package com.zilanghuo.dbutil.script;

import java.util.List;

/**
 * Created by laiwufa on 2021-10-29
 */

public class SimpleDto {

    private String type;

    private String script;

    private List<SubParam> scriptParameters;

    public SimpleDto(String type, String script) {
        this.type = type;
        this.script = script;
    }

    public SimpleDto(String script) {
        this.script = script;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public List<SubParam> getScriptParameters() {
        return scriptParameters;
    }

    public void setScriptParameters(List<SubParam> scriptParameters) {
        this.scriptParameters = scriptParameters;
    }

    public SimpleDto(String type, String script, List<SubParam> scriptParameters) {
        this.type = type;
        this.script = script;
        this.scriptParameters = scriptParameters;
    }
}
