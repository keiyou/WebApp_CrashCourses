package WebScrapper;


public class SearchRequest {
    public String department;
    public String quarter;
    public String level;
    
    public void setDepartment(String dept){
        this.department = dept;
    }
    
    public String getDepartment(){
        return department;
    }
    
    public void setQuarter(String qtr){
        this.quarter = qtr;
    }
    
    public String getQuarter(){
        return quarter;
    }
    
    public void setLevel(String l){
        this.level = l;
    }
    
    public String getLevel(){
        return level;
    }
    
}