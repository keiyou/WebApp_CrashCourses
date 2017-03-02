package ucsbCurriculum.Scheduler;

import java.util.ArrayList;

import ucsbCurriculum.Utility.*;


public class Schedule {

    private ArrayList<Course> courses; // ArrayList of lectures
    private ArrayList<ArrayList<Time>> sections;  // ArrayList of (ArrayList of sections)

    public Schedule(){
        courses = new ArrayList<Course>();
        sections = new ArrayList<ArrayList<Time>>();
    }

    public static boolean compare(ArrayList<Time> time1, ArrayList<Time> time2) {

      if(time1.size() == 0 || time2.size() == 0)
          return true;

      for (int i = 0; i < time1.size(); i++) {
        for (int j = 0; j < time2.size(); j++) {
          if (!Util.have_conflict(time1.get(i), time2.get(j))) {
            // TO-DO: delete that element in the second list
            time2.remove(j);
          }
        }
      }
      if (time2.size()==0)
        return false;
      else
        return true;
	}

    public void clearSchedule(){
    	while(courses.size() != 0){
    		delete(courses.get(0));
    	}
    }

    // if c result in conflicts, then just print a message and return
    public void add(Course c) {
      if(c == null)
        System.out.println("This is not a valid course.");

      ArrayList<Time> sectionTime = c.get_sectionTimes();
      for (int i = 0; i < courses.size(); i++) {
        Course temp = courses.get(i);
        if (temp.equals(c)) {
          // Only for command line version!!!
          System.out.println("Course already added!!!");
          return;
        }

        //if (!Course.compare(temp, c) || !compare(sections.get(i), sectionTime) || !compare(temp.get_lectureTimes(), sectionTime)) {
          // Only for command line version!!!
          //System.out.println("Conflicts detected! This course cannot be add to schedule!");
          //return;
        //}
      }

      courses.add(c);
      sections.add(sectionTime);
    }



    @Override
    public String toString() {
		String res = "Current Schedule:\n\n";

	    	for(int i = 0; i < courses.size(); i++)
	    	{
	    		Course c = courses.get(i);
	    		res += c.get_id() + c.get_name() + "\t";
	    		ArrayList<Time> lectureTimes = c.get_lectureTimes();
	    		for(int j = 0; j < lectureTimes.size(); j++)
	    		{
	    			res += Util.convert_to_string(lectureTimes.get(j)) + "\t";
	    		}
	            res += "\n\t\t\t" + "Section Time" + "\t";
	            ArrayList<Time> sectionTimes = sections.get(i);
	            if(sectionTimes.size() != 0)
	                res += Util.convert_to_string(sectionTimes.get(0)) + "\n";
	            else
	                res += "\n";
	    	}
	    	return res;
    }



    //removes course c's information from lecture times and section times array lists
    public void delete(Course c) {
	    	int i = courses.indexOf(c);
	    	courses.remove(i);
	    	sections.remove(i);
	    	System.out.println(c.get_id() + " is being deleted.");
	    	//user should be given some kind of warning - course being deleted because of time conflict/or personal choice
    }

    //goes through array list of sections and deletes all section times that have conflicts with other times
    //courses that were added first have higher priority
    //if a course has zero available section times left after comparison, it will be deleted from the list
    public void deleteConflicts() {
    	//first check all sections against conflicting lecture times
    	for (int i = 0; i < sections.size(); i++) {
    		boolean sectionToLectureConflict = true;
    		for (int j = 0; j < courses.size(); j++) {
    			sectionToLectureConflict = compare((courses.get(j)).get_lectureTimes(), sections.get(i));
    			if (sectionToLectureConflict == false) {
    				//false will be returned if all section times for a lecture have been removed due to conflict
    				delete(courses.get(i));
    			}
    		}
    	}

    	//now check all sections against previous sections
    	//start at 1 because course at position 0 has priority
    	for (int i = 1; i < sections.size(); i++) {
    		ArrayList<Time> currentSection = sections.get(i);
    		//now compare current section list with all previous section lists
    		boolean sectionToSectionConflict = true;
    		for (int j = i-1; j >= 0; j--) {
				sectionToSectionConflict = (compare(sections.get(j), currentSection));
				if (sectionToSectionConflict == false) {
					//no available section times due to conflict; remove course c
					delete(courses.get(i));
				}
			}
		}
	}
}
