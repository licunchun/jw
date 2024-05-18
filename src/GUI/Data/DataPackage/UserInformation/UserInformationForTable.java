package GUI.Data.DataPackage.UserInformation;

import GUI.Data.Enum.User.UserType;
import javafx.beans.property.SimpleStringProperty;

public class UserInformationForTable {
    private final SimpleStringProperty ID;
    private final SimpleStringProperty name;
    private final SimpleStringProperty password;
    private final SimpleStringProperty grade;
    private final SimpleStringProperty school;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty money;

    public UserInformationForTable(String ID) {
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(Service.Main.Components.UserServ.UserServ.getName(ID));
        this.password = new SimpleStringProperty("修改密码");
        //不会真的有人直接传密码吧，不会吧，不会吧！！
        UserType userType = Service.Main.Components.UserServ.UserServ.getUserType(ID);
        switch (userType) {
            case UserType.Student -> {
                this.grade = new SimpleStringProperty(Service.Main.Components.UserServ.UserServ.getGrade(ID).toString());
                this.school = new SimpleStringProperty(Service.Main.Components.UserServ.UserServ.getSchool(ID).toString());
                this.gender = new SimpleStringProperty(Service.Main.Components.UserServ.UserServ.getGender(ID).toString());
                this.money = new SimpleStringProperty(Service.Main.Components.UserServ.UserServ.getMoney(ID).toString());
            }
            case UserType.Teacher -> {
                this.grade = new SimpleStringProperty("");
                this.school = new SimpleStringProperty(Service.Main.Components.UserServ.UserServ.getSchool(ID).toString());
                this.gender = new SimpleStringProperty("");
                this.money = new SimpleStringProperty("");
            }
            case UserType.Admin -> {
                this.grade = new SimpleStringProperty("");
                this.school = new SimpleStringProperty("");
                this.gender = new SimpleStringProperty("");
                this.money = new SimpleStringProperty("");
            }
            case UserType.None ->
                    throw new RuntimeException("Error:UserInformationForTable,Constructor get a None UserType!");
            case null -> throw new RuntimeException("Error:UserInformationForTable,Constructor get a null UserType!");
        }
    }//构造之前一定要验证ID是否存在

    public String getID() {
        return ID.get();
    }

    public SimpleStringProperty IDProperty() {
        return ID;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public String getGrade() {
        return grade.get();
    }

    public SimpleStringProperty gradeProperty() {
        return grade;
    }

    public String getSchool() {
        return school.get();
    }

    public SimpleStringProperty schoolProperty() {
        return school;
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public String getMoney() {
        return money.get();
    }

    public SimpleStringProperty moneyProperty() {
        return money;
    }
}
