package org.telosys.tools.dsl.runner.main.config.bean;

import org.telosys.tools.commons.variables.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelosysConfig {

    public TelosysConfigConfig config;
    public List<Variable> variables = new ArrayList<Variable>();
    public Map<String, TelosysConfigTemplate> templates = new HashMap<String, TelosysConfigTemplate>();
    public List<TelosysConfigGeneration> generations = new ArrayList<TelosysConfigGeneration>();

}
