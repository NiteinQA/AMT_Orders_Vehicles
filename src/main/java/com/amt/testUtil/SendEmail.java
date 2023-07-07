package com.amt.testUtil;

import com.amt.testBase.TestBase;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;

import org.apache.commons.mail.*;

public class SendEmail extends TestBase

{
	
	public static void main(String[] args) throws EmailException {
	
		//SendEmail.send_email();
		// System.out.println(SendEmail.removeText("£ 1,511.88 inc. VAT"));
		
	}
	
	    public static String removeText(String input) {
	        String output = input.replace("ex. VAT", "").replace("inc. VAT", "");
	        return output;
	    }
	
	

	public static void send_email() throws EmailException 
	{

System.out.println("Email sending process started");

  // Create the email message
  String host = "mail.thegatewaydigital.com";
  String from = "nitein.kurane@autofacets.in";
  Properties props = System.getProperties();
  props.put("mail.smtp.host", host);
  props.put("mail.smtp.user", "nitein.kurane@autofacets.in");
  props.put("mail.smtp.password", "Nik989%&nhrl4");
  props.put("spring.mail.port", "2525");
  props.put("mail.smtp.ssl.enable","false");
  props.put("mail.smtp.starttls.enable","false");
  props.put("mail.smtp.auth", "true");

  
  
  //spring.mail.port
  
  try{
      Session session = Session.getDefaultInstance(props, null);
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress("nitein.kurane@autofacets.in"));
      message.addRecipients(Message.RecipientType.TO, "Nitein.Kurane@thegatewaycorp.co.in");
      message.addRecipients(Message.RecipientType.TO, "Nitein.Kurane@autofacets.in");
      message.setSubject("Test Automation Report");
      Transport transport = session.getTransport("smtp");
      transport.connect("mail.thegatewaydigital.com", "Nitein.Kurane@autofacets.in", "Nik989%&nhrl4");//CAUSES EXCEPTION 
      BodyPart messageBodyPart = new MimeBodyPart();

     
      messageBodyPart.setText("Hello Team ,This is Automation report generated after executing test suite, Please find the attachment");

      // Create a multipar message
      Multipart multipart = new MimeMultipart();

      // Set text message part
      multipart.addBodyPart(messageBodyPart);

      // Part two is attachment
      messageBodyPart = new MimeBodyPart();
      
//      String[] attachmentFiles = new  String[2]; 
      String filename1  = System.getProperty("user.dir")+"/ExtentReport/ExtentReport.html";
      //String filename2 = System.getProperty("user.dir")+"/target/surefire-reports/html/index.html";
      String filename2 = System.getProperty("user.dir")+"/test-output/emailable-report.html";

      MimeBodyPart attachmentBodyPart1 = new MimeBodyPart();
      attachmentBodyPart1.attachFile(filename1);
      multipart.addBodyPart(attachmentBodyPart1);

      // Attach file 2
      MimeBodyPart attachmentBodyPart2 = new MimeBodyPart();
      attachmentBodyPart2.attachFile(filename2);
      multipart.addBodyPart(attachmentBodyPart2);
      
     
      message.setContent(multipart);
      transport.sendMessage(message, message.getAllRecipients());
	  	  
      System.out.println("Email sent successfully");
	   
  }catch(Exception e){
      e.printStackTrace();
  }
}
		
}


