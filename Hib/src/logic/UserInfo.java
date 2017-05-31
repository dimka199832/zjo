package logic;

import javax.persistence.*;

/**
 * Created by dimal on 17.05.2017.
 */
@Entity
@Table(name = "UserInfo", uniqueConstraints = {
        @UniqueConstraint(columnNames = "FullName"),
        @UniqueConstraint(columnNames = "Age"),
        @UniqueConstraint(columnNames = "Gender")
})
public class UserInfo {
    private Long Id_UserInfo;
    private String FullName;
    private Integer Age;
    private String Gender;

    /*Constructors*/

    public UserInfo(){}

    /*Setters*/

    public void setId_UserInfo(Long Id_UserInfo){
        this.Id_UserInfo = Id_UserInfo;
    }

    public void setFullName(String FullName){
        this.FullName = FullName;
    }

    public void setAge(Integer Age){
        this.Age = Age;
    }

    public void setGender(String Gender){
        this.Gender = Gender;
    }

    /*Getters*/

    @Id
    @GeneratedValue
    @Column(name= "Id_UserInfo", unique=true)
    public Long getId_UserInfo(){
        return this.Id_UserInfo;
    }

    @Column(name = "FullName")
    public String getFullName(){
        return this.FullName;
    }

    @Column(name = "Age")
    public Integer getAge(){
        return this.Age;
    }

    @Column(name = "Gender")
    public String getGender(){
        return this.Gender;
    }
}
