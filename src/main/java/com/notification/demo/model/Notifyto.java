package com.notification.demo.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Notifyto {
	private String notificationType;
	private String processType;
	private LocalDateTime ScheduledTime;
	private Integer notificationTemplateId;
//	@OneToMany
//	private List<EmailObj> []otherCustomizableObject;
	private String []attachFile;
	private String []ccto;
	private String []bccto;
	@OneToMany
	private List<User> notifyto = new ArrayList<>();
	
}