package org.telosys.tools.dsl.runner.main;

import org.telosys.tools.commons.TelosysToolsException;
import org.telosys.tools.commons.TelosysToolsLogger;
import org.telosys.tools.commons.cfg.TelosysToolsCfg;
import org.telosys.tools.commons.cfg.TelosysToolsCfgManager;
import org.telosys.tools.dsl.generic.converter.Converter;
import org.telosys.tools.dsl.parser.DomainModelParser;
import org.telosys.tools.dsl.parser.model.DomainModel;
import org.telosys.tools.generator.Generator;
import org.telosys.tools.generator.api.GeneratorRunner;
import org.telosys.tools.generator.config.GeneratorConfig;
import org.telosys.tools.generic.model.Model;

import java.io.File;

public class GeneratorRunnerManager {

    static TelosysToolsLogger logger = new TelosysToolsLogger() {
        @Override
        public void log(Object object, String s) {
            System.out.println("log : "+((object==null)?"":object.toString())+" : "+s);
        }

        @Override
        public void log(String s) {
            System.out.println("log : "+s);
        }

        @Override
        public void error(String s) {
            System.out.println("error : "+s);
        }

        @Override
        public void info(String s) {
            System.out.println("info : "+s);
        }

        @Override
        public void exception(Throwable exception) {
            System.out.println("exception : "+exception);
        }
    };

    /**
     * Return the generator
     * @param projectAbsolutePath Absolute path of the project
     * @param dslFolder Folder of DSL files
     * @param bundleName Bundle name
     * @return Generator
     */
    public GeneratorRunner getGeneratorRunner(String projectAbsolutePath, String dslFolder, String bundleName) {
        // call parser tool
        DomainModelParser domainModelParser = new DomainModelParser();
        logger.info("\nParse folder : "+dslFolder);
        DomainModel domainModel = domainModelParser.parse(new File(projectAbsolutePath+"/"+dslFolder));
        logger.info("\n"+domainModel.toString());

        Converter converter = new Converter();
        Model model = converter.convertToGenericModel(domainModel);
        logger.info(model.toString());

        // Telosys tools config
        //TelosysToolsCfg telosysToolsCfg = new TelosysToolsCfg(projectAbsolutePath);
        TelosysToolsCfg telosysToolsCfg = loadTelosysToolsCfg(projectAbsolutePath);

        // Generator config
        String sProjectLocation = projectAbsolutePath;
        GeneratorConfig generatorConfig = new GeneratorConfig(sProjectLocation, telosysToolsCfg, bundleName);

        // Generator runner
        try {
            GeneratorRunner generatorRunner = new GeneratorRunner(model, generatorConfig, logger);

            return generatorRunner;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Returns the TelosysToolsCfg instance loaded from the 'telosys-tools.cfg' using 'TelosysToolsCfgManager'
     * @return
     */
    private final TelosysToolsCfg loadTelosysToolsCfg(String projectAbsolutePath)  {
        try {
            System.out.println("Loading configuration from folder : " + projectAbsolutePath );
            TelosysToolsCfgManager cfgManager = new TelosysToolsCfgManager( projectAbsolutePath );
            TelosysToolsCfg telosysToolsCfg = cfgManager.loadProjectConfig();
            return telosysToolsCfg ;
        } catch (TelosysToolsException e) {
            throw new RuntimeException("Cannot load 'TelosysToolsCfg'", e);
        }
    }

}
