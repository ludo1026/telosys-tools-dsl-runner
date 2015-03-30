/**
 * Copyright (C) 2008-2014  Telosys project org. ( http://www.telosys.org/ )
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.telosys.tools.dsl.runner.main.config;

import com.esotericsoftware.yamlbeans.YamlReader;
import org.telosys.tools.commons.variables.Variable;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfig;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfigConfig;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfigTemplate;
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
            if(data.containsKey("variables")) {
                Map<String, String> dataVariables = (Map<String, String>) data.get("variables");
                for(String variableName : dataVariables.keySet()) {
                    String variableValue = dataVariables.get(variableName);
                    Variable variable = new Variable(variableName, variableValue);
                    telosysConfig.variables.add(variable);
                }
            }
            if(data.containsKey("templates")) {
                Map<String, Object> dataTemplates = (Map<String, Object>) data.get("templates");
                for(String templateName : dataTemplates.keySet()) {
                    Map<String, String> dataTemplate = (Map<String, String>) dataTemplates.get(templateName);
                    TelosysConfigTemplate template = new TelosysConfigTemplate();
                    template.name = templateName;
                    template.file = dataTemplate.get("file");
                    template.folder = dataTemplate.get("folder");
                    telosysConfig.templates.put(templateName, template);
                }
            }
            if(data.containsKey("generations")) {
                List<Map<String, Object>> dataGenerations = (List<Map<String, Object>>) data.get("generations");
                for(Map<String, Object> dataGeneration : dataGenerations) {
                    TelosysConfigGeneration generation = new TelosysConfigGeneration();
                    telosysConfig.generations.add(generation);
                    if(dataGeneration.containsKey("entities")) {
                        List<String> dataGenerationEntities = (List<String>) dataGeneration.get("entities");
                        generation.entities.addAll(dataGenerationEntities);
                    }
                    List<String> dataGenerationTemplates = (List<String>) dataGeneration.get("templates");
                    for(String templateName : dataGenerationTemplates) {
                        TelosysConfigTemplate template = telosysConfig.templates.get(templateName);
                        if(template != null) {
                            generation.templates.add(template);
                        }
                    }
                }
            }

            return telosysConfig;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
