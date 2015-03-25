package org.telosys.tools.dsl.runner.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telosys.tools.commons.TelosysToolsLogger;
import org.telosys.tools.commons.cfg.TelosysToolsCfg;
import org.telosys.tools.dsl.generic.converter.Converter;
import org.telosys.tools.dsl.parser.DomainModelParser;
import org.telosys.tools.dsl.parser.model.DomainModel;
import org.telosys.tools.generator.api.GeneratorRunner;
import org.telosys.tools.generator.config.GeneratorConfig;
import org.telosys.tools.generic.model.Model;

import java.io.File;
import java.util.Properties;

public class Application {

    public void main() {

        GeneratorRunnerManager generatorRunnerManager = new GeneratorRunnerManager();

        String rootFolder = "/Users/ludovicchaboud/Code/workspace_telosys/telosys-tools-dsl-runner/sample";
        String dslFolder = "TelosysTools/model";
        String bundleName = "mongodb-templates";

        GeneratorRunner generatorRunner = generatorRunnerManager.getGeneratorRunner(rootFolder, dslFolder, bundleName);

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
    }

}
