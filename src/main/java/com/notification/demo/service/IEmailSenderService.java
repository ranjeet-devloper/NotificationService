package com.notification.demo.service;

import com.notification.demo.model.common.SuccessResponseModel;

public interface IEmailSenderService {
	public SuccessResponseModel sendEmail(String toMail,String subject,String body);
}