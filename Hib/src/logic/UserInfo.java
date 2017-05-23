package logic;

/**
 * Created by dimal on 17.05.2017.
 */
public class UserInfo {
    private long Id;
    private String FullName;
    private int Age;
    private String Gender;
    private User Person;

    public UserInfo(){}

    //Setters
    public void setId(long Id){
        this.Id = Id;
    }

    public void setFullName(String FullName){
        this.FullName = FullName;
    }

    public void setAge(int Age){
        this.Age = Age;
    }

    public void setGender(String Gender){
        this.Gender = Gender;
    }

    public void setPerson(User Person){
        this.Person = Person;
    }

    //Getters
    public long getId(){
        return this.Id;
    }

    public String getFullName(){
        return this.FullName;
    }

    public int getAge(){
        return this.Age;
    }

    public String getGender(){
        return this.Gender;
    }

    public User getPerson(){
        return this.Person;
    }
}
