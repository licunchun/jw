package Data.Enum.User;

import Data.Enum.School;

public enum StudentSchool {
    GiftedYoung("少年班学院"),
    MathematicalSciences("数学科学学院"),
    PhysicalSciences("物理学院"),
    Management("管理学院"),
    ChemistryAndMaterialsScience("化学与材料科学学院"),
    EarthAndSpaceSciences("地球和空间科学学院"),
    EngineeringScience("工程科学学院"),
    InformationScienceAndTechnology("信息科学技术学院"),
    HumanitiesAndSocialScience("人文与社会科学学院"),
    NuclearScienceAndTechnology("核科学技术学院"),
    ComputerScienceAndTechnology("计算机科学与技术学院"),
    Microelectronics("微电子学院"),
    CyberScienceAndTechnology("网络空间安全学院"),
    DataScience("大数据学院"),
    EnvironmentalScienceAndEngineering("环境科学与工程系"),
    LifeScienceAndMedicine("生命科学与医学部"),
    FutureTechnology("未来技术学院");

    private final String name;

    StudentSchool(String name){
        this.name=name;
    }

    public String toString(){	//生成中文字符串
        return this.name;
    }
    public School toSchool(){	//生成对应的School对象
        for(School school:School.values()){
            if(this.name.equals(school.toString())){
                return school;
            }
        }
        System.exit(-1);
        return School.GiftedYoung;
    }
}
