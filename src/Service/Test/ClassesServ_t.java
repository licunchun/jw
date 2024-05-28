package Service.Test;

import GUI.Data.DataPackage.Classes.Classes;
import GUI.Data.DataPackage.Classes.ClassesSet;
import GUI.Data.DataPackage.Classes.IDSet;
import Service.Main.Components.ClassServ.ClassesServ;

public class ClassesServ_t {
    public static void main(String[] args) {
        Classes c = ClassesServ.getClasses("NSE4015.01");
        c.print();
        ClassesSet cs = ClassesServ.searchClasses(c);
        for (Classes a: cs.getClassesIterable()){
            a.print();
        }
        IDSet idSet = ClassesServ.getStudentSet("NSE4015.01");
        for (String ID: idSet.getStudentIDSetIterable()){
            System.out.println(ID);
        }
        System.out.println(ClassesServ.getStudentScore("011103.01","PB22061222"));
        ClassesServ.setStudentScore("011103.01","PB23000000",23);
        System.out.println(ClassesServ.getStudentScore("011103.01","PB23000000"));

//        Classes c = ClassesServ.getClasses("NSE4015.01");
//        ClassesServ.deleteClasses("NSE4015.01");
//        String[] info = ClassesServ.fromClasses(c);
//        ClassesServ.newClasses(info[0],info[1],info[2],info[3],info[4],info[5],info[6],info[7],info[8],info[9],info[10],info[11],info[12],info[13],info[14],info[15],info[16]);

    }
}
