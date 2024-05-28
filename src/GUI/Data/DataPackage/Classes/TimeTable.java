package GUI.Data.DataPackage.Classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TimeTable {//TODO
    private final SimpleIntegerProperty number;
    private final SimpleStringProperty Monday;
    private final SimpleStringProperty Tuesday;
    private final SimpleStringProperty Wednesday;
    private final SimpleStringProperty Thursday;
    private final SimpleStringProperty Friday;
    private final SimpleStringProperty Saturday;
    private final SimpleStringProperty Sunday;

    public TimeTable(int number,String Monday,String Tuesday,String Wednesday,String Thursday,String Friday,String Saturday,String Sunday) {
        this.number = new SimpleIntegerProperty(number);
        this.Monday = new SimpleStringProperty(Monday);
        this.Tuesday = new SimpleStringProperty(Tuesday);
        this.Wednesday = new SimpleStringProperty(Wednesday);
        this.Thursday = new SimpleStringProperty(Thursday);
        this.Friday = new SimpleStringProperty(Friday);
        this.Saturday = new SimpleStringProperty(Saturday);
        this.Sunday = new SimpleStringProperty(Sunday);
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public String getMonday() {
        return Monday.get();
    }

    public SimpleStringProperty mondayProperty() {
        return Monday;
    }

    public String getTuesday() {
        return Tuesday.get();
    }

    public SimpleStringProperty tuesdayProperty() {
        return Tuesday;
    }

    public String getWednesday() {
        return Wednesday.get();
    }

    public SimpleStringProperty wednesdayProperty() {
        return Wednesday;
    }

    public String getThursday() {
        return Thursday.get();
    }

    public SimpleStringProperty thursdayProperty() {
        return Thursday;
    }

    public String getFriday() {
        return Friday.get();
    }

    public SimpleStringProperty fridayProperty() {
        return Friday;
    }

    public String getSaturday() {
        return Saturday.get();
    }

    public SimpleStringProperty saturdayProperty() {
        return Saturday;
    }

    public String getSunday() {
        return Sunday.get();
    }

    public SimpleStringProperty sundayProperty() {
        return Sunday;
    }
}
