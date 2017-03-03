package controllers;

import play.*;
import play.mvc.*;
import play.data.DynamicForm;

import play.data.Form;
import play.data.FormFactory;

import views.html.*;

import javax.inject.*;

import ucsbCurriculum.CourseScraper.*;
import ucsbCurriculum.Scheduler.*;

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
        if(requestForm.hasErrors()){
            System.out.println("caocaocoaocaocoac");
        }
        SearchRequest request = requestForm.bindFromRequest().get();
        System.out.println(request.department);
        String res = scraper.getCourseListFor(request.department, request.quarter, request.level);
        return ok(views.html.res.render("Play", res, ""));
    }
    
    public Result addClass(){
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
        return ok(views.html.res.render("Play", "", scheduler.toString()));
    }
    
    public Result deleteClass(){
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
    }
}