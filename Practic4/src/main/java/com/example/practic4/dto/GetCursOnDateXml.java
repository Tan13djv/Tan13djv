package com.example.practic4.dto;


import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlRootElement(name = "GetCursOnDateXML", namespace = "http://web.cbr.ru/")
@Data //Геттеры и сеттеры
public class GetCursOnDateXml {

    @XmlElement(name = "On_date", required = true, namespace = "http://web.cbr.ru/") //Указание на то, в каком теге XML должно быть данное поле
    protected XMLGregorianCalendar onDate;
}