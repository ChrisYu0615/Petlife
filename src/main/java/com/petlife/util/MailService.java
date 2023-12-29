package com.petlife.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.petlife.forum.entity.Article;

public class MailService {

	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	private void sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●1) 登入你的Gmail的:
			// ●2) 點選【管理你的 Google 帳戶】
			// ●3) 點選左側的【安全性】

			// ●4) 完成【兩步驟驗證】的所有要求如下:
			// ●4-1) (請自行依照步驟要求操作之.....)

			// ●5) 完成【應用程式密碼】的所有要求如下:
			// ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
			// ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
			// ●5-3) 最後按【產生】密碼
			final String myGmail = "ixlogic.wu@gmail.com";
			final String myGmail_password = "ddjomltcnypgcstn";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

	// 發送驗證碼信
	public static void sendAuthenCode(String account, String authenCode) {
		// 主旨
		String subject = "寵愛生活-驗證碼通知";
		// 內容
		String messageText = "您好，驗證碼如下：" + authenCode + "，請於10分鐘內輸入!!";

		MailService mailService = new MailService();
		mailService.sendMail(account, subject, messageText);
	}

	// 發送註冊成功信(一般會員)
	public static void memberRegisterSuccess(String account) {
		// 主旨
		String subject = "寵愛生活-註冊會員通知";
		// 內容
		String messageText = "您已完成註冊會員程序，歡迎加入並成為我們的會員，登入後即可享受各種服務!!";

		MailService mailService = new MailService();
		mailService.sendMail(account, subject, messageText);
	}

	// 發送註冊成功信(賣家/收容所會員)
	public static void registerSuccess(String account) {
		// 主旨
		String subject = "寵愛生活-註冊會員通知";
		// 內容
		String messageText = "您已完成註冊會員程序，歡迎加入並成為我們的會員，待管理員審核您的資格後即可享受各種服務!!" + "\n" + "如有疑問都可以寄信向我們反映，謝謝!!";

		MailService mailService = new MailService();
		mailService.sendMail(account, subject, messageText);
	}

	// 發送新密碼信
	public static void getNewPassword(String account, String newPassword) {
		// 主旨
		String subject = "寵愛生活-密碼變更通知";
		// 內容
		String messageText = "Hello! " + account + "\n" + "請謹記此密碼: " + newPassword + "\n" + "請於登入後盡速變更，謝謝!!";

		MailService mailService = new MailService();
		mailService.sendMail(account, subject, messageText);
	}

	// 發送驗證成功信(賣家/收容所會員)
	public static void verifySuccess(String account) {
		// 主旨
		String subject = "寵愛生活-驗證會員通知";
		// 內容
		String messageText = "您的帳戶已經管理員驗證後並啟用，於登入後即可開始體驗網站服務!!";

		MailService mailService = new MailService();
		mailService.sendMail(account, subject, messageText);
	}

	// 發送驗證失敗成功信(賣家/收容所會員)
	public static void verifyfailed(String account, String failedMsg) {
		// 主旨
		String subject = "寵愛生活-驗證會員通知";
		// 內容
		String messageText = "您的帳戶因管理員認定不符，已被取消註冊，如有問題可以向管理員聯繫，謝謝!!" + "\n" + "以下為原因：" + "\n" + failedMsg;

		MailService mailService = new MailService();
		mailService.sendMail(account, subject, messageText);
	}

	// 訂單被取消(發給賣家)
	public static void cancelBuylist(Integer buylistId, String account, String cancelReason) {
		// 主旨
		String subject = "寵愛生活-訂單取消通知";
		// 內容
		String messageText = "您的訂單(編號" + buylistId + ")已被取消" + "\n" + "以下為原因：" + "\n" + cancelReason;

		MailService mailService = new MailService();
		mailService.sendMail(account, subject, messageText);
	}

	// 回覆檢舉訊息
	public static void replyReportMsg(Article article, String account, String replyMsg) {
		// 主旨
		String subject = "寵愛生活-檢舉文章回覆";
		// 內容
		String messageText = "您所檢舉的文章--" + article.getArticleName() + "(編號" + article.getArticleId() + ")已經管理員查證過後"
				+ "\n" + "回覆如下：" + "\n" + replyMsg + "\n" + "如有其他問題可寄信與我們聯繫!!";

		MailService mailService = new MailService();
		mailService.sendMail(account, subject, messageText);
	}

}
