package com.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailApplicationTests {

	@Autowired
	JavaMailSenderImpl mailSender;

	@Test
	public void contextLoads() {
		//简单邮件
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		//邮件设置
		//标题
		mailMessage.setSubject("通知-今晚开会");
		//内容
		mailMessage.setText("今晚7点30开会");
		//收件人
		mailMessage.setTo("收件人");
		//发件人
		mailMessage.setFrom("发件人");
		//发送
		mailSender.send(mailMessage);
	}


	@Test
	public void test() throws MessagingException {
		//1.创建复杂消息邮件
		MimeMessage message = mailSender.createMimeMessage();
		//传入邮件对象   1.对象 2.是否支持上传附件
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		//邮件设置
		helper.setSubject("通知-今晚开会");
		//邮件内容   1.邮件内容	2.是否支持html
		helper.setText("<b style='color:red'>开会</b>", true);
		helper.setTo("收件人");
		helper.setFrom("发件人");

		//上传附件
		helper.addAttachment("1.png", new File("D:\\test\\1.png"));

		mailSender.send(message);
	}

}
