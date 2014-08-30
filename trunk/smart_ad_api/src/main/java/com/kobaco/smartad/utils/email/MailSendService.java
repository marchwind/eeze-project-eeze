package com.kobaco.smartad.utils.email;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kobaco.smartad.model.service.MailSend;


public class MailSendService  {
	
	@Inject  private JavaMailSender mailSender; 
	@Inject  private VelocityEngine velocityEngine;
	public void mailSend (final MailSend mailDto) {

		final MimeMessagePreparator prepator = new MimeMessagePreparator() {
			@Override
			public void prepare(final MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub	

				//String[] Receiver = {"hjs6877@siksco.co.kr", "edonay@siksco.co.kr"};  
				//message.setTo(Receiver); 
				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setFrom(mailDto.getSendFrom());
				message.setTo(mailDto.getSendTo());
				message.setSubject(mailDto.getSendSubject());
				final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, mailDto.getSendPath(), mailDto.getSendMessage());
				message.setText(text,true);				
			}
		};		
		
		this.mailSender.send(prepator);
		
	}

}
