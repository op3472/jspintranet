package net.member.db;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberBean { 
    private String MEMBER_ID; 
    private String MEMBER_PW; 
    private String MEMBER_NAME; 
    private int MEMBER_AGE; 
    private String MEMBER_GENDER; 
    private String MEMBER_EMAIL; 
    private int MEMBER_GRADE;
    public String getMEMBER_ID() { 
        return MEMBER_ID; 
    } 
    public void setMEMBER_ID(String member_id) { 
        MEMBER_ID = member_id; 
    } 
    public String getMEMBER_PW() { 
        return MEMBER_PW; 
    } 
    public void setMEMBER_PW(String member_pw) { 
        MEMBER_PW = member_pw; 
    } 
    public String getMEMBER_NAME() { 
        return MEMBER_NAME; 
    } 
    public void setMEMBER_NAME(String member_name) { 
        MEMBER_NAME = member_name; 
    } 
    public int getMEMBER_AGE() { 
        return MEMBER_AGE; 
    } 
    public void setMEMBER_AGE(int member_age) { 
        MEMBER_AGE = member_age; 
    } 
    public String getMEMBER_GENDER() { 
        return MEMBER_GENDER; 
    } 
    public void setMEMBER_GENDER(String member_gender) { 
        MEMBER_GENDER = member_gender; 
    } 
    public String getMEMBER_EMAIL() { 
        return MEMBER_EMAIL; 
    } 
    public void setMEMBER_EMAIL(String member_email) { 
        MEMBER_EMAIL = member_email; 
    } 
    public void setMEMBER_GRADE(int member_grade){
    	MEMBER_GRADE = member_grade;
    }
    public int getMEMBER_GRADE() {
    	return MEMBER_GRADE;
    }
 }

