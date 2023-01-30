package data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public enum InputFields {
    FIRSTRUSNAME("id_fname"),
    FIRSTLATNAME("id_fname_latin"),
    LASTRUSNAME ("id_lname"),
    LASTLATNAME ("id_lname_latin"),
    BLOGNAME ("id_blog_name");


    InputFields(String name){
        this.name= name;
    }

    private String name;

    public String getName(){
        return name;
    }

}
