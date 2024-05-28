package Data.Type;

import kotlin.reflect.KClassifier;

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

    public ClassInfoSet findCode(String code) {
        if (code.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).code.contains(code)) classInfos.remove(i--);
        }
        return this;
    }

    public ClassInfoSet findName(String name) {
        if (name.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).name.contains(name)) classInfos.remove(i--);
        }
        return this;
    }

    public ClassInfoSet findPeriod(String period) {
        if (period.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).period.equals(period)) classInfos.remove(i--);
        }
        return this;
    }

    public ClassInfoSet findCredit(String credit) {
        if (credit.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).credits.equals(credit)) classInfos.remove(i--);
        }
        return this;
    }

    public ClassInfoSet findCourseType(String courseType) {
        if (courseType.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).courseType.equals(courseType)) classInfos.remove(i--);
        }
        return this;
    }

    public ClassInfoSet findDepartment(String department) {
        if (department.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).department.equals(department)) classInfos.remove(i--);
        }
        return this;
    }

    public ClassInfoSet findCampus(String campus) {
        if (campus.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).campus.equals(campus)) classInfos.remove(i--);
        }
        return this;
    }

    public ClassInfoSet findExamMode(String examMode) {
        if (examMode.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).examMode.contains(examMode)) classInfos.remove(i--);
        }
        return this;
    }

    public ClassInfoSet findLanguage(String language) {
        if (language.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).Language.equals(language)) classInfos.remove(i--);
        }
        return this;
    }

    public ClassInfoSet findEducation(String education) {
        if (education.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).education.equals(education)) classInfos.remove(i--);
        }
        return this;
    }

    public ClassInfoSet findClassType(String classType) {
        if (classType.isEmpty()) return this;

        for (int i = 0; i < this.length(); i++) {
            if (!this.get(i).classType.equals(classType)) classInfos.remove(i--);
        }
        return this;
    }

    public ClassInfoSet findTeacher(String teacher) {
        if (teacher.isEmpty()) return this;

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
        return this;
    }

    public ClassInfoSet findNotFull() {
        for (int i = 0; i < this.length(); i++) {
            if (Integer.parseInt(this.get(i).stdCount) >= Integer.parseInt(this.get(i).limitCount))
                classInfos.remove(i--);
        }
        return this;
    }
}
