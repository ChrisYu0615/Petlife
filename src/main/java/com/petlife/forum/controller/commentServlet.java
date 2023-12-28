package com.petlife.forum.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petlife.forum.entity.Article;
import com.petlife.forum.entity.Comment;
import com.petlife.forum.service.ArticleService;
import com.petlife.forum.service.CommentService;
import com.petlife.forum.service.impl.ArticleServiceImpl;
import com.petlife.forum.service.impl.CommentServiceImpl;
import com.petlife.user.entity.User;

@WebServlet("/comment/comment.do")
@MultipartConfig
public class commentServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private CommentService commentService;
// private ArticleService articleService;

	@Override
	public void init() throws ServletException {
		commentService = new CommentServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "getOne_For_Display":
			// 來自selact_page.jsp
			forwardPath = getOneDisplay(req, res);
			break;
		case "getOne_For_Update":
			// 來自listAllCoupon.jsp
			forwardPath = getOneUpdate(req, res);
			break;
		case "update":
			// 來自update_coupon_input.jsp
			forwardPath = update(req, res);
			break;
		case "insert":
			// 來自listAllCoupon.jsp
			forwardPath = insert(req, res);
			break;
		case "delete":
			// 來自listAllCoupon.jsp
			forwardPath = delete(req, res);
			break;
		default:
			forwardPath = "/comment/listAllComment.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

	}

	private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

		String str = req.getParameter("commentId");

		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.add("請輸入留言編號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/comment/select_page.jsp";// 程式中斷
		}

		Integer commentId = null;
		try {
			commentId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("留言編號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/comment/select_page.jsp";// 程式中斷
		}

		/*************************** 2.開始查詢資料 *****************************************/
//  CouponService coupoService = new CouponService();
		commentService = new CommentServiceImpl();
		Comment comment = commentService.getCommentById(commentId);

//  articleImgService = new ArticleImgServiceImpl();
//  ArticleImg articleImg = articleImgService.getArticleImgByImgId(articleId);
//  System.out.println("你在哪"+article);

		if (comment == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/comment/select_page.jsp";// 程式中斷
		}

		/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

		req.setAttribute("comment", comment); // 資料庫取出的manage物件,存入req
		return "/comment/listOneComment.jsp";
	}

	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
		Integer commentId = Integer.valueOf(req.getParameter("commentId"));
		Comment comment = commentService.getCommentById(commentId);
		String commentText = req.getParameter("commentText");
//  Integer articleImgId = Integer.valueOf(req.getParameter("articleImgId"));
//  ArticleImg articleImg = articleImgService.getArticleImgByImgId(articleImgId);

//  req.setAttribute("articleImg", articleImg);

		req.setAttribute("comment", comment);
		return "/comment/update_article_input.jsp";
	}

	/**************************************************************************/
	private String update(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		Integer commentId = null;
//  Comment comment = commentService.getCommentById(commentId);
		String commentText = req.getParameter("commentText");
		Integer articleId = null;

		try {
			commentId = Integer.parseInt(req.getParameter("commentId"));
			articleId = Integer.parseInt(req.getParameter("articleId"));
		} catch (NumberFormatException e) {
			errorMsgs.add("格式不正確");
			return "/comment/update_comment_input.jsp";
		}

		// 從 session 中獲取當前用戶
		User user = (User) req.getSession().getAttribute("account");
		if (user == null) {
			errorMsgs.add("無法獲取用戶信息");
			return "/comment/update_comment_input.jsp";
		}

		// 查找對應的文章實體
		ArticleService articleService = new ArticleServiceImpl();
		Article article = articleService.getArticleByArticleId(articleId);
		if (article == null) {
			errorMsgs.add("找不到對應的文章");
			return "/comment/update_comment_input.jsp";
		}

		// 更新留言資訊
		Comment comment = new Comment();
		comment.setCommentId(commentId);
		comment.setUser(user);
		comment.setArticle(article);
		comment.setCommentText(commentText);

		req.setAttribute("comment", comment);

//*************************** 2.開始修改資料 *****************************************/
//  CommentService commentService = new CommentServiceImpl();
		commentService.updateComment(comment);

		return "/comment/listOneComment.jsp";
	}

	/***************************
	 * 1.開始新增
	 * 
	 * @throws IOException
	 * @throws ServletException
	 *************/
	private String insert(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//	 System.out.println("ArtServlet :  Entry");
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

		// Comment comment = commentService.getCommentById(commentId);
		String commentText = req.getParameter("commentText");
//	 System.out.println(commentText);

		Integer articleId = null;

		try {
			articleId = Integer.parseInt(req.getParameter("articleId"));
//	      System.out.println(articleId);
		} catch (NumberFormatException e) {
			errorMsgs.add("格式不正確");
			return "/article/spec-blog.jsp";
		}

		// 從 session 中獲取當前用戶
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			errorMsgs.add("無法獲取用戶信息");
			return "/article/spec-blog.jsp";
		}

		// 查找對應的文章實體
		ArticleService articleService = new ArticleServiceImpl();
		Article article = articleService.getArticleByArticleId(articleId);
		if (article == null) {
			errorMsgs.add("找不到對應的文章");
			return "/article/spec-blog.jsp";
		}

		// 新增留言資訊
		Comment comment = new Comment();
//	  comment.setCommentId(commentId);
		comment.setUser(user);
		comment.setArticle(article);
		comment.setCommentText(commentText);

		// 2.開始新增資料
		commentService.addComment(comment);

		req.setAttribute("article", article);
		// 3.新增完成,準備轉交
		return "/article/spec-blog.jsp";
	}

// private String user() {
//	// TODO Auto-generated method stub
//	return null;
//}

	private String delete(HttpServletRequest req, HttpServletResponse res) {
//    List<String> errorMsgs = new List<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
//          req.setAttribute("errorMsgs", errorMsgs);

		/** 1.接收請求參數 **/
		Integer commentId = Integer.valueOf(req.getParameter("commentId"));
		Comment comment = commentService.getCommentById(commentId);
		Article article = comment.getArticle();
		/** 2.開始刪除資料 **/
		commentService.deleteComment(commentId);
		req.setAttribute("article", article);
		/** 3.刪除完成,準備轉交(Send the Success view) **/
		return "/article/spec-blog.jsp";

	}

}
