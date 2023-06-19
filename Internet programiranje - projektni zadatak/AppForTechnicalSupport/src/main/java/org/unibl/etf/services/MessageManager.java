package org.unibl.etf.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.unibl.etf.beans.MessageBean;
import org.unibl.etf.dao.MessageDAO;

public class MessageManager {
	
	private List<MessageBean> messages = new ArrayList<>();

	public MessageManager() {
		// TODO Auto-generated constructor stub
	}
	
	public List<MessageBean> getAllMessages(String search) {
		messages = MessageDAO.getMessages();
		if(search==null || "".equals(search)) {
			return messages;
		}
		List<MessageBean> messagesWithSearch = new ArrayList<>();
		messagesWithSearch = messages.stream().filter(m -> m.getContent().contains(search)).toList();
		return messagesWithSearch;
	}
	
	public MessageBean seeMessage(int id) {
		MessageDAO.makeAsRead(id);
		return getMessageById(id);
	}
	
	public MessageBean getMessageById(int id) {
		messages = getAllMessages(null);
		for (MessageBean messageBean : messages) {
			if(messageBean.getId()==id) {
				return messageBean;
			}
		}
		return null;
	}
	
	private static Message prepareMessage(Session session,String from,String to,String text, String title) {
	    Message message = new MimeMessage(session);
	    try {
	        
	        message.setFrom(new InternetAddress(from));
	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        message.setSubject(title);
	        message.setText(text);
	        return message;
	    } catch (AddressException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public void replyMessage(int id, String replyContent) {
		String emailRecipient = MessageDAO.findSendersEmail(id);
		Properties properties=new Properties();
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    properties.put("mail.smtp.starttls.required", "true");
	    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
	    String myAccountEmail="webshop.ip1@gmail.com";
	    String password="yvpavetaozlkceak";
	    Session session=Session.getInstance(properties, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(myAccountEmail, password);
	        }
	    });
	    String title = "ETFBL_IP - Odgovor tehnicke podrske";
	    Message message=prepareMessage(session,myAccountEmail,emailRecipient,replyContent,title);
	    try {
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
