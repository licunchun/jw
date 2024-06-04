package GUI.Data.DataPackage.Classes;

import GUI.Data.DataPackage.UserInformation.UserInformationForTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

import static Service.Main.Components.UserServ.UserServ.getName;

public class IDSet {
    private final Set<String> IDSet = new HashSet<>();
    private List<String> IDList = null;

    public IDSet() {

    }

    public IDSet(Set<String> IDSet) {
        this.IDSet.addAll(IDSet);
    }

    public int size() {
        return IDSet.size();
    }

    public void add(String ID) {
        this.IDSet.add(ID);
    }

    public String getNames() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (String ID : IDSet) {
            stringBuilder.append(getName(ID));
            if (i < IDSet.size() - 1) {
                stringBuilder.append(",");
            }
            i++;
        }
        return stringBuilder.toString();
    }

    public ObservableList<UserInformationForTable> toObservableList() {
        ObservableList<UserInformationForTable> observableList = FXCollections.observableArrayList();
        for (String ID : IDSet) {
            observableList.add(new UserInformationForTable(ID));
        }
        return observableList;
    }

    public Iterable<String> getStudentIDSetIterable() {
        return IDSet;
    }



    public IDSet getSubSet(int fromIndex, int toIndex) {
        if (IDList == null) {
            IDList = new ArrayList<>(IDSet);
        }
        return new IDSet(new HashSet<>(IDList.subList(fromIndex, toIndex)));
    }

    public void print() {
        for (String s:IDSet)
            System.out.println(s);
    }
}
