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
    public int length() { return classInfos.size(); }
}
