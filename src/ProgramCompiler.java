//The program will compile the programs into a .class file to be used

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ProgramCompiler {

    public ProgramCompiler()  {

    }
    public static boolean compile(String sourceFilePath){
        //Initlize the java compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //Check if it works
        if(compiler == null){
            throw new IllegalStateException("Failed to get the compiler");
        }
        //Try to get the file manager to bridge the gap
        try(StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null)){
            File file = new File(sourceFilePath);
            //Bassically a wrapper for the File object as the compiler needs more data
            Iterable<? extends JavaFileObject> compilationUnits =
                    fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file));

            Iterable<String> compilerOptions = Arrays.asList("-d","Programs/");

            //Actually call the compiler now
            JavaCompiler.CompilationTask task = compiler.getTask(
                    null, //TODO: change this to capture output
                    fileManager,
                    null,
                    compilerOptions,
                    null,
                    compilationUnits
            );
            return task.call(); //If it compiles we get a true

        } catch (IOException e){
            throw new IllegalStateException("Failed to get the file");
        }
    }

}
