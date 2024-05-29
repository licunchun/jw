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
import Service.Data.Utils.UserUtil;
import Service.Data.Utils.*;


public class UserServ {
    private static final Students student = new Students();
    private static final Teachers teacher = new Teachers();
    private static final Managers manager = new Managers();
    /*
     * Editor
     */
    public static EditError editName(String ID, String name) {
        if(!IDUtil.check(ID))
            return EditError.IDNotFound;

        if (!NameUtil.check(name))
            return EditError.Invalid;

        UserUtil.setName(ID,name);
        return EditError.Success;
    }

    public static EditError editPassword(String ID, String password) {
        if(!IDUtil.check(ID))
            return EditError.IDNotFound;

        if (!PasswordUtil.check(password))
            return EditError.Invalid;

        UserUtil.setPassword(ID,password);
        return EditError.Success;
    }

    public static EditError editMoney(String ID, double money) {
        if(!IDUtil.check(ID))
            return EditError.IDNotFound;

        if(IDUtil.getUserType(ID)!= UserUtil.STUDENT)
            return EditError.Invalid;

        student.setMoney(ID,String.valueOf(money));
        return EditError.Success;
    }

    public static EditError editAddMoney(String ID, double addMoney) {
        if(!IDUtil.check(ID))
            return EditError.IDNotFound;

        if(IDUtil.getUserType(ID)!= UserUtil.STUDENT)
            return EditError.Invalid;

        double money = Double.parseDouble(student.getMoney(ID));
        student.setMoney(ID,String.valueOf(money+addMoney));
        return EditError.Success;
    }

    public static EditError editGrade(String ID, Grade grade) {
        if(!IDUtil.check(ID))
            return EditError.IDNotFound;

        if(IDUtil.getUserType(ID)!= UserUtil.STUDENT)
            return EditError.Invalid;

        if(grade==null)
            return EditError.Invalid;

        student.setGrade(ID,grade.toString());
        return EditError.Success;
    }

    public static EditError editSchool(String ID, School school) {
        if(!IDUtil.check(ID))
            return EditError.IDNotFound;

        if(IDUtil.getUserType(ID)!= UserUtil.STUDENT)
            return EditError.Invalid;

        if(school==null)
            return EditError.Invalid;

        student.setSchool(ID,school.toString());
        return EditError.Success;
    }

    /*
     * Getter
     */
    public static UserType getUserType(String ID) {
        return UserTypeTransformer.fromString(ID);
    }

    public static String getName(String ID) {
        if(!IDUtil.check(ID))
            return "";
        return UserUtil.getName(ID);
    }

    public static Gender getGender(String ID) {
        if(!IDUtil.check(ID))
            throw new RuntimeException("UserServ: ID have no Gender");
        if(IDUtil.getUserType(ID)!= UserUtil.STUDENT)
            throw new RuntimeException("UserServ: ID have no Gender");
        return Gender.fromString(student.getGender(ID));
    }

    public static School getSchool(String ID) {
        if(!IDUtil.check(ID))
            throw new RuntimeException("UserServ: ID have no School");
        if(IDUtil.getUserType(ID)== UserUtil.STUDENT)
            return School.fromString(student.getSchool(ID));
        if(IDUtil.getUserType(ID)== UserUtil.TEACHER)
            return School.fromString(teacher.getSchool(ID));
        throw new RuntimeException("UserServ: ID have no School");
    }

    public static Grade getGrade(String ID) {
        if(!IDUtil.check(ID))
            throw new RuntimeException("UserServ: ID have no Grade");
        if(IDUtil.getUserType(ID)!= UserUtil.STUDENT)
            throw new RuntimeException("UserServ: ID have no Grade");
        return Grade.fromString(student.getGrade(ID));
    }

    public static Double getMoney(String ID) {
        if(!IDUtil.check(ID))
            throw new RuntimeException("UserServ: ID have no Money");
        if(IDUtil.getUserType(ID)!= UserUtil.STUDENT)
            throw new RuntimeException("UserServ: ID have no Money");
        return Double.valueOf(student.getMoney(ID));
    }

    /*
     * Else
     */
    public static ChangePasswordError changePassword(String ID, String originPassword, String newPassword, String newConfirmPassword) {
        if(!IDUtil.check(ID))
            return ChangePasswordError.IDNotFound;
        if(!PasswordUtil.check(originPassword))
            return ChangePasswordError.WrongOriginPassword;
        String password_get = UserUtil.getPassword(ID);
        if(originPassword.compareTo(password_get) != 0)
            return ChangePasswordError.WrongOriginPassword;

        if(newPassword==null||newPassword.isEmpty())
            return ChangePasswordError.EmptyInput;
        if(newConfirmPassword==null||newConfirmPassword.isEmpty())
            return ChangePasswordError.EmptyInput;

        if(!PasswordUtil.checkLength(newPassword))
            return ChangePasswordError.OverLength;
        if(!PasswordUtil.checkChar(newPassword))
            return ChangePasswordError.InvalidChar;
        if(newPassword.compareTo(newConfirmPassword)!=0)
            return ChangePasswordError.NotMatch;

        UserUtil.setPassword(ID,newPassword);
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
        String[] sameNameTeacherID = Teachers.getSameNameID(name);
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
