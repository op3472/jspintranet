package message.db;

import java.util.Date;

public class MessageBean {
	private int messageId;
	private Date messageDate;
	private String messageSubject;
	private String messageContent;
	private String messageSender;
	private String messageAddressee;
	private int messageReadcheck;
	public int getMessageReadcheck() {
		return messageReadcheck;
	}
	public void setMessageReadcheck(int messageReadcheck) {
		this.messageReadcheck = messageReadcheck;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	public String getMessageSubject() {
		return messageSubject;
	}
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getMessageSender() {
		return messageSender;
	}
	public void setMessageSender(String messageSender) {
		this.messageSender = messageSender;
	}
	public String getMessageAddressee() {
		return messageAddressee;
	}
	public void setMessageAddressee(String messageAddressee) {
		this.messageAddressee = messageAddressee;
	}
}
