package com.cmdi.service.impl;

import java.io.File;
import java.util.HashMap;

import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cmdi.dao.GroupInfoDao;
import com.cmdi.model.GlobalVar;
import com.cmdi.model.GroupInfo;
import com.cmdi.service.GroupInfoService;
import com.cmdi.util.qrcode.QRCodeUtil;

/**
 * 测试Service
 * 
 * @author: 高宗宝
 * @Description:事务管理需要注意数据引擎，异常类型为RuntimeException
 */
@Service

public class GroupInfoServiceImpl implements GroupInfoService {
	
	private static Logger logger = Logger.getLogger(GroupInfoServiceImpl.class);
	@Autowired
	private GroupInfoDao groupInfoDao;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
//	
//
	@Autowired
	private GlobalVar globalVar;
//	


	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	//尽量不要使用一个启用事务的方法，调用同一个service中另外一个启动事务的方法，可能会产生自调用问题，引起事务失效
	//在事务方法中如果使用try catch，需要手动抛出RuntimeException让spring捕捉这个异常才可以事务有效
	public GroupInfo get(String id) throws Exception {
		// TODO Auto-generated method stub
		return groupInfoDao.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public void sendemail(GroupInfo selectByPrimaryKey) throws Exception {
		// TODO Auto-generated method stub
		String PERSON_HEALTH_ADDRESS_PRE = globalVar.Base_Url_Path + "/HealthReporting/user?groupId=";
		String RES_ADDRESS_PRE =           globalVar.Base_Url_Path + "/HealthReporting/admin?groupId=";
		//生成上报二维码并本地存储
    	String url = PERSON_HEALTH_ADDRESS_PRE + selectByPrimaryKey.getId();
    	String path = globalVar.LOCAL_P_PATH + File.separator + "p-" + selectByPrimaryKey.getId() + ".png";
    	QRCodeUtil.encode(url, path);
    	//生成统计二维码并本地存储
    	String url2 = RES_ADDRESS_PRE + selectByPrimaryKey.getId();
    	String path2 = globalVar.LOCAL_R_PATH + File.separator + "r-" + selectByPrimaryKey.getId() + ".png";
    	QRCodeUtil.encode(url2, path2);
    	//邮件发送
    	MimeMessage message = mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(mailSender.getJavaMailProperties().getProperty("mail.smtp.from"));
		if(selectByPrimaryKey == null || selectByPrimaryKey.getEmail() == null) {
			throw new RuntimeException("邮箱错误");
		}
		helper.setTo(selectByPrimaryKey.getEmail());
		helper.setSubject("疫情防控-健康上报系统");
		//String mailContent = mailContent(selectByPrimaryKey.getName(), selectByPrimaryKey.getContactPerson(), url, url2, mailSender.getJavaMailProperties().getProperty("mail.smtp.from"));
		String mailContent = mailContent(selectByPrimaryKey.getName(), selectByPrimaryKey.getContactPerson(), url, url2, selectByPrimaryKey.getPhoneNumber());
		helper.setText(mailContent,true);
		FileSystemResource img = new FileSystemResource(new File(path));
		FileSystemResource img2 = new FileSystemResource(new File(path2));
		helper.addInline("url1", img);
		helper.addInline("url2", img2);
		try {
			mailSender.send(message);
			selectByPrimaryKey.setStatus(0);
			groupInfoDao.updateByPrimaryKey(selectByPrimaryKey);
			logger.info("邮件发送成功 to->" + selectByPrimaryKey.getEmail());
		} catch (MailAuthenticationException e) {
			// TODO: handle exception
			System.out.println("配置错误");
			throw new RuntimeException("conferror");
		} catch (MailException es) {
			// TODO: handle exception
			System.out.println("发送错误");
			logger.info("邮件失败 to->" + selectByPrimaryKey.getEmail());
			throw new RuntimeException("senderror");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("错误?");
			throw new RuntimeException("err");
		}
	}
	
	private String mailContent(String company, String username, String url1, String url2, String contact) {
		StringBuilder builder = new StringBuilder();
		builder.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" </head><body>");
		builder.append("<h5>感谢您使用疫情防控-健康上报系统。如非您本人【" + username + "，"+ company +"， " + contact +  "】操作，请忽略该邮件。请勿回复本邮件。</h5> <br/>");
		builder.append("员工个人信息填报网址为：" + url1 + " <br/>");
		builder.append("<img src=\"cid:url1\" /> <br/>");
		
		builder.append("统计结果查看网址为：" + url2 + " <br/>");
		builder.append("直接登录不需要密码，请您妥善保存此链接，切勿公开，使用IE10以上浏览器或者谷歌浏览器打开下载<br/>");
		builder.append("<img src=\"cid:url2\" /> <br/>");
		//builder.append("<h5> <font color=\"#FF0000\">------->仅能查看前一天的统计数据<------- </font> </h5> <br/>");
		//builder.append("如有疑问，请联系" + contact);
		builder.append("如有疑问，请联系：电话15063362268，邮箱dingna1@139.com");
		builder.append("</body></html>");
		return builder.toString();
	}

	@Override
	public void update(GroupInfo id) throws Exception {
		// TODO Auto-generated method stub
		groupInfoDao.updateByPrimaryKey(id);
	}

}
