package Service.Main.Components.UserServ;

import Service.Data.DataBase;
import GUI.Data.DataPackage.Classes.IDSet;
import GUI.Data.Enum.Error.Main.Components.UserServ.ChangePasswordError;
import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.User.UserType;
import Service.Utils.*;


public class UserServ {
    /*
     * Editor
     */
    public static EditError editName(String ID, String name) {
        if(!IDManager.check(ID))
            return EditError.IDNotFound;

        if (!NameManager.check(name))
            return EditError.Invalid;

        DataBase.setUserName(ID,name);
        return EditError.Success;
    }

    public static EditError editPassword(String ID, String password) {
        if(!IDManager.check(ID))
            return EditError.IDNotFound;

        if (!PasswordManager.check(password))
            return EditError.Invalid;

        DataBase.setUserPassword(ID,password);
        return EditError.Success;
    }

    public static EditError editMoney(String ID, double money) {
        if(!IDManager.check(ID))
            return EditError.IDNotFound;

        if(IDManager.getUserType(ID)!=DataBase.STUDENT)
            return EditError.Invalid;

        DataBase.setStudentMoney(ID,money);
        return EditError.Success;
    }

    public static EditError editAddMoney(String ID, double addMoney) {
        if(!IDManager.check(ID))
            return EditError.IDNotFound;

        if(IDManager.getUserType(ID)!=DataBase.STUDENT)
            return EditError.Invalid;
        String[] studentInfo = DataBase.getUserInfo(ID);
        double money = Double.parseDouble(studentInfo[DataBase.MONEY]);
        DataBase.setStudentMoney(ID,money+addMoney);
        return EditError.Success;
    }

    public static EditError editGrade(String ID, Grade grade) {
        if(!IDManager.check(ID))
            return EditError.IDNotFound;

        if(IDManager.getUserType(ID)!=DataBase.STUDENT)
            return EditError.Invalid;

        if(grade==null)
            return EditError.Invalid;

        DataBase.setStudentGrade(ID,grade.toString());
        return EditError.Success;
    }

    public static EditError editSchool(String ID, School school) {
        if(!IDManager.check(ID))
            return EditError.IDNotFound;

        if(IDManager.getUserType(ID)!=DataBase.STUDENT)
            return EditError.Invalid;

        if(school==null)
            return EditError.Invalid;

        DataBase.setStudentSchool(ID,school.toString());
        return EditError.Success;
    }

    /*
     * Getter
     */
    public static UserType getUserType(String ID) {
        return UserTypeTransformer.fromString(ID);
    }

    public static String getName(String ID) {
        if(!IDManager.check(ID))
            return "";
        String[] studentInfo = DataBase.getUserInfo(ID);
        return studentInfo[DataBase.NAME];
    }

    public static Gender getGender(String ID) {
        if(!IDManager.check(ID))
            throw new RuntimeException("UserServ: ID have no Gender");
        if(IDManager.getUserType(ID)!=DataBase.STUDENT)
            throw new RuntimeException("UserServ: ID have no Gender");
        String[] studentInfo = DataBase.getUserInfo(ID);
        return Gender.fromString(studentInfo[DataBase.GENDER]);
    }

    public static School getSchool(String ID) {
        if(!IDManager.check(ID))
            throw new RuntimeException("UserServ: ID have no School");
        if(IDManager.getUserType(ID)!=DataBase.STUDENT)
            throw new RuntimeException("UserServ: ID have no School");
        String[] studentInfo = DataBase.getUserInfo(ID);
        return School.fromString(studentInfo[DataBase.SCHOOL]);
    }

    public static Grade getGrade(String ID) {
        if(!IDManager.check(ID))
            throw new RuntimeException("UserServ: ID have no Grade");
        if(IDManager.getUserType(ID)!=DataBase.STUDENT)
            throw new RuntimeException("UserServ: ID have no Grade");
        String[] studentInfo = DataBase.getUserInfo(ID);
        return Grade.fromString(studentInfo[DataBase.GRADE]);
    }

    public static Double getMoney(String ID) {
        if(!IDManager.check(ID))
            throw new RuntimeException("UserServ: ID have no Money");
        if(IDManager.getUserType(ID)!=DataBase.STUDENT)
            throw new RuntimeException("UserServ: ID have no Money");
        String[] studentInfo = DataBase.getUserInfo(ID);
        return Double.valueOf(studentInfo[DataBase.MONEY]);
    }

    /*
     * Else
     */
    public static ChangePasswordError changePassword(String ID, String originPassword, String newPassword, String newConfirmPassword) {
        if(!IDManager.check(ID))
            return ChangePasswordError.IDNotFound;
        if(!PasswordManager.check(originPassword))
            return ChangePasswordError.WrongOriginPassword;
        String password_get = DataBase.getUserPassword(ID);
        if(originPassword.compareTo(password_get) != 0)
            return ChangePasswordError.WrongOriginPassword;

        if(newPassword==null||newPassword.isEmpty())
            return ChangePasswordError.EmptyInput;
        if(newConfirmPassword==null||newConfirmPassword.isEmpty())
            return ChangePasswordError.EmptyInput;

        if(!PasswordManager.checkLength(newPassword))
            return ChangePasswordError.OverLength;
        if(!PasswordManager.checkChar(newPassword))
            return ChangePasswordError.InvalidChar;
        if(newPassword.compareTo(newConfirmPassword)!=0)
            return ChangePasswordError.NotMatch;

        DataBase.setUserPassword(ID,newPassword);
        return ChangePasswordError.Success;
    }

    public static IDSet findStudent(String name) {

        IDSet idSet = new IDSet();
        String[] sameNameStudentID = DataBase.getSameNameID("students",name);
        for (String ID : sameNameStudentID) {
            idSet.add(ID);
        }
        return idSet;
    }

    public static IDSet findTeacher(String name) {
        IDSet idSet = new IDSet();
        String[] sameNameTeacherID = DataBase.getSameNameID("teachers",name);
        for (String ID : sameNameTeacherID) {
            idSet.add(ID);
        }
        return idSet;
    }

    public static IDSet findAdmin(String name) {
        IDSet idSet = new IDSet();
        String[] sameNameManagerID = DataBase.getSameNameID("managers",name);
        for (String ID : sameNameManagerID) {
            idSet.add(ID);
        }
        return idSet;
    }

    public static IDSet findUser(UserType userType, String ID, String Name) {
        IDSet idSet = new IDSet();
        String[] sameNameIDStudent = DataBase.getSameNameID("students",ID,Name);
        String[] sameNameIDTeacher = DataBase.getSameNameID("teachers",ID,Name);
        String[] sameNameIDManager = DataBase.getSameNameID("managers",ID,Name);
        if(userType==null){
            for (String id : sameNameIDStudent) {
                idSet.add(id);
            }
            for (String id : sameNameIDTeacher) {
                idSet.add(id);
            }
            for (String id : sameNameIDManager) {
                idSet.add(id);
            }
        }
        if(userType==UserType.Student){
            for (String id : sameNameIDStudent) {
                idSet.add(id);
            }
        }
        if(userType==UserType.Teacher){
            for (String id : sameNameIDTeacher) {
                idSet.add(id);
            }
        }
        if(userType==UserType.Admin){
            for (String id : sameNameIDManager) {
                idSet.add(id);
            }
        }
        return idSet;
    }

    public static boolean isIDExist(String ID) {
        return IDManager.isIDExist(ID);
    }
}
