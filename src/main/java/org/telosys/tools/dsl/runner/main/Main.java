package org.telosys.tools.dsl.runner.main;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.*;

public class Main {

    public static void main(String[] args) throws Exception {

        //String dslFolder = "/Users/ludovicchaboud/Code/workspace_telosys/telosys-tools-dsl-runner/sample/TelosysTools/model";
        Application application = new Application();

        application.main();

        final WatchService watcher = FileSystems.getDefault().newWatchService();
        String root = "/Users/ludovicchaboud/Code/workspace_telosys/telosys-tools-dsl-runner/sample";
        Path start = Paths.get(root+"/TelosysTools");
        try {
            Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                        throws IOException
                {
                    if(dir.endsWith(".git")) {
                        return FileVisitResult.TERMINATE;
                    }
                    System.out.println("Watch: "+dir);
                    WatchKey key = dir.register(watcher,
                            ENTRY_CREATE,
                            ENTRY_DELETE,
                            ENTRY_MODIFY);
                    return FileVisitResult.CONTINUE;
                }
            });

        } catch (IOException x) {
            System.err.println(x);
        }

        for(;;) {
            // wait for key to be signaled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                // This key is registered only
                // for ENTRY_CREATE events,
                // but an OVERFLOW event can
                // occur regardless if events
                // are lost or discarded.
                if (kind == OVERFLOW) {
                    continue;
                }

                // The filename is the
                // context of the event.
                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();
                System.out.println("File changed : "+filename);

                application.main();
            }

            // Reset the key -- this step is critical if you want to
            // receive further watch events.  If the key is no longer valid,
            // the directory is inaccessible so exit the loop.
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }

}
