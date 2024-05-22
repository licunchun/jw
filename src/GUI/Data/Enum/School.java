package GUI.Data.Enum;

public enum School {
    GiftedYoung("少年班学院"),
    MathematicalSciences("数学科学学院"),
    UnderGraduate("本科生院"),
    PhysicalSciences("物理学院"),
    DepartmentOfPhysics("物理学系"),
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
    CyberScienceAndTechnology("网络空间安全学院"),
    SoftwareEngineering("软件学院"),
    DataScience("大数据学院"),
    NationalSynchrotronRadiationLaboratory("国家同步辐射实验室"),
    StateKeyLaboratoryOfFireScience("火灾科学国家重点实验室"),
    EnvironmentalScienceAndEngineering("环境科学与工程系"),
    InnovationAndEntrepreneurship("创新创业学院"),
    LifeScienceAndMedicine("生命科学与医学部"),
    FutureTechnology("未来技术学院"),
    PhysicsExperimentTeachingCenter("物理实验教学中心"),
    InstituteOfMetals("金属所"),
    DepartmentOfPrecisionMachineryAndPrecisionInstruments("精密机械与精密仪器系"),
    CenterForArtEducation("艺术教学中心"),
    AdvancedTechnologyResearchInstitute("先进技术研究院"),
    DepartmentOfAstronomy("天文学系"),
    PurpleMountainObservatory("紫金山天文台"),
    DepartmentOfChemistry("化学系"),
    DepartmentOfBusinessAdministration("工商管理系"),
    MPACenter1("MPA中心1"),
    SchoolOfBiomedicalEngineering("生物医学工程学院"),
    DepartmentOfPsychology("心理学系"),
    MFCenter("MF中心（金融专硕）"),
    CenterForPhysicalEducation("体育教学中心"),
    HeFeiInstitutesOfPhysicalScience("合肥物质研究院"),
    DepartmentOfElectronicEngineeringAndInformationScience("电子工程与信息科学系"),
    SchoolOfLifeSciences("生命科学学院"),
    DepartmentOfSafetyScienceAndEngineering("安全科学与工程系"),
    DepartmentOfManagementScience("管理科学系"),
    Library("图书馆"),
    DepartmentOfComputerScienceAndTechnology("计算机科学与技术系"),
    DepartmentOfPhilosophyOfScienceAndTechnology("科技哲学系"),
    DepartmentOfModernPhysics("近代物理系"),
    CenterForCrossDisciplinaryStudiesInLinguistics("语言科学交叉研究中心"),
    DepartmentOfModernMechanics("近代力学系"),
    NanoScienceAndTechnologyInstitute("纳米学院"),
    InstituteOfRareEarths("稀土研究院"),
    DepartmentOfAppliedChemistry("应用化学系"),
    DepartmentOfScienceCommunication("科技传播系"),
    SchoolOfSoftwareSuzhou("软件学院苏州"),
    HeFeiNationalLaboratoryForMicroScaleSciences("合肥微尺度物质科学国家实验室（筹）"),
    MasterOfLawsEducationCenter("法律硕士教育中心"),
    MBACenter("MBA中心"),
    DepartmentOfThermalScienceAndEnergyEngineering("热科学和能源工程系"),
    DepartmentOfStatisticsAndFinance("统计与金融系"),
    DepartmentOfHistoryAndArchaeologyOfScienceAndTechnology("科技史与科技考古系"),
    CenterForForeignLanguageTeaching("外语教学中心"),
    GeneralEducationCenter("通识教育中心"),
    AcademicAffairsOffice("教务处"),
    ProjectManagementEducationCenter("项目管理教育中心"),
    DepartmentOfMaterialsScienceAndEngineering("材料科学与工程系"),
    DepartmentOfInternationalCooperationAndExchange("国际合作与交流部"),
    DepartmentOfElectronicScienceAndTechnology("电子科学与技术系"),
    DepartmentOfPolymerScienceAndEngineering("高分子科学与工程系"),
    SchoolOfSoftwareHeFei("软件学院合肥"),
    DepartmentOfEngineeringAndAppliedPhysics("工程与应用物理系"),
    DepartmentOfChemicalPhysics("化学物理系"),
    DepartmentOfEnvironmentalScienceAndEngineering("环境科学与工程系（直属）"),
    DepartmentOfOpticsAndOpticalEngineering("光学与光学工程系"),
    DepartmentOfAutomation("自动化系"),
    InstituteOfAppliedChemistry("长春应化所");
    private final String name;

    School(String name) {
        this.name = name;
    }

    public String toString() {   //生成中文字符串
        return this.name;
    }

    public static School fromString(String s){
        for (int i = 0; i < values().length; i++) {
            String s_t = values()[i].toString();
            if(s.compareTo(s_t)==0)
                return values()[i];
        }
        throw new IllegalArgumentException("Invalid input: " + s);
    }

}
