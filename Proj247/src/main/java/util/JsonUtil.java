package util;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Student;
import model.University;


import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonUtil {

    public static String ConvertUniTo(University Univ){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String gsonStr = gson.toJson(Univ);
        return gsonStr;
    };

    public static String ConvertStudTo(Student Stud){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String gsonStr = gson.toJson(Stud);
        return gsonStr;
    };


    public static String ConvertUniversArrTo(ArrayList<University> ArrUniv){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String gsonStr = gson.toJson(ArrUniv);
        return gsonStr;
    };

    public static String ConvertStudArrTo(ArrayList<Student> ArrStud){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String gsonStr = gson.toJson(ArrStud);
        return gsonStr;
    };
    public static University ConvertUniFrom(String Univ){
        University univer = new Gson().fromJson(Univ,University.class);
        return univer;
    };

    public static Student ConvertStFrom(String Stud){
        Student stud1 = new Gson().fromJson(Stud,Student.class);
        return stud1;
    };


    public static ArrayList<University> ConvertUniversArrFrom(String ArrUniv){
        Type type = new TypeToken<ArrayList<University>>(){}.getType();
        ArrayList<University> univers= new Gson().fromJson(ArrUniv, type);
        return univers;
    };

    public static ArrayList<Student> ConvertStudArrFrom(String ArrStud){
        Type type = new TypeToken<ArrayList<Student>>(){}.getType();
        ArrayList<Student> studs= new Gson().fromJson(ArrStud, type);
        return studs;
    };

}
