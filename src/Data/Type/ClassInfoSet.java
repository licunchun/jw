package Data.Type;

import java.util.ArrayList;

public class ClassInfoSet {
    public ArrayList<ClassInfo> classInfos;

    public ClassInfoSet() {
        classInfos = new ArrayList<>();
    }

    public ClassInfo get(int i) {
        return classInfos.get(i);
    }

    public int length() {
        return classInfos.size();
    }

    public void findCode(String code) {
        if (code.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).code.contains(code)) classInfos.remove(i--);
        }
    }

    public void findName(String name) {
        if (name.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).name.contains(name)) classInfos.remove(i--);
        }
    }

    public void findPeriod(String period) {
        if (period.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).period.equals(period)) classInfos.remove(i--);
        }
    }

    public void findCredit(String credit) {
        if (credit.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).credits.equals(credit)) classInfos.remove(i--);
        }
    }

    public void findCourseType(String courseType) {
        if (courseType.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).courseType.equals(courseType)) classInfos.remove(i--);
        }
    }

    public void findDepartment(String department) {
        if (department.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).department.equals(department)) classInfos.remove(i--);
        }
    }

    public void findCampus(String campus) {
        if (campus.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).campus.equals(campus)) classInfos.remove(i--);
        }
    }

    public void findExamMode(String examMode) {
        if (examMode.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).examMode.contains(examMode)) classInfos.remove(i--);
        }
    }

    public void findLanguage(String language) {
        if (language.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).Language.equals(language)) classInfos.remove(i--);
        }
    }

    public void findEducation(String education) {
        if (education.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).education.equals(education)) classInfos.remove(i--);
        }
    }

    public void findClassType(String classType) {
        if (classType.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).classType.equals(classType)) classInfos.remove(i--);
        }
    }

    public void findTeacher(String teacher) {
        if (teacher.isEmpty()) return;

        for (int i = 0; i < this.length(); i++) {
            boolean flag = true;

            for (String j : this.get(i).teachers) {
                if (j.contains(teacher)) {
                    flag = false;
                    break;
                }
            }

            if (flag) this.classInfos.remove(i--);
        }
    }

    public void findNotFull() {
        for (int i = 0; i < this.length(); i++) {
            if (Integer.parseInt(this.get(i).stdCount) >= Integer.parseInt(this.get(i).limitCount))
                classInfos.remove(i--);
        }
    }
}
