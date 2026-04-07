package sub;

import utils.DiagnosticListener;
import utils.OutputListener;

import java.util.ArrayList;
import java.util.List;

//This hold the object instance and any other information about a program that is vital
public class Program {
    //Basic info
    private String programmerName;
    private List<String> sourcePaths;
    private List<String> compiledPaths;
    //If a fatal error happens we cannot keep calling methods
    private boolean fatalError = false;
    //Hold the class of the program
    private Class<?> programClass;
    //Current working directory of the program
    private String cwd;
    //The diagnostics listener for the program
    private DiagnosticListener diagnostic;
    //Output of the program
    private OutputListener output;

    //The constructor/constructors

    //This will be used by program compiler to intilize instances of this
    public Program(String programmerName, List<String> sourcePaths, String cwd, DiagnosticListener diagnosticListener, OutputListener outputListener){
        this.programmerName = programmerName;
        this.sourcePaths = sourcePaths;
        this.cwd = cwd;
        this.diagnostic = diagnosticListener;
        this.output = outputListener;
        this.compiledPaths = new ArrayList<>();

    }
    //Create a new diagnosticListener and outputListener by default
    public Program(String programmerName, List<String> sourcePaths, String cwd){
        this(programmerName,sourcePaths,cwd,new DiagnosticListener(), new OutputListener());
    }
    //Start instance with specific constructor
    public boolean invoke(){

        return false;
    }
    public DiagnosticListener getDiagnosticListener(){
        return diagnostic;
    }
    public OutputListener getOutputListener(){
        return output;
    }
}
