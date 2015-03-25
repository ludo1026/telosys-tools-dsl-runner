package org.telosys.tools.dsl.runner.main;

import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfig;
import org.telosys.tools.dsl.runner.main.config.TelosysConfigReader;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfigTemplate;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfigGeneration;
import org.telosys.tools.generator.api.GeneratorRunner;

public class Application {

    public void main() {

        TelosysConfigReader telosysConfigReader = new TelosysConfigReader();
        TelosysConfig telosysConfig = telosysConfigReader.read("/Users/ludovicchaboud/Code/workspace_telosys/telosys-tools-dsl-runner/src/main/resources/telosys.yml");

        GeneratorRunnerManager generatorRunnerManager = new GeneratorRunnerManager();

        GeneratorRunner generatorRunner =
                generatorRunnerManager
                        .getGeneratorRunner(
                                telosysConfig.config.rootFolder,
                                telosysConfig.config.dslFolder,
                                telosysConfig.config.bundleName);

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
