package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import util.XmlUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

@XmlRootElement(name = "root")
public class DataStructure {
    public static Logger log;

    @SerializedName(value = "studentsInfo")
    @Expose(serialize = true,deserialize = true)
    @XmlElementWrapper(name = "studentsInfo")
    @XmlElement(name = "studentsEntry")
    public ArrayList<Student> ArrStud;

    @SerializedName(value = "universitiesInfo")
    @Expose(serialize = true,deserialize = true)
    @XmlElementWrapper(name = "universitiesInfo")
    @XmlElement(name = "universityEntry")
    public ArrayList<University> ArrUniv;

    @SerializedName(value = "statisticalInfo")
    @Expose(serialize = true,deserialize = true)
    @XmlElementWrapper(name = "statisticalInfo")
    @XmlElement(name = "statisticsEntry")
    public ArrayList<Statistics> ArrStat;
    @SerializedName(value = "processedAt")
    @Expose(serialize = true,deserialize = true)
    @XmlElement(name = "processedAt")
    String dt;

    public DataStructure setData(ArrayList<Student> ArrStud, ArrayList<University> ArrUniv, ArrayList<Statistics> ArrStat) {
        this.ArrStud = new ArrayList<>(ArrStud);
        this.ArrUniv = new ArrayList<>(ArrUniv);
        this.ArrStat = new ArrayList<>(ArrStat);
        this.dt = String.valueOf(LocalDateTime.now());
        return this;
    }
}
