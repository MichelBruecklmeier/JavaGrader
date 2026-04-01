package utils;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.util.ArrayList;
import java.util.List;

//The purpose of this program is to attach a diagnostic listenrer to compiled programs in order
//to capture diagnostic output when running
public class DiagnosticListener implements javax.tools.DiagnosticListener<JavaFileObject> {
    //Hold all the diagnostics and its history
    List<Diagnostic<? extends JavaFileObject>> diagnostics = new ArrayList<>();
    //Check if theres a new diagnostics event
    private boolean isNew = false;
    //When diagnostic events happen this will update
    @Override
    public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
        diagnostics.add(diagnostic);
        isNew = true;
    }
    //Check if theres a new diagnostic event
    public boolean checkNew(){
        //Base check if its false
        if(!isNew) return false;
        //If isNew is true we have to set it to false now
        isNew = false;
        return true;
    }
    //Get lattest diagnostic
    public Diagnostic<? extends JavaFileObject> getLatest(){
        return diagnostics.getLast();
    }
}
