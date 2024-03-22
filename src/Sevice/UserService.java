package Sevice;

import Data.Enum.*;

import Utils.*;

public class UserService{
    /*
     * 属性
     */
    private UserType userType;
    private String name;
    private String ID;
    private String password;
    private Grade grade;
    private School school;
    private Gender gender;
    /*
     * 构造
     */

    //空参构造
    public UserService() {
    }

    //学生构造

    public UserService(UserType userType, String name, String ID, String password, Grade grade, School school, Gender gender) {
        this.userType = userType;
        this.name = name;
        this.ID = ID;
        this.password = password;
        this.grade = grade;
        this.school = school;
        this.gender = gender;
    }


    //教师构造

    public UserService(UserType userType, String name, String ID, String password, School school, Gender gender) {
        this.userType = userType;
        this.name = name;
        this.ID = ID;
        this.password = password;
        this.school = school;
        this.gender = gender;
    }

    //管理员构造

    public UserService(UserType userType, String name, String ID, String password) {
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

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /*
     * 功能主体
     */

    public void Regist(){
        setID(GetUtil.getAvailableID(this.userType));
        switch (getUserType()) {
            case None -> {exit();
            }
            case Student -> {
                StoreUtil.storeStudent(userType,name,ID,password,grade,school,gender);
            }
            case Teacher -> {
                StoreUtil.storeTeacher(userType,name,ID,password,school,gender);
            }
            case Admin -> {
                StoreUtil.storeAdmin(userType,name,ID,password);
            }
        };
    }

    public boolean isNameValid(){
        if (NameUtil.checkValid(name)==StringState.RIGHT)
            return true;
        else
            return false;
    }

    public boolean isPasswordValid(){
        if(PasswordUtil.checkValid(password)== StringState.RIGHT)
            return true;
        else
            return false;
    }
    public boolean checkIDAndPassword(){
        int index = IDUtil.isIDExist(ID);
        if(index == -1)
            return false;
        else if(password.compareTo(GetUtil.getPassword(this.userType,index))==0)
            return true;
        else
            return false;
    }


    public boolean exit(){return true;}



}

