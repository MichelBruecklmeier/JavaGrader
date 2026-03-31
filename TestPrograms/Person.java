//Simple java program to test reflection
//Name: Quagmire
public class Person{
    private int age;
    private String firstName;
    private String lastName;
    //Simple constructor
    public Person(int age, String firstName, String lastName){
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    //Testing constructor overloading
    public Person(int age){
        this(age,"Jhon","Doe");
    }
    public Person(){
        this(100);
    }
    //Getter for name
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getFullName(){
        return getFirstName() +" " + getLastName();
    }
    //Setters for name
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setAge(int age){
        this.age = age;
    }

}