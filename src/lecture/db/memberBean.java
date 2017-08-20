package lecture.db;

public class memberBean {
	private int lecture_num;
	private String lecture_id;
	private int lecture_board;
	private String lecture_name;
	private String lecture_subject;
	private int lecture_credit;
	private double result;
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public String getLecture_id() {
		return lecture_id;
	}
	public void setLecture_id(String lecture_id) {
		this.lecture_id = lecture_id;
	}
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
}
