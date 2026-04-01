package utils;//The program will compile the programs into a .class file to be used

import sub.Program;

import javax.tools.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ProgramCompiler {
    //Curent working directory
    private final String CWD = System.getProperty("user.dir");
    public ProgramCompiler()  {

    }
    //IMPORTANT: this will compile the programs attach listeners and store valuable
    //info in the program object to be used then
    public List<Program> compileToProgramObj(List<String> sourceFilePaths){
        //Final list of program objects
        List<Program> programs = new ArrayList<>();
        //Get the java compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if(compiler == null)
            throw new IllegalStateException("Failed to get java compiler");

        //Try to get file manager
        try(StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null)){

        } catch(IOException e){
            //WOW such amazing programing here
            throw new IllegalStateException(e.getMessage());
        }
        return programs;
    }

    //TODO: reevaluate the importance of all this code below (probably delete)
    //This will compile .java files to .class files
    //NOTE: Will only compile to .class file wont return the instance
    public List<String> compile(List<String> sourceFilePaths,List<String> dirName){
        //Initlize the java compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //Hold where we compiled the files to
        List<String> compiledFilesDirectories = new ArrayList<>();
        //Check if it works
        if(compiler == null){
            throw new IllegalStateException("Failed to get the compiler");
        }
        //Try to get the file manager to bridge the gap tp file system
        try(StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null)){
            for(int i = 0; i < sourceFilePaths.size(); i++) {
                String sourceFilePath = sourceFilePaths.get(i);
                File file = new File(sourceFilePath);
                //Basically a wrapper for the File object as the compiler needs more data
                Iterable<? extends JavaFileObject> compilationUnits =
                        fileManager.getJavaFileObjectsFromFiles(List.of(file));

                String dirOptions = "Programs/";
                //Check if we have a dir name
                //Programs/{student_name}/...
                if(dirName.get(i) != null) {
                    //Concat if we do
                    dirOptions = dirOptions + dirName.get(i)+"/";
                }
                //Attempt to make new directory in programs under the name for the java file
                if(!makeProgramsDir(dirName.get(i)))
                    throw new IllegalStateException("Failed to create dir for @ "+dirName);
                Iterable<String> compilerOptions = Arrays.asList("-d", dirOptions);

                //Actually call the compiler now and compile the file
                JavaCompiler.CompilationTask task = compiler.getTask(
                        null, //TODO: change this to capture output
                        fileManager,
                        null,
                        compilerOptions,
                        null, //TODO: Make advance
                        compilationUnits
                );

                //If this is all successful we can add it to compilation list
                if(task.call())
                    compiledFilesDirectories.add(CWD+File.separator+compilerOptions);
            }
            //Return a list of compiled directories and see which ones failed



        } catch (IOException e){
            throw new IllegalStateException("Failed to get the file");
        }
        return compiledFilesDirectories;
    }
    //This is the autodetect function
    public HashMap<String, String> compileWithNames(List<String> sourceFilePaths){
        //Final HashMap
        HashMap<String,String> namesToDirs = new HashMap<>();
        List<String> dirName = new ArrayList<>();
        //Number of unnamed people
        int unnamed = 0;
        //Now scan files for the //Name: then attempt to read name
        for(String sourcePath:sourceFilePaths){
            String name = findCommentName(sourcePath);
            //Check if it found a name
            if(name == null){
                //If there are no other unnamed files we go
                name = "unnamed_"+unnamed;
                unnamed++;
            }
            //Add to list
            dirName.add(name);
        }
        //Compile the actual files and get a list
        List<String> compiledPaths = compile(sourceFilePaths,dirName);
        //Check if size matches
        if(compiledPaths.size() != dirName.size()){
            throw new IllegalStateException("Compiled path and Directory Names miss match");
        }
        //Copy to hashmap
        for(int i = 0; i<dirName.size(); i++){
            namesToDirs.put(dirName.get(i),compiledPaths.get(i));
        }
        return namesToDirs;
    }
    //Compile so the name links with the set
    public HashMap<String, String> compileWithNames(List<String> sourceFilePaths, List<String> dirName) {
        //HashMap to return
        HashMap<String, String> namesToDir = new HashMap<>();
        List<String> compiledList = compile(sourceFilePaths,dirName);
        //Now we combine it
        for(int i = 0; i<compiledList.size(); i++){
            //Map
            namesToDir.put(dirName.get(i),compiledList.get(i));
        }
        return namesToDir;
    }
    //Helper method to find the name in the comment with conventional style //Name: {Name}
    private String findCommentName(String filePath){
        //Name in the java file
        String name = null;
        try {
            Scanner fileReader = new Scanner(new File(filePath));

            //Read every line
            while(fileReader.hasNext()){
                String line = fileReader.nextLine();
                if(line.matches(".*[Nn]ame:.*")){
                    //Attempt to access name from comment
                    try {
                        //Regex to cut up name comment
                        name = line.split(":")[1];
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                        System.err.println("No name was found at comment //Name: "+filePath);
                    }
                    break;
                }
            }
            //Make sure to close it
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Failed to get file @ " + filePath);
        }
        return name;
    }
    //Helper function for compiler to make directories nescesary
    private boolean makeProgramsDir(String dir){
        File folder = new File(CWD + File.separator + "Programs" + File.separator + dir);
        //Important base check to make sure were not making these dirs in a random spot
        //we check this by seeing if JavaGrader is immiditly followed by programs folder
        if((CWD+dir).matches("JavaGrader(\\|/)Programs"))
            throw new IllegalStateException("Failed to validate matching working directory");
        return folder.mkdir();
    }

    //Overloaded method to make name optional
    public List<String> compile(List<String> sourceFilePaths){
        return compile(sourceFilePaths,null);
    }

}
