package Sevice;

import Data.DataBase;
import Data.Enum.Error.Login;
import Data.Enum.Error.Regist;
import Data.Enum.School;
import Data.Enum.User.*;
import JavaBean.User;
import Sevice.Utils.GetUtil;
import Sevice.Utils.StoreUtil;
import Sevice.Utils.NameUtil;
import Sevice.Utils.PasswordUtil;

public class UserService{
    /*
     * 属性
     */
    User user;
    private Grade grade;
    private School school;
    private Gender gender;
    private DataBase db = new DataBase();
    /*
     * 构造
     */

    //空参构造
    public UserService() {
        user = new User();
    }
    /*
     * Getter&Setter
     */
    public UserType getUserType() {
        return user.getUserType();
    }

    private void setUserType(UserType userType) {
        user.setUserType(userType);
    }

    public String getName() {
        return user.getName();
    }

    private void setName(String name) {
        user.setName(name);
    }

    public String getID() {
        return user.getID();
    }

    private void setID(String ID) {
        user.setID(ID);
    }

    public String getPassword() {
        return user.getPassword();
    }

    private void setPassword(String password) {
        user.setPassword(password);
    }

    public Grade getGrade() {
        return grade;
    }

    private void setGrade(Grade grade) {
        this.grade = grade;
    }

    public School getSchool() {
        return school;
    }

    private void setSchool(School school) {
        this.school = school;
    }

    public Gender getGender() {
        return gender;
    }

    private void setGender(Gender gender) {
        this.gender = gender;
    }

    /*
     * 功能主体
     */
    public Regist regist(UserType userType,String name,String password,String password_confirm)
    {
        Regist r = NameUtil.checkValid(name);
        if(r != Regist.Pass)
            return r;
        r = PasswordUtil.checkValid(password);
        if(r != Regist.Pass)
            return r;
        if(password.compareTo(password_confirm)==0)
        {
            user.setUserType(userType);
            user.setName(name);
            user.setPassword(password);
            return Regist.Pass;
        }
        else
            return Regist.PasswordNotMatch;
    }

    public String storeUser(Gender gender,School school,Grade grade)
    {
        String ID = GetUtil.getAvailableID(user.getUserType());
        user.setID(ID);
        switch (getUserType()) {
            case UserType.Student->{
                StoreUtil.storeStudent(user.getUserType(),user.getName(), user.getID(), user.getPassword(),school,gender,grade);
            }
            case UserType.Teacher->{
                StoreUtil.storeTeacher(user.getUserType(), user.getName(), user.getID(), user.getPassword(),school,gender);
            }
            case UserType.Admin->{
                StoreUtil.storeAdmin(user.getUserType(), user.getName(), user.getID(), user.getPassword());
            }
            default -> {
                throw new RuntimeException("UserService.java(line ) StoreUser have no userType");
            }
        }
        return ID;
    }


    public Login checkIDAndPassword(String ID,String password){
        if(ID.isEmpty())
            return Login.IDEmpty;
        if(password.isEmpty())
            return Login.PasswordEmpty;
        int index = 0;
        if(index == -1)
            return Login.NotPass;
        user = GetUtil.getUser(ID);
        if(password.compareTo(user.getPassword())==0)
        {
            switch (user.getUserType()){
                case Student -> {
                    return Login.Student;
                }
                case Teacher -> {
                    return Login.Teacher;
                }
                case Admin -> {
                    return Login.Admin;
                }
                default -> {
                    throw new RuntimeException("UserService.java(line ) StoreUser have no userType");
                }
            }
        }
        else
            return Login.NotPass;
    }


    public boolean exit(){return true;}



}

