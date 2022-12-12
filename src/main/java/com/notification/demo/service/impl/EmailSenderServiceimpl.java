package com.notification.demo.service.impl;

import java.io.File;
import java.net.http.HttpRequest;
import java.nio.file.FileSystem;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.notification.demo.model.common.SuccessResponseModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailSenderServiceimpl {

	@Autowired
	private JavaMailSender mailsender;

	public void sendEmail(String toMail, String subject, String body, String[] cclist, String[] bcclist, String[] file) throws MessagingException {
		
			MimeMessage mimeMessage = mailsender.createMimeMessage();
			MimeMessageHelper mimemessagehelper = new MimeMessageHelper(mimeMessage, true);
			mimemessagehelper.setFrom("ranjeetkumaryadvv@gmail.com");
			mimemessagehelper.setTo(toMail);
			mimemessagehelper.setCc(cclist);
			mimemessagehelper.setBcc(bcclist);
			mimemessagehelper.setText(body);
			mimemessagehelper.setSubject(subject);
			for(int i=0;i<file.length;i++) {
			FileSystemResource filesystemResorce = new FileSystemResource(new File(file[i]));
			mimemessagehelper.addAttachment(filesystemResorce.getFilename(), filesystemResorce);
			}
			mailsender.send(mimeMessage);	
		
	}

}
