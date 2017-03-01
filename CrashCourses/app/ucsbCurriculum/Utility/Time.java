package ucsbCurriculum.Utility;

public class Time {
    public int startTime;
    public int endTime;
    
    public Time(int start, int end) {
        startTime = start;
        endTime = end;
    }
    
    public int getStart() {
        return startTime;
    }
    
    public int getEnd() {
        return endTime;
    }
}
