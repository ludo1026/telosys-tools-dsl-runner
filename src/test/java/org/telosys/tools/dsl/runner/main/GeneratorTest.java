package org.telosys.tools.dsl.runner.main;

import org.junit.Test;
import org.telosys.tools.generator.api.GeneratorRunner;

public class GeneratorTest {

    @Test
    public void testGenerateBeanMongo() {
        // Given

        GeneratorRunnerManager generatorRunnerManager = new GeneratorRunnerManager();

        String rootFolder = "/Users/ludovicchaboud/Code/workspace_telosys/telosys-tools-dsl-runner/sample";
        String dslFolder = "TelosysTools/model";
        String bundleName = "mongodb-templates";

        GeneratorRunner generatorRunner = generatorRunnerManager.getGeneratorRunner(rootFolder, dslFolder, bundleName);

        String entityClassName = "Employee"; // Nom de la classe de l'entité à générer";
        String outputFile = "Employee.java"; // Nom du fichier généré";
        String outputFolder = "src/main/java"; // Sous-répertoire à la racine du projet";
        String templateFileName = "bean_mongo.vm"; // Nom du fichier de template de génération";

        // When
        generatorRunner.generateEntity(
                entityClassName,
                outputFile,
                outputFolder,
                templateFileName);

        // Then

    }

}
