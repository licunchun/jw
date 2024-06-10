package Service.Main.Components.UserServ;

import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.Enum.Error.Main.Components.UserServ.ChangePasswordError;
import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.User.UserType;
import Service.Data.Tables.Managers;
import Service.Data.Tables.Students;
import Service.Data.Tables.Teachers;
import Service.Data.Utils.*;

import java.util.Objects;

public class UserServ {
    private static UserUtil userUtil;
    private static Students students;
    private static PasswordUtil passwordUtil;

    /*
     * Editor
     */
    public static EditError editName(String ID, String name) {
        userUtil = new UserUtil(ID);
        if(userUtil.userType==UserUtil.INVALID)
            return EditError.IDNotFound;

        NameUtil nameUtil = new NameUtil(name);
        if (nameUtil.nameOverLength)
            return EditError.Invalid;

        userUtil.setName(name);
        return EditError.Success;
    }

    public static EditError editPassword(String ID, String password) {
        userUtil = new UserUtil(ID);
        if(userUtil.userType==UserUtil.INVALID)
            return EditError.IDNotFound;

        passwordUtil = new PasswordUtil(password);
        if (passwordUtil.passwordOverLength)
            return EditError.Invalid;

        userUtil.setPassword(password);
        return EditError.Success;
    }

    public static EditError editMoney(String ID, double money) {
        students = new Students(ID);
        if(!students.IDExist)
            return EditError.IDNotFound;

        students.setMoney(String.valueOf(money));
        return EditError.Success;
    }

    public static EditError editAddMoney(String ID, double addMoney) {
        students = new Students(ID);
        if(!students.IDExist)
            return EditError.IDNotFound;


        double money = Double.parseDouble(students.money);
        students.setMoney(String.valueOf(money+addMoney));
        return EditError.Success;
    }

    public static EditError editGrade(String ID, Grade grade) {
        students = new Students(ID);
        if(!students.IDExist)
            return EditError.IDNotFound;

        if(grade==null)
            return EditError.Invalid;

        students.setGrade(grade.toString());
        return EditError.Success;
    }

    public static EditError editSchool(String ID, School school) {
        userUtil = new UserUtil(ID);
        if(userUtil.userType==UserUtil.INVALID||userUtil.userType==UserUtil.MANAGER)
            return EditError.IDNotFound;

        if(school==null)
            return EditError.Invalid;

        userUtil.setSchool(school.toString());
        return EditError.Success;
    }

    /*
     * Getter
     */
    public static UserType getUserType(String ID) {
        return UserTypeTransformer.fromString(ID);
    }

    public static String getName(String ID) {
        userUtil = new UserUtil(ID);
        return userUtil.name;
    }

    public static Gender getGender(String ID) {
        students = new Students(ID);
        if(!students.IDExist)
            throw new RuntimeException("UserServ: ID have no Gender");
        return Gender.fromString(students.gender);
    }

    public static School getSchool(String ID) {
        userUtil = new UserUtil(ID);
            return School.fromString(userUtil.school);
    }

    public static Grade getGrade(String ID) {
        students = new Students(ID);
        if(!students.IDExist)
            throw new RuntimeException("UserServ: ID have no Grade");
        return Grade.fromString(students.grade);
    }

    public static Double getMoney(String ID) {
        students = new Students(ID);
        if(!students.IDExist)
            throw new RuntimeException("UserServ: ID have no Money");
        return Double.valueOf(students.money);
    }

    /*
     * Else
     */
    public static ChangePasswordError changePassword(String ID, String originPassword, String newPassword, String newConfirmPassword) {
        userUtil = new UserUtil(ID);
        if(userUtil.userType==UserUtil.INVALID)
            return ChangePasswordError.IDNotFound;
        if(Objects.equals(userUtil.password, originPassword))
            return ChangePasswordError.WrongOriginPassword;

        passwordUtil = new PasswordUtil(newPassword);
        if(passwordUtil.passwordEmpty)
            return ChangePasswordError.EmptyInput;


        if(passwordUtil.passwordCharValid)
            return ChangePasswordError.InvalidChar;
        if(passwordUtil.passwordOverLength)
            return ChangePasswordError.OverLength;
        if(newPassword.compareTo(newConfirmPassword)!=0)
            return ChangePasswordError.NotMatch;

        userUtil.setPassword(newPassword);
        return ChangePasswordError.Success;
    }

    public static IDSet findStudent(String name) {
        IDSet idSet = new IDSet();
        String[] sameNameStudentID = Students.getSameNameID(name);
        for (String ID : sameNameStudentID) {
            idSet.add(ID);
        }
        return idSet;
    }

    public static IDSet findTeacher(String name) {
        IDSet idSet = new IDSet();
        String[] sameNameTeacherID = Teachers.getIDWithSubName(name);
        for (String ID : sameNameTeacherID) {
            idSet.add(ID);
        }
        return idSet;
    }

    public static IDSet findAdmin(String name) {
        IDSet idSet = new IDSet();
        String[] sameNameManagerID = Managers.getSameNameID(name);
        for (String ID : sameNameManagerID) {
            idSet.add(ID);
        }
        return idSet;
    }

    public static IDSet findUser(UserType userType, String ID, String Name) {
        IDSet idSet = new IDSet();
        String[] studentsID,teachersID,managersID;
        if((Name==null||Name.isEmpty())&&(ID==null||ID.isEmpty())){
            studentsID = Students.getAllID();
            teachersID = Teachers.getAllID();
            managersID = Managers.getAllID();
            packed(userType,idSet,studentsID,teachersID,managersID);
            return idSet;
        }
        if (Name==null||Name.isEmpty()) {
            studentsID = Students.getIDWithSubID(ID);
            teachersID = Teachers.getIDWithSubID(ID);
            managersID = Managers.getIDWithSubID(ID);
            packed(userType,idSet,studentsID,teachersID,managersID);
            return idSet;
        }
        if(ID==null||ID.isEmpty()){
            studentsID = Students.getIDWithSubName(Name);
            teachersID = Teachers.getIDWithSubName(Name);
            managersID = Managers.getIDWithSubName(Name);
            packed(userType,idSet,studentsID,teachersID,managersID);
            return idSet;
        }
        {
            studentsID = Students.getIDWithString(ID,Name);
            teachersID = Teachers.getIDWithString(ID,Name);
            managersID = Managers.getIDWithString(ID,Name);
            packed(userType,idSet,studentsID,teachersID,managersID);
            return idSet;

        }
    }
    private static void packed(UserType userType,IDSet idSet,String[] studentsID,String[] teachersID,String[] managersID){
        if(userType==null){
            for (String id : studentsID) {
                idSet.add(id);
            }
            for (String id : teachersID) {
                idSet.add(id);
            }
            for (String id : managersID) {
                idSet.add(id);
            }
        } else if(userType==UserType.Student){
            for (String id : studentsID) {
                idSet.add(id);
            }
        }else if(userType==UserType.Teacher){
            for (String id : teachersID) {
                idSet.add(id);
            }
        }else if(userType==UserType.Admin){
            for (String id : managersID) {
                idSet.add(id);
            }
        }else{
            throw new RuntimeException("UserServ");
        }
    }


    public static boolean isIDExist(String ID) {
        return IDUtil.isIDExist(ID);
    }
}
