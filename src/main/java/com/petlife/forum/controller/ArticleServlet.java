package com.petlife.forum.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.petlife.pet.entity.PetPhoto;
import com.petlife.user.entity.User;

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
		case "getTopArticlesByCTR":
	        forwardPath = getTopArticlesByCTR(req, res);
	        break;
		case "getArticlesByForumId":
	        forwardPath = getArticlesByForumId(req, res);
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
	
	private String getArticlesByForumId(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    // 从请求中获取论坛ID参数
	    String ForumId = req.getParameter("forumId");
	    Integer forumId = Integer.parseInt(ForumId);

	    // 从服务层获取与论坛ID相关联的文章列表
	    List<Article> articlesByForum = articleService.getArticlesByForumId(forumId);

	    // 将文章列表设置为请求属性
	    req.setAttribute("articlesByForum", articlesByForum);

	    // 返回适当的 JSP 页面路径
	    return "/article/ArticleBySort.jsp";
	}
	
	private String getTopArticlesByCTR(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    // 从请求中获取论坛ID参数
	    String strForumId = req.getParameter("forumId");
	    Integer forumId = null;
	    
	    
	    try {
	        forumId = Integer.parseInt(strForumId);
	    } catch (NumberFormatException e) {
	        // 如果转换失败，可能需要重定向到错误页面或设置错误消息
	        req.setAttribute("errorMessage", "无效的论坛ID");
	        return "/article/ArticleTop.jsp"; // 替换为实际的错误页面路径
	    }

	    // 根据论坛ID获取热门文章
	    List<Article> topArticles = articleService.getTopArticlesByCTR(forumId, 5);

	    // 将获取到的热门文章列表设置到请求属性中
	    req.setAttribute("topArticles", topArticles);

	    // 返回适当的 JSP 页面路径
	    return "/article/ArticleTop.jsp"; // 确保这是正确的JSP页面路径
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
		req.setAttribute("articleImg", articleImg); //這裡會對應到 spec-blog的 <%Article article = (Article)request.getAttribute("article");%> 來設置他的值  注意名稱要對應到

	}

	private String getArticleById(HttpServletRequest req, HttpServletResponse res) {
		Integer articleId = Integer.valueOf(req.getParameter("articleId")); // 先拿到他的ID
		Article article = articleService.getArticleByArticleId(articleId); // 再透過articleId去articleService叫用getArticleByArticleId方法拿到Article的資料
		req.setAttribute("article", article); // 這裡會對應到 spec-blog的 <%Article article =
														// (Article)request.getAttribute("article");%> 來設置他的值 注意名稱要對應到
		//瀏覽數			
		articleService.updateView(articleId);
		return "/article/spec-blog.jsp";
	}

	private String modifyArticleState(HttpServletRequest req, HttpServletResponse res) {
		Integer articleId = Integer.valueOf(req.getParameter("articleId").trim());
		String userId = req.getParameter("userId");
		String value = req.getParameter("value").trim();
		Article article = articleService.getArticleByArticleId(articleId);

		if ("removeArticle".equals(value))
			article.setState(false);
		else if ("uploadArticle".equals(value))
			article.setState(true);

		articleService.updateArticle(article);

		if (userId != null) {
			return "/art/art.do?action=getAllArticles&userId=" + Integer.valueOf(userId.trim());
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
		
	String selectArticle  =	req.getParameter("selectArticle");
	if(selectArticle!=null && selectArticle.length()>0) {
		List<Article> list = articleService.getArticlesByCompositeQuery(map);
		req.setAttribute("list", list);
		return "/article/Articleindex2.jsp";
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
		return "/article/listOneArticle.jsp";
	}
	/*************************** 1.更新一筆文章(Send the Success view) *************/
	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
		Integer articleId = Integer.valueOf(req.getParameter("articleId"));
		Article article = articleService.getArticleByArticleId(articleId);

		req.setAttribute("article", article);
		return "/article/blog-details2.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		Integer articleId = Integer.valueOf(req.getParameter("articleId"));

		User user = (User) req.getSession().getAttribute("user");
	    Integer userId = user.getUserId();
	    	if (userId == null) {
	        errorMsgs.add("無法獲取用戶信息");
	    }
	    System.out.println(user.getUserId());

	    Integer forumName = Integer.valueOf(req.getParameter("forumName"));

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
//		articleService.getArticleByArticleId(articleId);
//		Integer ctr = 1000;
		Boolean state = Boolean.valueOf(req.getParameter("state"));
		
	    // 查找對應的論壇類別
	    ForumService forumService = new ForumServiceImpl();
	    Forum forum = forumService.getForumById(forumName);
	    if (forum == null) {
	        errorMsgs.add("找不到對應的論壇類別");
	    }
	    System.out.println(forumName);
		
		Article article = articleService.getArticleByArticleId(articleId);
		
		article.setArticleName(articleName);
	    article.setArticleContent(articleContent);
	    article.setForum(forum);
	    article.setUpdateTime(updateTime);

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("article", article); // 含有輸入格式錯誤的manage物件,也存入req
			return "/article/blog-details2.jsp"; // 程式中斷
		}

		/*************************** 2.開始修改資料 *****************************************/
		articleService.updateArticle(article);
//		req.setAttribute("article", articleService.getArticleByArticleId(articleId));

		/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
		req.setAttribute("article", article); // 資料庫update成功後,正確的的manage物件,存入req
		return "/article/spec-blog.jsp";
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		 System.out.println("ArtServlet :  Entry");
		    // 錯誤處理
		    List<String> errorMsgs = new ArrayList<>();
		    req.setAttribute("errorMsgs", errorMsgs);
		    
//		    ArticleImg articleImg = new ArticleImg();
		    
		    for (Part part : req.getParts()) {
				if (!part.getName().equals("articlePhoto"))
					continue;
				InputStream in = part.getInputStream();
				byte[] articlePhoto = null;
				if (in.available() != 0) {
					articlePhoto = new byte[in.available()];
					in.read(articlePhoto);
					in.close();
					// 將抓取的照片存進petphoto->table
					ArticleImg articleImg = new ArticleImg();
//					articleImg.setArticle(article);
					articleImg.setArticleImg(articlePhoto);
//					articleImg.getArticleImg().;
				}
			}
		    
		    
		    // 1.接收請求參數 - 輸入格式的錯誤處理
		    Article article = new Article();
//		    String articleId = req.getParameter("articleId");
		    String articleName = req.getParameter("articleName");
		    System.out.println(articleName);
		    String articleContent = req.getParameter("articleContent");
		    System.out.println(articleContent);
		    Integer forumName = Integer.valueOf(req.getParameter("forumName")); // 使用 forumName 而不是 forumId
		    System.out.println(forumName);
		    if (articleName == null || articleName.trim().isEmpty()) {
		        errorMsgs.add("文章名稱不得為空");
		    }
		    if (articleContent == null || articleContent.trim().isEmpty()) {
		        errorMsgs.add("文章內容不得為空");
		    }
//		    if (forumName == null || forumName.trim().isEmpty()) {
//		        errorMsgs.add("請選擇論壇分類");
//		    }

		    // 如果有錯誤，返回表單頁面
		    if (!errorMsgs.isEmpty()) {
		        req.setAttribute("article", new Article());
		        return "/article/addArticle.jsp";
		    }
//		    Integer articleId = null;
//		    System.out.println(articleId);
		    Integer ctr = 10;
		    System.out.println(ctr);
		    Boolean state = true;
		    System.out.println(state);
		    System.out.println(req.getParameter("articlePhoto"));
		    try {
//		        ctr = Integer.valueOf(req.getParameter("ctr"));
//		        state = Boolean.valueOf(req.getParameter("state"));
		    } catch (NumberFormatException e) {
		        errorMsgs.add("計數器或狀態格式不正確");
		    } 
		  

		
		    User user = (User) req.getSession().getAttribute("user");
		    Integer userId = user.getUserId();
		    if (userId == null) {
		        errorMsgs.add("無法獲取用戶信息");
		    }
		    System.out.println(user.getUserId());

		    // 查找對應的論壇類別
		    ForumService forumService = new ForumServiceImpl();
		    Forum forum = forumService.getForumById(forumName);
		    if (forum == null) {
		        errorMsgs.add("找不到對應的論壇類別");
		    }
		    System.out.println(forumName);

		    // 如果有錯誤，返回表單頁面
		    if (!errorMsgs.isEmpty()) {
		        req.setAttribute("article", new Article());
		        return "/article/addArticle.jsp";
		    }
		    
//		    Integer user= 100000005;
//		    System.out.println(user);
		    // 建立文章對象並設置其屬性
//		    Article article = new Article();
		    article.setArticleName(articleName);
		    article.setArticleContent(articleContent);
		    article.setUser(user);
		    article.setForum(forum);
		    article.setCtr(ctr);
		    article.setState(state);
		    
		    for (Part part : req.getParts()) {
				if (!part.getName().equals("articlePhoto"))
					continue;
				InputStream in = part.getInputStream();
				byte[] articlePhoto = null;
				if (in.available() != 0) {
					articlePhoto = new byte[in.available()];
					in.read(articlePhoto);
					in.close();
					// 將抓取的照片存進petphoto->table
					ArticleImg articleImg = new ArticleImg();
					articleImg.setArticle(article);
					articleImg.setArticleImg(articlePhoto);
					article.getArticleImgs().add(articleImg);
				}
			}

		    // 2.開始新增資料
		    articleService.addArticle(article);
		    
		    
		    // 3.新增完成,準備轉交
		    return "/article/Articleindex.jsp";
		}

	private String delete(HttpServletRequest req, HttpServletResponse res) {
//    List<String> errorMsgs = new List<String>();
//		 Store this set in the request scope, in case we need to
//		 send the ErrorPage view.
//          req.setAttribute("errorMsgs", errorMsgs);

		/** 1.接收請求參數 **/
		Integer articleId = Integer.valueOf(req.getParameter("articleId"));

		/** 2.開始刪除資料 **/
		articleService.deleteArticle(articleId);

		/** 3.刪除完成,準備轉交(Send the Success view) **/
		return "/article/listAllArticle.jsp";
//          String url = "/coupon/listAllCoupon.jsp";
//          RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//          successView.forward(req, res);
	}

}
