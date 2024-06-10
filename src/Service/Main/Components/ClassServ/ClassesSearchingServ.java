package Service.Main.Components.ClassServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import Service.Data.Tables.Courses;
import Service.Data.Utils.DaysUtil;
import Service.Data.Utils.TimeUtil;

public class ClassesSearchingServ {
    private String[] codes = {};

    public void searchClasses(Classes inputClasses, String teacher) {
        String[] conditions = fromClasses(inputClasses);
        conditions[14] = teacher==null?"":teacher;
        codes = Courses.findCode(conditions);
    }

    public int getCount() {
        return codes.length;
    }

    public ClassesSet getClassesSet(int fromIndex, int toIndex) {
        ClassesSet cs = new ClassesSet();
        for (int i = fromIndex; i < toIndex; i++) {
            String[] info = Courses.getInfo(codes[i]);
            cs.add(Classes.fromArray(info));
        }
        return cs;
    }
    private static String[] fromClasses(Classes classes){
        String code = classes.getCode()==null?"":classes.getCode();
        String name = classes.getName()==null?"":classes.getName();
        String period = classes.getPeriod()==null?"":classes.getPeriod().toString();
        String credits = classes.getCredits()==null?"":classes.getCredits().toString();
        String times;
        if(classes.getTime()==null)
            times = "";
        else {
            String days = DaysUtil.getDaysFromCourseTimeSet(classes.getTime());
            times = DaysUtil.getLikeDays(days);
        }
        String stdCount = "";
        String limitCount = "";
        String classType = classes.getClassType()==null?"":classes.getClassType().toString();
        String courseType = classes.getCourseType()==null?"":classes.getCourseType().toString();
        String school = classes.getSchool()==null?"":classes.getSchool().toString();
        String campus = classes.getCampus()==null?"":classes.getCampus().toString();
        String examMode = classes.getExamMode()==null?"":classes.getExamMode().toString();
        String language = classes.getLanguage()==null?"":classes.getLanguage().toString();
        String education = classes.getEducation()==null?"":classes.getEducation().toString();
        String teacher = "";
        if (classes.getTeacher() != null) {
            Iterable<String> IDs = classes.getTeacher().getStudentIDSetIterable();
            for (String ID:IDs){
                teacher = ID;
                break;
            }
        }
        String full = classes.getFull()==null?"":classes.getFull().toString();
        String place = "";

        return new String[]{
                code,
                name,
                period,
                credits,
                times,
                stdCount,
                limitCount,
                classType,
                courseType,
                school,
                campus,
                examMode,
                language,
                education,
                teacher,
                full,
                place
        };
    }
}
