package com.petlife.forum.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.petlife.forum.entity.Article;
import com.petlife.forum.entity.ArticleImg;
import com.petlife.forum.entity.Forum;
import com.petlife.forum.service.ArticleImgService;
import com.petlife.forum.service.ArticleService;
import com.petlife.forum.service.CommentService;
import com.petlife.forum.service.ForumService;
import com.petlife.forum.service.impl.ArticleImgServiceImpl;
import com.petlife.forum.service.impl.ArticleServiceImpl;
import com.petlife.forum.service.impl.ForumServiceImpl;
import com.petlife.pet.entity.PetPhoto;
import com.petlife.user.entity.User;
import com.petlife.user.service.UserServeice;
import com.petlife.user.service.impl.UserServiceImpl;
import com.petlife.forum.entity.Comment;
import com.petlife.forum.service.impl.CommentServiceImpl;

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
   forwardPath = getOneDisplay(req,res);
   break;
  case "getOne_For_Update":
   // 來自listAllCoupon.jsp
   forwardPath = getOneUpdate(req,res);
   break;
  case "update":
   // 來自update_coupon_input.jsp
   forwardPath = update(req,res);
   break;
   case "insert":
    //來自listAllCoupon.jsp
    forwardPath = insert(req, res);
    break;
   case "delete":
    // 來自listAllCoupon.jsp
    forwardPath = delete(req,res);
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
  String commentId = req.getParameter("commentId");
  String commentText = req.getParameter("commentText");
//  String articleContent = req.getParameter("articleContent");
//  String forumName = req.getParameter("forumName"); // 使用 forumName 而不是 forumId
  Integer forumName=1;
  if (commentText == null || commentText.trim().isEmpty()) {
      errorMsgs.add("留言不得為空");
  }
  if (articleContent == null || articleContent.trim().isEmpty()) {
      errorMsgs.add("文章內容不得為空");
  }
//  if (forumName == null || forumName.trim().isEmpty()) {
//      errorMsgs.add("請選擇論壇分類");
//  }

  Article article = null;
  Comment comment = null;
  
  Timestamp commentDatetime = null;
  try {
      comment = commentService.getCommentById(Integer.parseInt(commentId));
//      state = Boolean.valueOf(req.getParameter("state"));
  } catch (NumberFormatException e) {
      errorMsgs.add("留言編號、不正確");
  }
  

  // 檢查用戶是否登錄
  User user = (User) req.getSession().getAttribute("account");
  if (user == null) {
      errorMsgs.add("無法獲取用戶信息");
  }
  System.out.println(user.getUserId());

  // 查找對應的論壇類別
  // 查找對應的論壇類別
//  ForumService forumService = new ForumServiceImpl();
//  Forum forum = forumService.findForumBySortName(forumName);
//  if (forum == null) {
//      errorMsgs.add("找不到對應的論壇類別");
//  }
//  System.out.println(forumName);

  // 如果有錯誤，返回表單頁面
  if (!errorMsgs.isEmpty()) {
      req.setAttribute("comment", comment);
      return "/comment/update_comment_input.jsp";
  }

  // 更新文章對象並設置其屬性
  comment.setCommentText(commentText);
//  article.setArticleContent(articleContent);
//  article.setForum(forumName);
//  article.setState(state);
  // Send the use back to the form, if there were errors
  if (!errorMsgs.isEmpty()) {
   req.setAttribute("comment", comment); // 含有輸入格式錯誤的manage物件,也存入req
   return "/comment/update_comment_input.jsp"; // 程式中斷
  }

/*************************** 2.開始修改資料 *****************************************/
commentService.updateComment(comment);
req.setAttribute("comment", commentService.updateComment(comment));

