/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author Loly
 */
public class Fecha {
    //LocalDate a Long
    public static long getFechaLong(LocalDate fecha){
        String dia = String.valueOf(fecha.getDayOfMonth());
        String mes = String.valueOf(fecha.getMonthValue());
        String ano = String.valueOf(fecha.getYear());
        String fechaString = dia +"/"+mes+"/"+ano+" 00:00:00";
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date;
        long millis;
        try{
            date = (Date) sdf.parse(fechaString);
            millis = date.getTime();
        }catch(Exception ex){
            System.out.println("Error al convertir de Localdate a long: "+ex);
            millis = 0;
        }
        return millis;
    }
    //Long a LocalDate
    public static LocalDate getFechaLocalDate(long millis){
        return new java.sql.Date(millis).toLocalDate();
    }
    
    //long a string
    public static String getFechaString(long millis){
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
