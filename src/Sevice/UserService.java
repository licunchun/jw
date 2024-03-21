package Sevice;

import Data.DataBase;
import Data.Enum.*;

/*
public class UserService extends User{
    final static int STUDENT = 10;
    final static int TEACHER = 5;
    final static int MANAGER = 1;
    DataBase db;
    public int login(String ID_login,String password_login){
        String password;
        int permission = ID_login.length();
        switch (permission){
            case STUDENT:
                password = db.keyOfStudent(ID_login);
                return password.compareTo(password_login)==0?1:0;
            case TEACHER:
                password = db.keyOfTeacher(ID_login);
                return password.compareTo(password_login)==0?2:0;
            case MANAGER:
                password = db.keyOfManager(ID_login);
                return password.compareTo(password_login)==0?3:0;
            default:
                return 0;
        }
    }
}*/
public class UserService{
    /*
     * 属性
     */
    private UserType userType;
    private Grade grade;
    private School school;
    private Gender gender;
    private String name;
    private String ID;
    private String password;

    /*
     * 构造
     */

    //空参构造
    public UserService() {}
    //学生构造
    public UserService(UserType userType, Grade grade, School school, Gender gender, String name, String ID, String password) {
        if(userType!=UserType.Student)
            return;
        this.userType = userType;
        this.grade = grade;
        this.school = school;
        this.gender = gender;
        this.name = name;
        this.ID = ID;
        this.password = password;
    }
    //教师构造
    public UserService(UserType userType, School school, Gender gender, String name, String ID, String password) {
        if(userType!=UserType.Teacher)
            return;
        this.userType = userType;
        this.school = school;
        this.gender = gender;
        this.name = name;
        this.ID = ID;
        this.password = password;
    }
    //管理员构造
    public UserService(UserType userType, String name, String ID, String password) {
        if(userType!=UserType.Admin)
            return;
        this.userType = userType;
        this.name = name;
        this.ID = ID;
        this.password = password;
    }

    /*
     * Getter&Setter
     */

    public UserType getUserType() {
        return userType;
    }

    public Grade getGrade() {
        return grade;
    }

    public School getSchool() {
        return school;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * 功能主体
     */
    public void Regist(){}
    public boolean isNameValid(){return true;}
    public boolean isPasswordValid(){return true;}
    public boolean checkIDAndPassword(){return true;}
}