/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
req.setAttribute("comment", comment); // 資料庫update成功後,正確的的manage物件,存入req
return "/comment/listAllcomment.jsp";
}

 
 /*************************** 1.開始新增 
 * @throws IOException 
 * @throws ServletException *************/
 private String insert(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//	 System.out.println("ArtServlet :  Entry");
	    // 錯誤處理
	    List<String> errorMsgs = new ArrayList<>();
	    req.setAttribute("errorMsgs", errorMsgs);

	    // 1.接收請求參數 - 輸入格式的錯誤處理
	    Article article = new Article();
	    String articleId = req.getParameter("articleId");
	    String articleName = req.getParameter("articleName");
	    System.out.println(articleName);
	    String articleContent = req.getParameter("articleContent");
	    System.out.println(articleContent);
//	    String forumName = req.getParameter("forumName"); // 使用 forumName 而不是 forumId
	    Integer forumName=1;
	    System.out.println(forumName);
	    if (articleName == null || articleName.trim().isEmpty()) {
	        errorMsgs.add("文章名稱不得為空");
	    }
	    if (articleContent == null || articleContent.trim().isEmpty()) {
	        errorMsgs.add("文章內容不得為空");
	    }
//	    if (forumName == null || forumName.trim().isEmpty()) {
//	        errorMsgs.add("請選擇論壇分類");
//	    }

	    // 如果有錯誤，返回表單頁面
	    if (!errorMsgs.isEmpty()) {
	        req.setAttribute("article", new Article());
	        return "/article/addArticle.jsp";
	    }

	    Integer ctr = 1000;
	    System.out.println(ctr);
	    Boolean state = true;
	    System.out.println(state);
	    System.out.println(req.getParameter("articlePhoto"));
	    try {
//	        ctr = Integer.valueOf(req.getParameter("ctr"));
//	        state = Boolean.valueOf(req.getParameter("state"));
	    } catch (NumberFormatException e) {
	        errorMsgs.add("計數器或狀態格式不正確");
	    } 
	    
//	    List<ArticleImg> articleList = new ArrayList<ArticleImg>();
	    
	 // ... 之前的代码 ...

	    

	    // ... 后续代码 ...

	    
	    
	    
	    
	    // 檢查用戶是否登錄
	    User user = (User) req.getSession().getAttribute("account");
	    if (user == null) {
	        errorMsgs.add("無法獲取用戶信息");
	    }
	    System.out.println(user.getUserId());

	    // 查找對應的論壇類別
//	    ForumService forumService = new ForumServiceImpl();
//	    Forum forum = forumService.findForumBySortName(forumName);
//	    if (forum == null) {
//	        errorMsgs.add("找不到對應的論壇類別");
//	    }
//	    System.out.println(forumName);

	    // 如果有錯誤，返回表單頁面
	    if (!errorMsgs.isEmpty()) {
	        req.setAttribute("article", new Article());
	        return "/article/addArticle.jsp";
	    }
	    
//	    Integer user= 100000005;
	    System.out.println(user);
	    // 建立文章對象並設置其屬性
//	    Article article = new Article();
	    article.setArticleName(articleName);
	    article.setArticleContent(articleContent);
	    article.setUser(user);
	    article.setForum(forumName);
	    article.setCtr(ctr);
	    article.setState(state);

	    // 2.開始新增資料
	    articleService.addArticle(article);

	    // 3.新增完成,準備轉交
	    return "/article/listAllArticle.jsp";
	}

 
// private String user() {
//	// TODO Auto-generated method stub
//	return null;
//}

private String delete (HttpServletRequest req, HttpServletResponse res) {
//    List<String> errorMsgs = new List<String>();
          // Store this set in the request scope, in case we need to
          // send the ErrorPage view.
//          req.setAttribute("errorMsgs", errorMsgs);
          
          /**1.接收請求參數**/
          Integer articleId = Integer.valueOf(req.getParameter("articleId"));

          /**2.開始刪除資料**/
          articleService.deleteArticle(articleId);

          /**3.刪除完成,準備轉交(Send the Success view)**/
          return "/article/listAllarticle.jsp";

 }
 
 
  
 
}
