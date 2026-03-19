public class Main {
    public static final String CWD = System.getProperty("user.dir");
    public static void main(String[] args) {

        ProgramCompiler compiler = new ProgramCompiler();
        System.out.println(compiler.compile(CWD+"/TestPrograms/Person.java"));
    }
}