package jsp.board.comment.model;

import java.sql.Date;

public class CommentBean {
	private int comment_num;        // 댓글 글번호
    private int comment_board;        // 게시글 번호
    private String comment_id;        // 댓글 작성자
    private Date comment_date;        // 댓글 작성일
    private int comment_parent;        // 부모글
	private int comment_lev;
    private int comment_seq;
    private String comment_content;    // 댓글 내용
    
    public int getComment_num() {
 		return comment_num;
 	}
 	public void setComment_num(int comment_num) {
 		this.comment_num = comment_num;
 	}
 	public int getComment_board() {
 		return comment_board;
 	}
 	public void setComment_board(int comment_board) {
 		this.comment_board = comment_board;
 	}
 	public String getComment_id() {
 		return comment_id;
 	}
 	public void setComment_id(String comment_id) {
 		this.comment_id = comment_id;
 	}
 	public Date getComment_date() {
 		return comment_date;
 	}
 	public void setComment_date(Date comment_date) {
 		this.comment_date = comment_date;
 	}
 	public int getComment_parent() {
 		return comment_parent;
 	}
 	public void setComment_parent(int comment_parent) {
 		this.comment_parent = comment_parent;
 	}
 	public int getComment_lev() {
 		return comment_lev;
 	}
 	public void setComment_lev(int comment_lev) {
 		this.comment_lev = comment_lev;
 	}
 	public int getComment_seq() {
 		return comment_seq;
 	}
 	public void setComment_seq(int comment_seq) {
 		this.comment_seq = comment_seq;
 	}
 	public String getComment_content() {
 		return comment_content;
 	}
 	public void setComment_content(String comment_content) {
 		this.comment_content = comment_content;
 	}
}
