package GUI.Data.Enum.Classes;

public enum CourseTime {
    /*
     * 主体
     */
    //星期一
    Section1(Week.Monday,1),
    Section2(Week.Monday,2),
    Section3(Week.Monday,3),
    Section4(Week.Monday,4),
    Section5(Week.Monday,5),
    Section6(Week.Monday,6),
    Section7(Week.Monday,7),
    Section8(Week.Monday,8),
    Section9(Week.Monday,9),
    Section10(Week.Monday,10),
    Section11(Week.Monday,11),
    Section12(Week.Monday,12),
    Section13(Week.Monday,13),
    //星期二
    Section14(Week.Tuesday,1),
    Section15(Week.Tuesday,2),
    Section16(Week.Tuesday,3),
    Section17(Week.Tuesday,4),
    Section18(Week.Tuesday,5),
    Section19(Week.Tuesday,6),
    Section20(Week.Tuesday,7),
    Section21(Week.Tuesday,8),
    Section22(Week.Tuesday,9),
    Section23(Week.Tuesday,10),
    Section24(Week.Tuesday,11),
    Section25(Week.Tuesday,12),
    Section26(Week.Tuesday,13),
    //星期三
    Section27(Week.Wednesday,1),
    Section28(Week.Wednesday,2),
    Section29(Week.Wednesday,3),
    Section30(Week.Wednesday,4),
    Section31(Week.Wednesday,5),
    Section32(Week.Wednesday,6),
    Section33(Week.Wednesday,7),
    Section34(Week.Wednesday,8),
    Section35(Week.Wednesday,9),
    Section36(Week.Wednesday,10),
    Section37(Week.Wednesday,11),
    Section38(Week.Wednesday,12),
    Section39(Week.Wednesday,13),
    //星期四
    Section40(Week.Thursday,1),
    Section41(Week.Thursday,2),
    Section42(Week.Thursday,3),
    Section43(Week.Thursday,4),
    Section44(Week.Thursday,5),
    Section45(Week.Thursday,6),
    Section46(Week.Thursday,7),
    Section47(Week.Thursday,8),
    Section48(Week.Thursday,9),
    Section49(Week.Thursday,10),
    Section50(Week.Thursday,11),
    Section51(Week.Thursday,12),
    Section52(Week.Thursday,13),
    //星期五
    Section53(Week.Friday,1),
    Section54(Week.Friday,2),
    Section55(Week.Friday,3),
    Section56(Week.Friday,4),
    Section57(Week.Friday,5),
    Section58(Week.Friday,6),
    Section59(Week.Friday,7),
    Section60(Week.Friday,8),
    Section61(Week.Friday,9),
    Section62(Week.Friday,10),
    Section63(Week.Friday,11),
    Section64(Week.Friday,12),
    Section65(Week.Friday,13),
    //星期六
    Section66(Week.Saturday,1),
    Section67(Week.Saturday,2),
    Section68(Week.Saturday,3),
    Section69(Week.Saturday,4),
    Section70(Week.Saturday,5),
    Section71(Week.Saturday,6),
    Section72(Week.Saturday,7),
    Section73(Week.Saturday,8),
    Section74(Week.Saturday,9),
    Section75(Week.Saturday,10),
    Section76(Week.Saturday,11),
    Section77(Week.Saturday,12),
    Section78(Week.Saturday,13),
    //星期日
    Section79(Week.Sunday,1),
    Section80(Week.Sunday,2),
    Section81(Week.Sunday,3),
    Section82(Week.Sunday,4),
    Section83(Week.Sunday,5),
    Section84(Week.Sunday,6),
    Section85(Week.Sunday,7),
    Section86(Week.Sunday,8),
    Section87(Week.Sunday,9),
    Section88(Week.Sunday,10),
    Section89(Week.Sunday,11),
    Section90(Week.Sunday,12),
    Section91(Week.Sunday,13);

    private final Week week;
    private final int section;

    CourseTime(Week week, int section) {
        this.week = week;
        this.section = section;
    }

    public Week getWeek() {
        return week;
    }

    public int getSection() {
        return section;
    }

    public String toString(){
        return '('+this.week.toString()+','+this.section+')';
    }
}
