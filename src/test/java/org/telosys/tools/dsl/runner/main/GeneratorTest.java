package org.telosys.tools.dsl.runner.main;

import org.junit.Test;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfig;
import org.telosys.tools.generator.api.GeneratorRunner;

public class GeneratorTest {

    //@Test
    public void testGenerateBeanMongo() {
        // Given

        RunnerManager generatorRunnerManager = new RunnerManager();

        TelosysConfig telosysConfig = new TelosysConfig();
        telosysConfig.config.rootFolder = "/Users/ludovicchaboud/Code/workspace_telosys/telosys-tools-dsl-runner/sample";
        telosysConfig.config.dslFolder = "TelosysTools/model";
        telosysConfig.config.bundleName = "mongodb-templates";

        Runner runner = generatorRunnerManager.getRunner(telosysConfig);

        String entityClassName = "Employee"; // Nom de la classe de l'entité à générer";
        String outputFile = "Employee.java"; // Nom du fichier généré";
        String outputFolder = "src/main/java"; // Sous-répertoire à la racine du projet";
        String templateFileName = "bean_mongo.vm"; // Nom du fichier de template de génération";

        // When
        runner.generatorRunner.generateEntity(
                entityClassName,
                outputFile,
                outputFolder,
                templateFileName);

        // Then

    }

}
