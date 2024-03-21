package Sevice;

import Data.DataBase;
import Data.Enum.*;
import Interface.IGetData;
import Interface.IStoreData;
import Interface.IUserSevice;

public class UserService implements IUserSevice, IStoreData, IGetData {
    /*
     * 属性
     */
    final static int MAX_NUM_PASSWORD = 10;
    private User user;
    private Grade grade;
    private School school;
    private Gender gender;
    private String password_temp;
    /*
     * 构造
     */

    //空参构造
    public UserService() {
    }
    //学生构造
    public UserService(UserType userType, String name, String ID, String password, Grade grade, School school, Gender gender) {
        this.user = new User(userType,name,ID,password);
        this.grade = grade;
        this.school = school;
        this.gender = gender;
    }
    //教师构造
    public UserService(UserType userType, School school, Gender gender, String name, String ID, String password) {
        this.user = new User(userType,name,ID,password);
        this.school = school;
        this.gender = gender;
    }
    //管理员构造
    public UserService(UserType userType, String name, String ID, String password) {
        this.user = new User(userType,name,ID,password);
    }
    /*
     * Getter&Setter
     */

    public UserType getUserType() {
        return this.user.getUserType();
    }
    public void setUserType(UserType userType) {
        this.user.setUserType(userType);
    }
    public String getName() {
        return this.user.getName();
    }
    public void setName(String name) {
        this.user.setName(name);
    }
    public String getID() {
        return this.user.getID();
    }

    public String getPassword() {
        return this.user.getPassword();
    }
    public void setPassword(String password) {
        this.user.setPassword(password);
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
    public String getPassword_temp() {
        return password_temp;
    }

    public void setPassword_temp(String password_temp) {
        this.password_temp = password_temp;
    }
    /*
     * 功能主体
     */
    @Override
    public void Regist(){
        user.setID(getAvailableID(user.getUserType()));
        switch (user.getUserType()) {
            case None -> {
                System.out.println("用户不存在");
            }
            case Student -> {storeUser(user.getUserType(), user.getName(), user.getID(), user.getPassword(),grade,school,gender);
            }
            case Teacher -> {storeUser(user.getUserType(), user.getName(), user.getID(), user.getPassword(),school,gender);
            }
            case Admin -> {storeUser(user.getUserType(), user.getName(), user.getID(), user.getPassword());
            }
        };
    }
    @Override
    public boolean isNameValid(){

        return true;
    }
    @Override
    public boolean isPasswordValid(){

            return true;

    }
    /*

     */

    @Override
    public boolean checkIDAndPassword(){return true;}
    @Override
    public boolean exit(){return true;}





















    @Override
    public boolean storeUser(UserType userType, String name, String ID, String password, Grade grade, School school, Gender gender) {
        return false;
    }

    @Override
    public boolean storeUser(UserType userType, String name, String ID, String password, School school,Gender gender) {
        return false;
    }

    @Override
    public boolean storeUser(UserType userType, String name, String ID, String password) {
        return false;
    }

    @Override
    public String getAvailableID(UserType userType) {
        return null;
    }


}

