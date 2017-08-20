package lecture.db;

public class LectureBean {
	private int lecture_num;
	private String lecture_name;
	private String lecture_subject;
	private int lecture_credit;
	private String lecture_content;
	private double lecture_grade;
	private int lecture_check;
	public int getLecture_check() {
		return lecture_check;
	}
	public void setLecture_check(int lecture_check) {
		this.lecture_check = lecture_check;
	}
	public int getLecture_num() {
		return lecture_num;
	}
	public void setLecture_num(int lecture_num) {
		this.lecture_num = lecture_num;
	}
	public String getLecture_name() {
		return lecture_name;
	}
	public void setLecture_name(String lecture_name) {
		this.lecture_name = lecture_name;
	}
	public String getLecture_subject() {
		return lecture_subject;
	}
	public void setLecture_subject(String lecture_subject) {
		this.lecture_subject = lecture_subject;
	}
	public int getLecture_credit() {
		return lecture_credit;
	}
	public void setLecture_credit(int lecture_credit) {
		this.lecture_credit = lecture_credit;
	}
	public String getLecture_content() {
		return lecture_content;
	}
	public void setLecture_content(String lecture_content) {
		this.lecture_content = lecture_content;
	}
	public double getLecture_grade() {
		return lecture_grade;
	}
	public void setLecture_grade(double lecture_grade) {
		this.lecture_grade = lecture_grade;
	}
	public double graderesult(){
		if(lecture_check==0){
			return 0;
		}
		else{
		return lecture_grade/lecture_check;
		}
	}
}
