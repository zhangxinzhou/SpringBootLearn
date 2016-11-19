package com.example;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;



@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBoot17JavaMailSenderApplicationTests {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Configuration configuration;

	
	//@Test
	public void contextLoads() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("username@163.com");             //改成自己的邮箱,还有application.properties也是,建议使用163的,qq的尝试过但是失败了
		message.setTo("username@163.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容"+new Date());
		
		mailSender.send(message);
	}
	
	//@Test
	public void sendAttachmentsMail() throws Exception{
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("username@163.com");
		helper.setTo("username@163.com");
		helper.setSubject("主题：有附件");
		helper.setText("有附件的邮件"+new Date());
		
		//注意:这个路径也要改成自己的图片路径
		FileSystemResource file=new FileSystemResource(new File("C:\\Users\\user\\Desktop\\logo2.png"));
		helper.addAttachment("附件-1.jpg", file);
		helper.addAttachment("附件-2.jpg", file);
		
		mailSender.send(mimeMessage);
	}
	
	
	//@Test
	public void sendInlineMail() throws Exception{
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("username@163.com");
		helper.setTo("username@163.com");
		helper.setSubject("主题：嵌入静态资源");
		//helper.setText("<html><body><h1>静态邮件"+new Date()+"</h1></body></html>",true);
		helper.setText("<html><body><img src=\"cid:pic\" ></body></html>", true);
		
		//注意:这个路径也要改成自己的图片路径
		FileSystemResource file=new FileSystemResource(new File("C:\\Users\\user\\Desktop\\logo2.png"));   
		helper.addInline("pic", file);
		
		mailSender.send(mimeMessage);
	}
	
	
	
	@Test   //用velocity会报错,这里改为使用freemarker,测试通过
	public void sendTemplateMail() throws Exception {
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("username@163.com");
		helper.setTo("username@163.com");
		helper.setSubject("主题：模板邮件");
		
		Map<String, Object> model=new HashMap<>();
		model.put("username", "zxz");
		Template t=configuration.getTemplate("template.fm");
		String content=FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
		helper.setText(content,true);
		mailSender.send(mimeMessage);
	}

}
