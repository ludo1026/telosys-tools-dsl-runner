package org.telosys.tools.dsl.runner.main.config;

import com.esotericsoftware.yamlbeans.YamlReader;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfig;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfigConfig;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfigEntity;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfigGeneration;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelosysConfigReader {

    public TelosysConfig read(String filename) {
        try {
            YamlReader reader = new YamlReader(new FileReader(filename));
            //TelosysConfig telosysConfig = reader.read(TelosysConfig.class);
            //System.out.println(telosysConfig);

            TelosysConfig telosysConfig = new TelosysConfig();

            Map<String, Object> data = reader.read(HashMap.class);
            if(data.containsKey("config")) {
                Map<String, String> dataConfig = (Map<String, String>) data.get("config");
                TelosysConfigConfig telosysConfigConfig = new TelosysConfigConfig();
                telosysConfigConfig.rootFolder = dataConfig.get("rootFolder");
                telosysConfigConfig.dslFolder = dataConfig.get("dslFolder");
                telosysConfigConfig.bundleName = dataConfig.get("bundleName");
                telosysConfig.config = telosysConfigConfig;
            }
            if(data.containsKey("entities")) {
                Map<String, Object> dataEntities = (Map<String, Object>) data.get("entities");
                for(String entityName : dataEntities.keySet()) {
                    Map<String, String> dataEntity = (Map<String, String>) dataEntities.get(entityName);
                    TelosysConfigEntity entity = new TelosysConfigEntity();
                    entity.name = entityName;
                    entity.file = dataEntity.get("file");
                    entity.folder = dataEntity.get("folder");
                    telosysConfig.entities.put(entityName, entity);
                }
            }
            if(data.containsKey("generations")) {
                List<Map<String, Object>> dataGenerations = (List<Map<String, Object>>) data.get("generations");
                for(Map<String, Object> dataGeneration : dataGenerations) {
                    TelosysConfigGeneration generation = new TelosysConfigGeneration();
                    telosysConfig.generations.add(generation);
                    List<String> dataGenerationEntities = (List<String>) dataGeneration.get("entities");
                    for(String entityName : dataGenerationEntities) {
                        TelosysConfigEntity entity = telosysConfig.entities.get(entityName);
                        if(entity != null) {
                            generation.entities.add(entity);
                        }
                    }
                    List<String> dataGenerationTemplates = (List<String>) dataGeneration.get("templates");
                    generation.templates.addAll(dataGenerationTemplates);
                }
            }

            return telosysConfig;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
