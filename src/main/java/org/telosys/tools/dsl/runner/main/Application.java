package org.telosys.tools.dsl.runner.main;

import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfig;
import org.telosys.tools.dsl.runner.main.config.TelosysConfigReader;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfigEntity;
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

            for(TelosysConfigEntity entity : generation.entities) {
                String entityClassName = entity.name;
                String outputFile = entity.file;
                String outputFolder = entity.folder;

                for (String templateFileName : generation.templates) {
                    generatorRunner.generateEntity(
                            entityClassName,
                            outputFile,
                            outputFolder,
                            templateFileName);
                }
            }
        }

        /*
        String rootFolder = "/Users/ludovicchaboud/Code/workspace_telosys/telosys-tools-dsl-runner/sample";
        String dslFolder = "TelosysTools/model";
        String bundleName = "mongodb-templates";

        GeneratorRunner generatorRunner = generatorRunnerManager.getGeneratorRunner(rootFolder, dslFolder, bundleName);
*/
        /*
        // Generator entity
        String entityClassName = "Employee"; // Nom de la classe de l'entité à générer";
        String outputFile = "Employee.java"; // Nom du fichier généré";
        String outputFolder = "src/main/java"; // Sous-répertoire à la racine du projet";
        String templateFileName = "bean_mongo.vm"; // Nom du fichier de template de génération";

        generatorRunner.generateEntity(
                entityClassName,
                outputFile,
                outputFolder,
                templateFileName);
        */
    }

}
