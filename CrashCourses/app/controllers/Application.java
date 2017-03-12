package controllers;

import play.*;
import play.mvc.*;
import play.data.DynamicForm;

import play.data.Form;
import play.data.FormFactory;

import views.html.*;

import javax.inject.*;
import java.util.*;

import ucsbCurriculum.CourseScraper.*;
import ucsbCurriculum.Scheduler.*;
import ucsbCurriculum.Utility.ConstraintTime;

public class Application extends Controller {
    
    public CourseScraper scraper = new CourseScraper();
    public Schedule scheduler = new Schedule();
    
    @Inject 
        FormFactory formFactory;

    public Result index() {
        return ok(views.html.res.render("Play", "", ""));
    }
    
    
    public Result postForm(){
        Form<SearchRequest> requestForm = formFactory.form(SearchRequest.class);
        SearchRequest request = requestForm.bindFromRequest().get();
        System.out.println(request.department);
        String res = scraper.getCourseListFor(request.department, request.quarter, request.level);
        return ok(views.html.res.render("Play", res, scheduler.toString()));
    }
    
    public Result addConstraints(){
        Form<ConstraintTime> requestForm = formFactory.form(ConstraintTime.class);
        ConstraintTime request = requestForm.bindFromRequest().get();
        String start = request.startTime;
        String end = request.endTime;
        scheduler.setOnlySectionsAfter(start);
        scheduler.setOnlySectionsBefore(end);
        scheduler.deleteConflicts();
        scheduler.deleteRestraintConflicts();
        return ok(views.html.res.render("Play", "", scheduler.toString()));
    }
    
    public Result addClass(){
        try{
            DynamicForm in = formFactory.form().bindFromRequest();
            String res = in.get("content");
            res = res.replaceAll("\\s","");
            res = res.toUpperCase();
            res += " ";
            for(int i = 0; i < res.length(); i++)
            {
                if(Character.isDigit(res.charAt(i)))
                {
                    res = res.substring(0, i) + " " + res.substring(i, res.length());
                    break;
                }
            }
            System.out.println(res);
            scheduler.add(scraper.get_course_by_name(res));
            scheduler.deleteConflicts();
            return ok(views.html.res.render("Play", "", scheduler.toString()));
        }catch(NullPointerException e){
            return ok("Wrong input");
        }
    }
    
    public Result deleteClass(){
        try{
            DynamicForm in = formFactory.form().bindFromRequest();
            String res = in.get("content");
            res = res.replaceAll("\\s","");
            res = res.toUpperCase();
            res += " ";
            for(int i = 0; i < res.length(); i++)
            {
                if(Character.isDigit(res.charAt(i)))
                {
                    res = res.substring(0, i) + " " + res.substring(i, res.length());
                    break;
                }
            }
            scheduler.delete(scraper.get_course_by_name(res));
            return ok(views.html.res.render("Play", "", scheduler.toString()));
        }catch(NullPointerException e){
            return ok("wrong inut");
        }
    }
    
    public Result showSchdule(){
        return ok(views.html.schdule.render("play"));
    }
}