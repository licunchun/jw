package GUI.Data.DataPackage.Classes;

import GUI.Data.DataPackage.UserInformation.UserInformationForTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public ObservableList<UserInformationForTable> toObservableList(){
        ObservableList<UserInformationForTable> observableList = FXCollections.observableArrayList();
        if(IDSet==null){
            return observableList;
        }
        for(String ID:IDSet){
            observableList.add(new UserInformationForTable(ID));
        }
        return observableList;
    }
}
