package lecture.db;

import java.sql.Date;

public class CommentBean {
	private int lecture_num;
	private int lecture_board;
	private String lecture_id;
	private Date lecture_date;
	private int lecture_seq;
	private String lecture_content;
	private double lecture_grade;
	public int getLecture_num() {
		return lecture_num;
	}
	public void setLecture_num(int lecture_num) {
		this.lecture_num = lecture_num;
	}
	public int getLecture_board() {
		return lecture_board;
	}
	public void setLecture_board(int lecture_board) {
		this.lecture_board = lecture_board;
	}
	public String getLecture_id() {
		return lecture_id;
	}
	public void setLecture_id(String lecture_id) {
		this.lecture_id = lecture_id;
	}
	public Date getLecture_date() {
		return lecture_date;
	}
	public void setLecture_date(Date lecture_date) {
		this.lecture_date = lecture_date;
	}
	public int getLecture_seq() {
		return lecture_seq;
	}
	public void setLecture_seq(int lecture_seq) {
		this.lecture_seq = lecture_seq;
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
}
