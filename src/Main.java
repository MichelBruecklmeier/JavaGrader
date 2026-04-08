import sub.Program;
import sub.Subject;
import utils.ProgramCompiler;

import java.util.HashMap;
import java.util.List;

//This is a example of how the java tester would work
public class Main {
    public static final String CWD = System.getProperty("user.dir");
    public static void main(String[] args) {
        System.out.println(CWD);

        ProgramCompiler compiler = new ProgramCompiler();
        HashMap<String,List<String>> input = new HashMap<>();
        input.put("Quagmire", List.of(CWD+"\\TestPrograms\\Person.java"));
        List<Subject> subjects = compiler.compileToSubject(input);
        System.out.println(subjects);
    }
}