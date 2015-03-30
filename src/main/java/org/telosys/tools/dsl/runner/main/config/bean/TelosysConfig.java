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
package org.telosys.tools.dsl.runner.main.config.bean;

import org.telosys.tools.commons.variables.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelosysConfig {

    public TelosysConfigConfig config;
    public List<Variable> variables = new ArrayList<Variable>();
    public Map<String, TelosysConfigTemplate> templates = new HashMap<String, TelosysConfigTemplate>();
    public List<TelosysConfigGeneration> generations = new ArrayList<TelosysConfigGeneration>();

}
