import utils.ProgramCompiler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//This is a example of how the java tester would work
public class Main {
    public static final String CWD = System.getProperty("user.dir");
    public static void main(String[] args) {

        ProgramCompiler compiler = new ProgramCompiler();
        HashMap<?,?> directories = compiler.compileWithNames(Arrays.asList(CWD+"/TestPrograms/Person.java"));
        System.out.println(directories);
    }
}