package GUI.Data.DataPackage.Classes;

import java.util.HashSet;
import java.util.Set;

import static Sevice.Main.Components.UserServ.UserServ.getName;

public class IDSet {
    private Set<String> IDSet=new HashSet<>();
    public IDSet(){

    }
    public void add(String ID){
        this.IDSet.add(ID);
    }

    public String getNames(){
        StringBuilder stringBuilder=new StringBuilder();
        int i=0;
        for (String ID:IDSet) {
            stringBuilder.append(getName(ID));
            if (i < IDSet.size() - 1) {
                stringBuilder.append(",");
            }
            i++;
        }
        return stringBuilder.toString();
    }
}
