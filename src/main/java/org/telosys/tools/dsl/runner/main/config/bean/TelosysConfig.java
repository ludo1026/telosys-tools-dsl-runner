package org.telosys.tools.dsl.runner.main.config.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelosysConfig {

    public TelosysConfigConfig config;
    public Map<String, TelosysConfigEntity> entities = new HashMap<String, TelosysConfigEntity>();
    public List<TelosysConfigGeneration> generations = new ArrayList<TelosysConfigGeneration>();

}
