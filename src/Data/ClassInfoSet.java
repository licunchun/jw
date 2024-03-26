package Data;

import java.util.ArrayList;

public class ClassInfoSet {
    ArrayList<ClassInfo> classInfos;
    public ClassInfoSet() {
        classInfos = new ArrayList<>();
    }
    public ClassInfo index(int i) {
        return classInfos.get(i);
    }
}
