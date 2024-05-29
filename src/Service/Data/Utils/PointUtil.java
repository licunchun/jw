package Service.Data.Utils;

public class PointUtil {
    public static double pointToGPA(String point){
        int grade = Integer.parseInt(point);
        if(grade>=95)
            return 4.3;
        else if (grade>=90)
            return 4.0;
        else if (grade>=85)
            return 3.7;
        else if (grade>=82)
            return 3.3;
        else if (grade>=78)
            return 3.0;
        else if (grade>=75)
            return 2.7;
        else if (grade>=72)
            return 2.3;
        else if (grade>=68)
            return 2.0;
        else if (grade>=65)
            return 1.7;
        else if (grade == 64)
            return 1.5;
        else if (grade>=61)
            return 1.3;
        else if (grade == 60)
            return 1.0;
        else
            return 0.0;
    }
}
