package Service.Main.Components.ClassServ;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import Service.Data.Tables.Courses;

public class ClassesSearchingServ {
    private String[] codes = {};

    public void searchClasses(Classes inputClasses) {
        String[] conditions = ClassesServ.fromClasses(inputClasses);
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
}
