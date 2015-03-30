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
package org.telosys.tools.dsl.runner.main;

import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfig;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfigGeneration;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfigTemplate;
import org.telosys.tools.generator.api.GeneratorRunner;

public class Runner {

    protected TelosysConfig telosysConfig;
    protected GeneratorRunner generatorRunner;

    public Runner(TelosysConfig telosysConfig, GeneratorRunner generatorRunner) {
        this.telosysConfig = telosysConfig;
        this.generatorRunner = generatorRunner;
    }

    public void main() {

        for(TelosysConfigGeneration generation : telosysConfig.generations) {

            if(generation.entities.isEmpty()) {

                for (TelosysConfigTemplate template : generation.templates) {
                    String templateFileName = template.name;
                    String outputFile = template.file;
                    String outputFolder = template.folder;
                    generatorRunner.generateEntity(
                            "",
                            outputFile,
                            outputFolder,
                            templateFileName);
                }

            } else {

                for (String entityClassName : generation.entities) {

                    for (TelosysConfigTemplate template : generation.templates) {
                        String templateFileName = template.name;
                        String outputFile = template.file;
                        String outputFolder = template.folder;
                        generatorRunner.generateEntity(
                                entityClassName,
                                outputFile,
                                outputFolder,
                                templateFileName);
                    }
                }

            }
        }

    }

}
