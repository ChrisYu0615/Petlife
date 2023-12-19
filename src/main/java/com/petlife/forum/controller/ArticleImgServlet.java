package com.petlife.forum.controller;

import java.io.IOException;
import java.io.InputStream;
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
import com.petlife.forum.service.ForumService;
import com.petlife.forum.service.impl.ArticleImgServiceImpl;
import com.petlife.forum.service.impl.ArticleServiceImpl;
import com.petlife.forum.service.impl.ForumServiceImpl;
import com.petlife.user.entity.User;

@WebServlet("/articleImg/articleImg.do")
@MultipartConfig
public class ArticleImgServlet extends HttpServlet {
 // 一個 servlet 實體對應一個 service 實體
 private ArticleImgService articleImgService;

 @Override
 public void init() throws ServletException {
  articleImgService = new ArticleImgServiceImpl();
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
    forwardPath = "/select_page.jsp";
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
String str = req.getParameter("articleId");

  if (str == null || (str.trim()).length() == 0) {
   errorMsgs.add("請輸入文章編號");
  }
  // Send the use back to the form, if there were errors
  if (!errorMsgs.isEmpty()) {
   return "/article/select_page.jsp";// 程式中斷
  }

  Integer articleId = null;
  try {
	  articleId = Integer.valueOf(str);
  } catch (Exception e) {
   errorMsgs.add("文章編號格式不正確");
  }
  // Send the use back to the form, if there were errors
  if (!errorMsgs.isEmpty()) {
   return "/articleImg/select_page.jsp";// 程式中斷
  }

/*************************** 2.開始查詢資料 *****************************************/
//  CouponService coupoService = new CouponService();
  articleImgService = new ArticleImgServiceImpl();
  ArticleImg article = articleImgService.getArticleImgById(articleId);
//  System.out.println("你在哪"+article);

  if (article == null) {
   errorMsgs.add("查無資料");
  }
  // Send the use back to the form, if there were errors
  if (!errorMsgs.isEmpty()) {
   return "/article/select_page.jsp";// 程式中斷
  }
  
/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
req.setAttribute("article", article); // 資料庫取出的manage物件,存入req
return "/article/listOneArticle.jsp";
}
 
 private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
	 Integer articleImgId = Integer.valueOf(req.getParameter("articleImgId"));
  ArticleImg articleImg = articleImgService.getArticleImgById(articleImgId);

  req.setAttribute("articleImg", articleImg);
  return "/article/update_ArtImg_input.jsp";
 }
 
 private String update(HttpServletRequest req, HttpServletResponse res) {
  // 錯誤處理
  List<String> errorMsgs = new ArrayList<>();
  req.setAttribute("errorMsgs", errorMsgs);

/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
  String articleImg = req.getParameter("articleImgId");
  

  // 檢查用戶是否登錄
  User user = (User) req.getSession().getAttribute("account");
  if (user == null) {
      errorMsgs.add("無法獲取用戶信息");
  }

  // 查找對應的論壇類別
  ForumService forumService = new ForumServiceImpl();
  List<Forum> forums = forumService.getAllForums();
  req.setAttribute("forums", forums);
  if (forums == null) {
      errorMsgs.add("找不到對應的論壇類別");
  }

  // 如果有錯誤，返回表單頁面
  if (!errorMsgs.isEmpty()) {
      req.setAttribute("article", article);
      return "/article/update_Article_Input.jsp";
  }

  // 更新文章對象並設置其屬性
  article.setArticleName(articleName);
  article.setArticleContent(articleContent);
//  article.setForum(forum);
  article.setState(state);
  // Send the use back to the form, if there were errors
  if (!errorMsgs.isEmpty()) {
   req.setAttribute("article", article); // 含有輸入格式錯誤的manage物件,也存入req
   return "/article/update_article_input.jsp"; // 程式中斷
  }

/*************************** 2.開始修改資料 *****************************************/
articleImgService.updateArticle(articleImg);
req.setAttribute("article", articleService.updateArticle(article));

/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
req.setAttribute("article", article); // 資料庫update成功後,正確的的manage物件,存入req
return "/article/listAllArticleImg.jsp";
}

 
 /*************************** 1.開始新增 *************/
 private String insert(HttpServletRequest req, HttpServletResponse res) {
	    // 錯誤處理
	    List<String> errorMsgs = new ArrayList<>();
	    req.setAttribute("errorMsgs", errorMsgs);

	    // 接收請求參數
	    String articleIdStr = req.getParameter("articleId");

	    // 輸入格式的錯誤處理
	    if (articleIdStr == null || articleIdStr.trim().isEmpty()) {
	        errorMsgs.add("文章ID不能为空");
	    }

	    Integer articleId = Integer.parseInt(articleIdStr);

	    // 查找 Article 实体
	    ArticleService articleService = new ArticleServiceImpl(); // 假设你已有这个服务
	    Article article = articleService.getArticleByArticleId(articleId);
	    if (article == null) {
	        errorMsgs.add("找不到對應的文章");
	        req.setAttribute("articleImg", new ArticleImg());
	        return "/articleImg/addArticleImg.jsp";
	    }

	    // 处理图片文件
	    byte[] upFiles = null;
	    try {
	        InputStream in = req.getPart("upFiles").getInputStream();
	        if (in.available() != 0) {
	            upFiles = new byte[in.available()];
	            in.read(upFiles);
	            in.close();
	        } else {
	            errorMsgs.add("文章圖片: 请上傳照片");
	        }
	    } catch (IOException | ServletException e) {
	        errorMsgs.add("图片上传失败: " + e.getMessage());
	    }

	    // 如果有错误，返回表单页面
	    if (!errorMsgs.isEmpty()) {
	        req.setAttribute("articleImg", new ArticleImg());
	        return "/articleImg/addArticleImg.jsp";
	    }

	    
	    
	    
	    // 创建 ArticleImg 对象并设置其属性
	    ArticleImg articleImg = new ArticleImg();
	    articleImg.setArticle(article); // 设置关联的 Article 实体
	    articleImg.setArticleImg(upFiles); // 设置图片的字节数组

	    // 这里假设你已经有了 ArticleImgService 的实现
	    ArticleImgService articleImgService = new ArticleImgServiceImpl();
	    
	    // 开始新增数据
	    articleImgService.addArticleImg(articleImg);

	    // 新增完成，准备转交
	    return "/articleImg/listAllArticleImg.jsp";
	}





 
// private String user() {
//	// TODO Auto-generated method stub
//	return null;
//}

private String saveUploadedFile(Part articleImgPart) {
	// TODO Auto-generated method stub
	return null;
}

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
