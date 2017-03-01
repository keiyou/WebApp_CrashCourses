package ucsbCurriculum.Utility;

//import java.util.ArrayList;
import ucsbCurriculum.Utility.Time;

public class Util {
	
	// return true if those two Time have conflicts
	public static boolean have_conflict (Time t1, Time t2) {
		return t1.startTime > t2.startTime ? (t1.startTime > t2.endTime) : (t2.startTime > t1.startTime);
	}
	
	public static Time converts_to_minute(String daym, String timm) {
        int[] t = new int[2];
        int time = 0;
        
        char a_char;
        char b_char;
        char c_char;
        char d_char;
        
        int addedmin;
        int minutes;
        int firstmin;
        int more;
        
        String hi;
        //String hello = " 9:30am - 12:33am ";
        String[] tab = timm.split("-");
        
        int timesforday=0;
        
        //converts day to int
        String daytomin = daym;
        
        if (daytomin.contains("M"))
            timesforday=0;
        if (daytomin.contains("T"))
            timesforday=(24*60);
        if (daytomin.contains("W"))
            timesforday=(48*60);
        if (daytomin.contains("R"))
            timesforday=(72*60);
        if (daytomin.contains("F"))
        	timesforday=(96*60);
        
        //converts time to int
        for (int i=0; i<tab.length; i++) {
            if (tab[i].contains("pm")) {
                if (tab[i].length()==8) {
	                hi = tab[i];
	                a_char = hi.charAt(1);
	                b_char = hi.charAt(3);
	                c_char = hi.charAt(4);
	                addedmin = Character.getNumericValue(a_char);
	                minutes = Character.getNumericValue(b_char);
	                more = Character.getNumericValue(c_char);
	                time = (12*60) + (addedmin * 60) + (minutes*10) + (more);
	                t[i]=time;
                }
                else if (tab[i].length() == 9) {
	                hi = tab[i];
	                a_char = hi.charAt(1);
	                d_char = hi.charAt(2);
	                b_char = hi.charAt(4);
	                c_char = hi.charAt(5);
	                addedmin = Character.getNumericValue(a_char);
	                firstmin = Character.getNumericValue(d_char);
	                minutes = Character.getNumericValue(b_char);
	                more = Character.getNumericValue(c_char);
	                
                    if ((addedmin==1)&&(firstmin==2)) {
                        time = ((12 * 60) + (minutes*10) + (more));
                        t[i]=time;
                    } else {
                        time = ((12*60) + ((10 + firstmin) * 60) + (minutes*10) + (more));
                        t[i]=time;
                    }
                }                
            }
            
            else if (tab[i].contains("am")) {
                if (tab[i].length() == 8) {
	                hi = tab[i];
	                a_char = hi.charAt(1);
	                b_char = hi.charAt(3);
	                c_char = hi.charAt(4);
	                addedmin = Character.getNumericValue(a_char);
	                minutes = Character.getNumericValue(b_char);
	                more = Character.getNumericValue(c_char);
	                time =(addedmin * 60) + (minutes*10) + (more);
	                t[i]=time;
                }
                else if (tab[i].length() == 9) {
	                hi = tab[i];
	                a_char = hi.charAt(1);
	                d_char = hi.charAt(2);
	                b_char = hi.charAt(4);
	                c_char = hi.charAt(5);
	                addedmin = Character.getNumericValue(a_char);
	                firstmin = Character.getNumericValue(d_char);
	                minutes = Character.getNumericValue(b_char);
	                more = Character.getNumericValue(c_char);
	                
                    if ((addedmin==1)&&(firstmin==2)) {
                        time = 0 +(minutes*10)+more;
                        t[i]=time;
                    } else {
                        time = (((10 + firstmin) * 60) + (minutes*10) + (more));
                        t[i]=time;
                    }
                }                
            }          
        }
        
        Time ti= new Time(timesforday+t[0], timesforday+t[1]);
        return ti;
	}
 
	public static String convert_to_string(Time t) {
	    int starttime = t.startTime;
	    int endtime = t.endTime;
        String[] names = {"M","T","W","R","F"};
        
        //starttime
        int startday = (starttime / (24*60));
        String strday1 = names[startday];
        String strtime;
        int fhour;
        int fdays = (starttime/(24*60));
        
        if (fdays==0) {
            fhour = (starttime/(60))-12;
        } else {
            fhour = (starttime-(fdays*24*60))/60-12;
        }
        
        int ftenminute = (starttime-(fdays*24*60)-(fhour*60+(12*60)))/10;
        int foneminute = (starttime-(fdays*24*60)-(fhour*60+(12*60)))%10;
        
        //endtime 
        int shour;
        int sdays = (endtime/(24*60));
        
        if (sdays==0) {
            shour = (endtime/(60))-12;
        } else {
            shour = (endtime-(sdays*24*60))/60-12;
        }
        
        int stenminute = (endtime-(sdays*24*60)-(shour*60+(12*60)))/10;
        int soneminute = (endtime-(sdays*24*60)-(shour*60+(12*60)))%10;
        strtime = strday1 + " " + Integer.toString(fhour)+":"+Integer.toString(ftenminute)+ Integer.toString(foneminute) + " - " + Integer.toString(shour)+":"+Integer.toString(stenminute)+ Integer.toString(soneminute)+ " ";
        
        return strtime; 
	}
}
