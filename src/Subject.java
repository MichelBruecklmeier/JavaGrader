//The point of the subject class is to make a wrapper for each 'student' or
//participant that is getting they're program tested, it allows us to store information
//like the name of the person getting tested and there grade.
public class Subject {
    //Name of person
    String name;
    //Directory of program
    String directory;
    //Points to allocate
    private double points;
    //Max points (its shared)
    private static double MAX_POINTS;
    //Shared ID
    private static int SHARED_ID;
    //Program object
    private Object instance;

    public Subject(String name, String directory){
        this.name = name;
        this.directory = directory;
    }
    public void StartInstance(){

    }
}
