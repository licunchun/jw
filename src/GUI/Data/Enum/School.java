package GUI.Data.Enum;

public enum School {
    GiftedYoung("少年班学院"),
    MathematicalSciences("数学科学学院"),
    UnderGraduate("本科生院"),
    PhysicalSciences("物理学院"),
    Management("管理学院"),
    ChemistryAndMaterialsScience("化学与材料科学学院"),
    EarthAndSpaceSciences("地球和空间科学学院"),
    EngineeringScience("工程科学学院"),
    InformationScienceAndTechnology("信息科学技术学院"),
    HumanitiesAndSocialScience("人文与社会科学学院"),
    NuclearScienceAndTechnology("核科学技术学院"),
    ComputerScienceAndTechnology("计算机科学与技术学院"),
    PublicAffairs("公共事务学院"),
    EnvironmentalScienceAndOptoelectronicTechnology("环境科学与光电技术学院"),
    Microelectronics("微电子学院"),
    Marxism("马克思主义学院"),
    CyberScienceAndTechnology(" 网络空间安全学院"),
    SoftwareEngineering("软件学院"),
    DataScience("大数据学院"),
    NationalSynchrotronRadiationLaboratory("国家同步辐射实验室"),
    StateKeyLaboratoryOfFireScience("火灾科学国家重点实验室"),
    EnvironmentalScienceAndEngineering("环境科学与工程系"),
    InnovationAndEntrepreneurship("创新创业学院"),
    LifeScienceAndMedicine("生命科学与医学部"),
    FutureTechnology("未来技术学院");

    private final String name;

    School(String name) {
        this.name = name;
    }

    public String toString() {   //生成中文字符串
        return this.name;
    }
}
