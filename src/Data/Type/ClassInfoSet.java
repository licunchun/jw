package Data.Type;

import java.util.ArrayList;

public class ClassInfoSet {
    public ArrayList<ClassInfo> classInfos;
    public ClassInfoSet() {
        classInfos = new ArrayList<>();
    }
    public ClassInfo index(int i) {
        return classInfos.get(i);
    }
}
