package sub;

import java.util.List;

//The point of the subject class is to make a wrapper for each 'student' or
//participant that is getting their program tested, it allows us to store information
//like the name of the person getting tested and there grade.
public class Subject {
    //Name of person
    private String name;
    //Programs that are in project
    private Program program;
    //Points to allocate
    private double points;
    //Max points (its shared)
    private static double MAX_POINTS;
    //Shared ID
    private static int SHARED_ID;
    //current id
    private int ID = SHARED_ID++;
    //sub.Program object
    private Object instance;

    public Subject(String name,Program program){
        this.name = name;
        this.program = program;
    }
    public Subject(Program program){
        this(program.getName(),program);
    }
    public void StartInstance(){

    }
    //Add and remove points
    public void removePoints(double points){
        this.points -= points;
    }
    public void addPoints(double points){
        this.points += points;
    }
    //Getter
    public Program getProgram(){
        return program;
    }
    public static void setMaxPoints(double maxPoints){
        Subject.MAX_POINTS = maxPoints;
    }

    public String toString(){
        return "Name: "+name+" Grade: "+(points/MAX_POINTS) + " program: "+program.toString();
    }
}
