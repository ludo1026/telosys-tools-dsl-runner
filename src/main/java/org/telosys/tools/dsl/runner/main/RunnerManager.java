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

import org.telosys.tools.commons.TelosysToolsException;
import org.telosys.tools.commons.TelosysToolsLogger;
import org.telosys.tools.commons.cfg.TelosysToolsCfg;
import org.telosys.tools.commons.cfg.TelosysToolsCfgManager;
import org.telosys.tools.dsl.generic.converter.Converter;
import org.telosys.tools.dsl.parser.DomainModelParser;
import org.telosys.tools.dsl.parser.model.DomainModel;
import org.telosys.tools.dsl.runner.main.config.TelosysConfigReader;
import org.telosys.tools.dsl.runner.main.config.bean.TelosysConfig;
import org.telosys.tools.generator.Generator;
import org.telosys.tools.generator.api.GeneratorRunner;
import org.telosys.tools.generator.config.GeneratorConfig;
import org.telosys.tools.generic.model.Model;

import java.io.File;

public class RunnerManager {

    static TelosysToolsLogger logger = new TelosysToolsLogger() {
        @Override
        public void log(Object object, String s) {
            //System.out.println("log : "+((object==null)?"":object.toString())+" : "+s);
        }

        @Override
        public void log(String s) {
            //System.out.println("log : "+s);
        }

        @Override
        public void error(String s) {
            System.out.println("error : "+s);
        }

        @Override
        public void info(String s) {
            if(s.indexOf("info : Saving target file : ")>0) {
                System.out.println("info : " + s);
            }
        }

        @Override
        public void exception(Throwable exception) {
            System.out.println("exception : "+exception);
        }
    };

    /**
     * Return the generator
     * @param telosysConfig Configuration
     * @return Generator
     */
    public Runner getRunner(TelosysConfig telosysConfig) {
        try {
            String projectAbsolutePath = telosysConfig.config.rootFolder;
            String dslFolder = telosysConfig.config.dslFolder;
            String bundleName = telosysConfig.config.bundleName;

            // call parser tool
            DomainModelParser domainModelParser = new DomainModelParser();
            logger.info("\nParse folder : "+dslFolder);
            DomainModel domainModel = domainModelParser.parse(new File(projectAbsolutePath+"/"+dslFolder));
            logger.info("\n"+domainModel.toString());

            Converter converter = new Converter();
            Model model = converter.convertToGenericModel(domainModel);
            logger.info(model.toString());

            // Telosys tools config
            TelosysToolsCfg telosysToolsCfg = loadTelosysToolsCfg(projectAbsolutePath);

            // Generator config
            String sProjectLocation = projectAbsolutePath;
            GeneratorConfig generatorConfig = new GeneratorConfig(sProjectLocation, telosysToolsCfg, bundleName);

            // Generator runner
            GeneratorRunner generatorRunner = new GeneratorRunner(model, generatorConfig, logger);

            Runner runner = new Runner(telosysConfig, generatorRunner);

            return runner;
        } catch(Exception e) {
            logger.exception(e);
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
