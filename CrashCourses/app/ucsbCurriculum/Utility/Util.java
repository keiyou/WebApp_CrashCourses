package ucsbCurriculum.Utility;

//import java.util.ArrayList;
import ucsbCurriculum.Utility.Time;

public class Util {
	// return true if those two Time have conflicts
	public static boolean have_conflict (Time t1, Time t2) {
		return t1.startTime > t2.startTime ? (t1.startTime > t2.endTime) : (t2.startTime > t1.startTime);
	}

  public static Time converts_to_minute(String daym, String timm){
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

    //String hello = "9:30am - 12:33am";
		String hi;
    String newTime = timm.replaceAll("\\s+", "");
    String[] tab = newTime.split("-");

		int timesforday = 0;

		//converts day to int
    String daytomin = daym;
    if(daytomin.contains("M"))
      timesforday=0;
    if(daytomin.contains("T"))
      timesforday=(24*60);
    if(daytomin.contains("W"))
      timesforday=(48*60);
    if(daytomin.contains("R"))
      timesforday=(72*60);
    if(daytomin.contains("F"))
      timesforday=(96*60);

		//converts time to int
    for (int i=0; i<tab.length; i++) {
      if (tab[i].contains("pm")) {
        if(tab[i].length()==6) {
          hi = tab[i];
          a_char = hi.charAt(0);
          b_char = hi.charAt(2);
          c_char = hi.charAt(3);

          addedmin = Character.getNumericValue(a_char);
          minutes = Character.getNumericValue(b_char);
          more = Character.getNumericValue(c_char);
          time = (12*60) + (addedmin * 60) + (minutes*10) + (more
					t[i]=time;
        }
        else if (tab[i].length() == 7) {
          hi = tab[i];
          a_char = hi.charAt(0);
          d_char = hi.charAt(1);
          b_char = hi.charAt(3);
          c_char = hi.charAt(4);

          addedmin = Character.getNumericValue(a_char);
          firstmin = Character.getNumericValue(d_char);
          minutes = Character.getNumericValue(b_char);
          more = Character.getNumericValue(c_char);

          if ((addedmin==1)&&(firstmin==2)) {
            time = ((12 * 60) + (minutes*10) + (more));
            t[i]=time;
          }
          else {
            time = ((12*60) + ((10 + firstmin) * 60) + (minutes*10) + (more));
            t[i]=time;
          }
        }
      }

      else if (tab[i].contains("am")) {
        if(tab[i].length()==6){
	        hi = tab[i];
	        a_char = hi.charAt(0);
	        b_char = hi.charAt(2);
	        c_char = hi.charAt(3);

	        addedmin = Character.getNumericValue(a_char);
	        minutes = Character.getNumericValue(b_char);
	        more = Character.getNumericValue(c_char);
	        time =(addedmin * 60) + (minutes*10) + (more);
	        t[i]=time;
        }
      	else if (tab[i].length() == 7) {
          hi = tab[i];
          a_char = hi.charAt(0);
          d_char = hi.charAt(1);
          b_char = hi.charAt(3);
          c_char = hi.charAt(4);

          addedmin = Character.getNumericValue(a_char);
          firstmin = Character.getNumericValue(d_char);
          minutes = Character.getNumericValue(b_char);
          more = Character.getNumericValue(c_char);

          if ((addedmin==1)&&(firstmin==2)) {
            time = 0 +(minutes*10)+more;
            t[i]=time;
          }
          else {
          	time = (((10 + firstmin) * 60) + (minutes*10) + (more));
            t[i]=time;
          }
        }
      }
			//return t;
		}

    Time ti= new Time(0,0);
    ti.startTime=timesforday+t[0];
    ti.endTime=timesforday+t[1];
    //System.out.println(ti.startTime + " " + ti.endTime);
    return ti;
	}

	public static String convert_to_string(Time t) {
		int starttime = t.startTime;
		int endtime = t.endTime;
		String[] names = {"M","T","W","R","F"};

    //starttime
    int startday = (starttime / (24*60));
    String strday1 = names[startday];

    boolean fpm = false;
    boolean spm = false;

    String strtime = "";
    //starttime
    int fhour = 0;
    int fdays = (starttime/(24*60));
    int ftenminute = 0;
    int foneminute = 0;

    if (fdays==0) {
      fhour = (starttime/(60));
      ftenminute = ((starttime-(fhour*60))/10);
      foneminute = (starttime-(fdays*24*60)-(fhour*60))%10;

      if (fhour>12) {
        fhour=fhour-12;
        fpm = true;
      }
      if (fhour == 12) {
      	fpm = true;
      }
    }

    if(fdays >0){
      fhour = (starttime-(24*60*fdays))/(60);
      ftenminute = (starttime-(24*60*fdays)-(fhour*60))/(10);
      foneminute = (starttime-(fdays*24*60)-(fhour*60))%10;

      if (fhour>12) {
        fhour=fhour-12;
        fpm = true;
      }
      if (fhour == 12) {
      	fpm = true;
      }
    }

    //endtime
    int stenminute = 0;
    int shour = 0;
    int soneminute = 0;
    int sdays = (endtime/(24*60));

		if(sdays==0){
      shour = (endtime/(60));
      stenminute = ((endtime-(shour*60))/10);
      soneminute = (endtime-(sdays*24*60)-(shour*60))%10;

      if (shour>12) {
        shour=shour-12;
        spm = true;
      }
      if (shour == 12) {
      	spm = true;
      }
    }

    if (sdays > 0) {
      shour = (endtime-(24*60*sdays))/(60);
      stenminute = (endtime-(24*60*sdays)-(shour*60))/(10);
      soneminute = (endtime-(sdays*24*60)-(shour*60))%10;

      if (shour>12) {
        shour=shour-12;
        spm = true;
      }
      if (shour == 12) {
      	spm = true;
      }
    }

    String ftime;
    String stime;

    if (fpm == true) {
    	ftime = "pm";
    }
    else {
    	ftime = "am";
    }

    if (spm == true) {
    	stime = "pm";
    }
    else {
    	stime = "am";
    }

    strtime = strday1 + " " + Integer.toString(fhour) + ":" + Integer.toString(ftenminute) + Integer.toString(foneminute) + ftime + " - " + Integer.toString(shour) + ":" + Integer.toString(stenminute) + Integer.toString(soneminute) + stime + " ";
    return strtime;
	}
}
