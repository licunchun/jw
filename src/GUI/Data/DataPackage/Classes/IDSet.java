package GUI.Data.DataPackage.Classes;

import GUI.Data.DataPackage.UserInformation.UserInformationForTable;
import Service.Data.Tables.Teachers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (IDSet == null) {
            return observableList;
        }
        for (String ID : IDSet) {
            observableList.add(new UserInformationForTable(ID));
        }
        return observableList;
    }

    public Iterable<String> getStudentIDSetIterable() {
        return IDSet;
    }

    public static IDSet fromTeacherString(String teachers){
        IDSet idSet = new IDSet();
        String pattern = "^[一-龥·]+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(teachers);
        while (m.find()) {
            String name = m.group();
            String ID = Teachers.getID(name);
            idSet.add(ID);
        }
        return idSet;
    }

    public IDSet getSubSet(int fromIndex, int toIndex) {
        if (IDList == null) {
            IDList = new ArrayList<>(IDSet);
        }
        return new IDSet(new HashSet<String>(IDList.subList(fromIndex, toIndex)));
    }

    public void print() {
        for (String s:IDSet)
            System.out.println(s);
    }
}
