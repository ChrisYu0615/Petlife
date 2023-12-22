package com.petlife.forum.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petlife.forum.entity.Article;
import com.petlife.forum.entity.ArticleImg;
import com.petlife.forum.service.ArticleService;
import com.petlife.forum.service.ArticleImgService;
import com.petlife.forum.service.impl.ArticleImgServiceImpl;
import com.petlife.forum.service.impl.ArticleServiceImpl;

@WebServlet("/art/art.do")
@MultipartConfig
public class ArticleServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private ArticleService articleService;
	private ArticleImgService articleImgService;

	@Override
	public void init() throws ServletException {
		articleService = new ArticleServiceImpl();
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
		res.setContentType("text/html; charset=UTF-8");
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
		case "getAllArticles":
			forwardPath = getAllArticles(req, res);
			break;
		case "CompositeArticleQuery":
			forwardPath = CompositeArticleQuery(req, res);
			break;
		case "modifyArticleState":
			forwardPath = modifyArticleState(req, res);
			break;
		case "getArticleById":
			forwardPath = getArticleById(req, res);
			break;
		case "getArticleImgById":
			getArticleImgById(req, res);
			break;
		default:
			forwardPath = "";
			break;
		}

		if (!forwardPath.isEmpty()) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}

	private void getArticleImgById(HttpServletRequest req, HttpServletResponse res) {
		Integer articleId = Integer.valueOf(req.getParameter("articleId")); // 先拿到他的ID
		ArticleImg articleImg = articleImgService.getArticleImgById(articleId); // 再透過articleId去articleService叫用getArticleByArticleId方法拿到Article的資料
		byte[] artImg;
		System.out.println(articleImg);
		FileInputStream fis = null;

		ServletOutputStream out = null;
		if (articleImg == null) {
			artImg = null;
		} else {
			artImg = articleImg.getArticleImg();
		}

		System.out.println(artImg);
		res.setContentType("image/png");
		if (artImg != null && artImg.length > 0) {
			try {
				out = res.getOutputStream();
				out.write(artImg);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				File file = new File(req.getServletContext().getRealPath("/dist/img/login_user1.png"));
				fis = new FileInputStream(file);
				byte[] buf = fis.readAllBytes();
				out = res.getOutputStream();
				out.write(buf);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					if (out != null) {
						out.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
//		req.setAttribute("articleImg", articleImg); //這裡會對應到 spec-blog的 <%Article article = (Article)request.getAttribute("article");%> 來設置他的值  注意名稱要對應到

	}

	private String getArticleById(HttpServletRequest req, HttpServletResponse res) {
		Integer articleId = Integer.valueOf(req.getParameter("articleId")); // 先拿到他的ID
		Article article = articleService.getArticleByArticleId(articleId); // 再透過articleId去articleService叫用getArticleByArticleId方法拿到Article的資料
		req.setAttribute("article", article); // 這裡會對應到 spec-blog的 <%Article article =
												// (Article)request.getAttribute("article");%> 來設置他的值 注意名稱要對應到
		return "/article/spec-blog.jsp";
	}

	private String modifyArticleState(HttpServletRequest req, HttpServletResponse res) {
		Integer articleId = Integer.valueOf(req.getParameter("articleId"));
		Integer userId = Integer.valueOf(req.getParameter("userId"));
		String value = req.getParameter("value");
		Article article = articleService.getArticleByArticleId(articleId);

		if ("removeArticle".equals(value))
			article.setState(false);
		else if ("uploadArticle".equals(value))
			article.setState(true);

		articleService.updateArticle(article);

		if (userId != null) {
			return "/art/art.do?action=getAllArticles&userId=" + userId;
		} else {
			return "/art/art.do?action=getAllArticles";
		}
	}

	private String CompositeArticleQuery(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> map = req.getParameterMap();

		if (map != null) {
			// 將map(請求的參數資訊)轉交給service處理
			List<Article> articleList = articleService.getArticlesByCompositeQuery(map);
			req.setAttribute("getAllArticles", articleList);
		} else {
			return "/art/art.do?action=getAllArticles";
		}

		return "/admin/article_management.jsp";
	}

	private String getAllArticles(HttpServletRequest req, HttpServletResponse res) {
		String userId = req.getParameter("userId");
		List<Article> articleList = new ArrayList<>();
		String forwardPath;
		if (userId != null && userId.length() > 0) {
			articleList = articleService.getAllArticle(Integer.valueOf(userId));
			forwardPath = "/member_center/article_management.jsp";
		} else {
			articleList = articleService.getAllArticle();
			forwardPath = "/admin/article_management.jsp";
		}

		req.setAttribute("getAllArticles", articleList);
		return forwardPath;
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
			return "/article/select_page.jsp";// 程式中斷
		}

		/*************************** 2.開始查詢資料 *****************************************/
//  CouponService coupoService = new CouponService();
		Article article = articleService.getArticleByArticleId(articleId);

		if (article == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			return "/article/select_page.jsp";// 程式中斷
		}

		/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		req.setAttribute("artcle", article); // 資料庫取出的manage物件,存入req
		return "/article/listOneCoupon.jsp";
	}

	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
		Integer articleId = Integer.valueOf(req.getParameter("articleId"));
		Article article = articleService.getArticleByArticleId(articleId);

		req.setAttribute("article", article);
		return "/article/update_article_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		Integer articleId = Integer.valueOf(req.getParameter("articleId"));

		Integer usertId = Integer.valueOf(req.getParameter("userId"));

		Integer forumArtId = Integer.valueOf(req.getParameter("forumArtId"));

		String articleName = req.getParameter("articleName");

		String articleContent = req.getParameter("articleContent");

//  byte[] articleImg = req.getPart(articleImg);

		Timestamp updateTime = null;
		try {
			updateTime = java.sql.Timestamp.valueOf(req.getParameter("updateTime").trim());
		} catch (IllegalArgumentException e) {
			updateTime = new java.sql.Timestamp(System.currentTimeMillis());
			errorMsgs.add("請輸入文章發布時間");
		}
		Integer ctr = Integer.valueOf(req.getParameter("ctr"));

		Boolean state = Boolean.valueOf(req.getParameter("state"));
//  Timestamp endDate  = null;
//  try {
//   endDate = java.sql.Timestamp.valueOf(req.getParameter("endDate").trim());
//  } catch (IllegalArgumentException e) {
//   endDate = new java.sql.Timestamp(System.currentTimeMillis());
//   errorMsgs.add("請輸入管理員最後上線時間!");
//  }

//  Integer discountAmount =  Integer.valueOf(req.getParameter("discountAmount"));

		Article article = new Article();
		article.setArticleId(articleId);
//  article.setUserId(usertId);
//  article.setForumArtId(forumArtId);
		article.setArticleContent(articleContent);
//  article.setUpdatTime(updateTime);
		article.setCtr(ctr);
		article.setState(state);

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("article", article); // 含有輸入格式錯誤的manage物件,也存入req
			return "/article/update_article_input.jsp"; // 程式中斷
		}

		/*************************** 2.開始修改資料 *****************************************/
		articleService.updateArticle(article);
		req.setAttribute("article", articleService.getArticleByArticleId(articleId));

		/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
		req.setAttribute("article", article); // 資料庫update成功後,正確的的manage物件,存入req
		return "/article/listarticle.jsp";
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//  String couponName = req.getParameter("couponName");
//  String couponNameReg = "^[\\u4e00-\\u9fa5]{2,}$";
//   if (couponName == null || couponName.trim().length() == 0) {
//    errorMsgs.add("管理員姓名: 請勿空白");
//   } else if (!couponName.trim().matches(couponNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//    errorMsgs.add("管理員姓名: 只能是中文, 且長度必需大於2個字");
//   }
//
//  String couponContent = req.getParameter("couponContent"); 
//  
//  Integer conditionsOfUse = Integer.valueOf(req.getParameter("conditionsOfUse"));
//  
//  Timestamp startDate  = null;                                              
//  try {
//   startDate = java.sql.Timestamp.valueOf(req.getParameter("startDate").trim());
//  } catch (IllegalArgumentException e) {
//   startDate = new java.sql.Timestamp(System.currentTimeMillis());
//   errorMsgs.add("請輸入管理員最後上線時間!");
//  }
//  
//  java.sql.Timestamp endDate  = null;                                             
//  try {
//   endDate = java.sql.Timestamp.valueOf(req.getParameter("endDate").trim());
//  } catch (IllegalArgumentException e) {
//   endDate = new java.sql.Timestamp(System.currentTimeMillis());
//   errorMsgs.add("請輸入管理員最後上線時間!");
//  }
//  
//  Integer discountAmount =  Integer.valueOf(req.getParameter("discountAmount"));
		Integer usertId = Integer.valueOf(req.getParameter("userId"));

		Integer forumArtId = Integer.valueOf(req.getParameter("forumArtId"));

		String articleContent = req.getParameter("articleContent");

		Timestamp updateTime = null;
		try {
			updateTime = java.sql.Timestamp.valueOf(req.getParameter("updateTime").trim());
		} catch (IllegalArgumentException e) {
			updateTime = new java.sql.Timestamp(System.currentTimeMillis());
			errorMsgs.add("請輸入文章發布時間");
		}
		Integer ctr = Integer.valueOf(req.getParameter("ctr"));
		Boolean state = Boolean.valueOf(req.getParameter("state"));

		Article article = new Article();
//  article.setUserId(usertId);
//  article.setForumArtId(forumArtId);
		article.setArticleContent(articleContent);
//  article.setUpdatTime(updateTime);
		article.setCtr(ctr);
		article.setState(state);

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("article", article); // 含有輸入格式錯誤的empVO物件,也存入req
			return "/article/addarticle.jsp";
		}

		/*************************** 2.開始新增資料 ***************************************/

		articleService.addArticle(article);

		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		return "/coupon/listAllCoupon.jsp";
	}

	private String delete(HttpServletRequest req, HttpServletResponse res) {
//    List<String> errorMsgs = new List<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
//          req.setAttribute("errorMsgs", errorMsgs);

		/** 1.接收請求參數 **/
		Integer articleId = Integer.valueOf(req.getParameter("articleId"));

		/** 2.開始刪除資料 **/
		articleService.deleteArticle(articleId);

		/** 3.刪除完成,準備轉交(Send the Success view) **/
		return "/article/listAllarticle.jsp";
//          String url = "/coupon/listAllCoupon.jsp";
//          RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//          successView.forward(req, res);
	}

}
