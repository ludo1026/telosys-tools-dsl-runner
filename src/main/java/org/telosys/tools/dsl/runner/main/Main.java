package org.telosys.tools.dsl.runner.main;

public class Main {

    public static void main(String[] args) throws Exception {

        /*
        // check argument exist
        if (args == null) {
            throw new RuntimeException("No file given");
        }

        if (args.length != 1) {
            throw new RuntimeException("A single parameter is required");
        }

        String dslFolder = args[0];
*/
        //String dslFolder = "/Users/ludovicchaboud/Code/workspace_telosys/telosys-tools-dsl-runner/sample/TelosysTools/model";
        Application application = new Application();
        application.main();
    }

}
