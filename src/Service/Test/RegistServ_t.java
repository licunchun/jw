package Service.Test;

import GUI.Data.Enum.Error.Login.Regist;
import GUI.Data.Enum.School;
import GUI.Data.Enum.User.Gender;
import GUI.Data.Enum.User.Grade;
import GUI.Data.Enum.User.UserType;
import Service.Login.RegistServ;

public class RegistServ_t {
    public static void main(String[] args) {
        UserType[] userTypes = {UserType.Admin,UserType.Student,UserType.Teacher,UserType.Student,UserType.Teacher};
        String[] name = {"一","二","三","四","五"};
        String[] password = {"1","2","3","4","5"};
        String[] password_confirm = {"1","2","3","3","5"};
        Gender[] genders = {Gender.Female,Gender.Female,Gender.Female,Gender.Female,Gender.Female};
        School[] schools = {School.CyberScienceAndTechnology,School.ComputerScienceAndTechnology,School.ChemistryAndMaterialsScience,School.Marxism,School.ChemistryAndMaterialsScience};
        Grade[] grades = {Grade.Grade1,Grade.Grade3,Grade.Grade3,Grade.Grade1,Grade.Grade2};
        for (int i = 0; i < userTypes.length; i++) {
            Regist r = RegistServ.regist(userTypes[i],name[i],password[i],password_confirm[i]);
            System.out.println(r);
            if(r==Regist.Pass)
                System.out.println(RegistServ.store(userTypes[i],name[i],password[i],password_confirm[i],genders[i],schools[i],grades[i]));
        }
    }
}
