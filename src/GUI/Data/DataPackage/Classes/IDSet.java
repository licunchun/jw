package GUI.Data.DataPackage.Classes;

import java.util.HashSet;
import java.util.Set;

public class IDSet {
    private Set<String> IDSet=new HashSet<>();
    public IDSet(){

    }
    public void add(String ID){
        this.IDSet.add(ID);
    }
}
