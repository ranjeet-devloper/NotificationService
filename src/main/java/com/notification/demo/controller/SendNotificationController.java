package com.notification.demo.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.demo.exception.EmailException;
import com.notification.demo.model.NotificationTemplate;
import com.notification.demo.model.Notifyto;
import com.notification.demo.model.common.ErrorResponseModel;
import com.notification.demo.model.common.SuccessResponseModel;
import com.notification.demo.service.impl.EmailSenderServiceimpl;
import com.notification.demo.service.impl.NotificationTemplateServiceImpl;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v0.0.1/otp")
public class SendNotificationController {

	@Autowired
	private EmailSenderServiceimpl emailSenderServiceimpl;

	@Autowired
	NotificationTemplateServiceImpl notificationTemplateServiceImpl;

	@PostMapping("/sendemailnotification")
	public ResponseEntity<SuccessResponseModel> sendNotificationviamail(@RequestBody Notifyto user) {
		
		log.info(user.getNotifyto().toString());
		Integer templateid = user.getNotificationTemplateId();

		NotificationTemplate template = notificationTemplateServiceImpl.getTemplate(templateid);
		log.info(template.getSubject());
		user.getNotifyto().forEach(x -> {
				try {
					  emailSenderServiceimpl.sendEmail(x.getEmail(),
							template.getSubject(),
							template.getMessageBody().toString(),
							user.getCcto(),user.getBccto(),user.getAttachFile());
					
				} catch (MessagingException e) {
					throw new EmailException("Internal Server Error");
				}
			
		});
		SuccessResponseModel response = SuccessResponseModel.builder().status(HttpStatus.ACCEPTED.value())
				.messsage("The Email-notification is generated").templateId(templateid).build();
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

}