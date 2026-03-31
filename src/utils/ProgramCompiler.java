package utils;//The program will compile the programs into a .class file to be used

import javax.tools.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ProgramCompiler {

    public ProgramCompiler()  {

    }
    //This will compile .java files to .class files
    //NOTE: Will only compile to .class file wont return the instance
    public List<String> compile(List<String> sourceFilePaths,List<String> dirName){
        //Initlize the java compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //Hold where we compiled the files to
        List<String> compiledFilesDirectories = new ArrayList<String>();
        //Check if it works
        if(compiler == null){
            throw new IllegalStateException("Failed to get the compiler");
        }
        //Try to get the file manager to bridge the gap
        try(StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null)){
            for(String sourceFilePath:sourceFilePaths) {
                File file = new File(sourceFilePath);
                //Bassically a wrapper for the File object as the compiler needs more data
                Iterable<? extends JavaFileObject> compilationUnits =
                        fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file));

                String dirOptions = "Programs/";
                //Check if we have a dir name
                if(dirName != null) {
                    //Concat if we do
                    dirOptions = dirOptions + dirName+"/";
                }
                Iterable<String> compilerOptions = Arrays.asList("-d", dirOptions);

                //Actually call the compiler now
                JavaCompiler.CompilationTask task = compiler.getTask(
                        null, //TODO: change this to capture output
                        fileManager,
                        null,
                        compilerOptions,
                        null, //TODO: Make advance
                        compilationUnits
                );
            }
            //Return a list of compiled directories and see which ones failed
            return compiledFilesDirectories;


        } catch (IOException e){
            throw new IllegalStateException("Failed to get the file");
        }
    }
    public HashMap<String, String> compileWithNames(List<String> sourceFilePaths){
        //Final HashMap
        HashMap<String,String> namesToDirs = new HashMap<>();
        List<String> dirName = new ArrayList<>();
        //Now scan files for the //Name: then attempt to read name
        for(String sourcePath:sourceFilePaths){
            try {
                Scanner fileReader = new Scanner(new File(sourcePath));
                //Read every line
                while(fileReader.hasNext()){
                    String line = fileReader.nextLine();
                    if(line.matches("[Nn]ame:$")){
                        String name;
                        //Attempt to access name from comment
                        try {
                            //Regex to cut up name comment
                            name = line.split("[Nn]name:")[1];
                        } catch(ArrayIndexOutOfBoundsException e){
                            System.err.println("No name was found at comment //Name: ");
                        }
                        break;
                    }
                }
                //Make sure to close it
                fileReader.close();

            } catch (FileNotFoundException e) {
                System.err.println("Failed to get file @ " + sourcePath);
            }
        }
        //Compile the actual files and get a list
        List<?> compiledPaths = compile(sourceFilePaths,dirName);

        return namesToDirs;
    }
    //Compile so the name links with the set
    public HashMap<String, String> compileWithNames(List<String> sourceFilePaths, List<String> dirName) {
        //HashMap to return
        HashMap<String, String> namesToDir = new HashMap<>();
        List<String> compiledList = compile(sourceFilePaths,dirName);

        return namesToDir;
    }
    //Overloaded method to make name optional
    public List<String> compile(List<String> sourceFilePaths){
        return compile(sourceFilePaths,null);
    }

}
