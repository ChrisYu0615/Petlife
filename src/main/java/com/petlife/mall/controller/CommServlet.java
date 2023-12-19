package com.petlife.mall.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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

import com.petlife.admin.entity.Coupon;
import com.petlife.admin.service.impl.CouponServiceImpl;
import com.petlife.mall.entity.Comm;
import com.petlife.mall.service.CommService;
import com.petlife.mall.service.impl.CommServiceImpl;
import com.petlife.seller.entity.Seller;
import com.petlife.seller.service.SellerService;
import com.petlife.seller.service.impl.SellerServiceImpl;
import com.petlife.user.entity.User;

@WebServlet("/comm/comm.do")
@MultipartConfig
public class CommServlet extends HttpServlet {
    private CommService commService;
    private SellerService sellerService;

    @Override
    public void init() throws ServletException {
        commService = new CommServiceImpl();
        sellerService = new SellerServiceImpl();
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
                forwardPath = getOneDisplay(req, res);
                break;
            case "getOne_For_Update":
                forwardPath = getOneUpdate(req, res);
                break;
            case "update":
                forwardPath = update(req, res);
                break;
            case "insert":
                forwardPath = insert(req, res);
                break;
            case "delete":
                forwardPath = delete(req, res);
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

        // 接收請求參數
        String str = req.getParameter("commId");

        if (str == null || (str.trim()).length() == 0) {
            errorMsgs.add("請輸入商品編號");
        }

        if (!errorMsgs.isEmpty()) {
            return "/comm/select_page.jsp";
        }

        Integer commId = null;
        try {
            commId = Integer.valueOf(str);
        } catch (Exception e) {
            errorMsgs.add("商品編號格式不正確");
        }

        if (!errorMsgs.isEmpty()) {
            return "/commImg/select_page.jsp";
        }

        // 開始查詢資料
        commService = new CommServiceImpl();        
        Comm comm = commService.findByPk(commId);

        if (comm == null) {
            errorMsgs.add("查無資料");
        }

        if (!errorMsgs.isEmpty()) {
            return "/comm/select_page.jsp";
        }

        req.setAttribute("comm", comm);
        return "/comm/listOneComm.jsp";
    }
//2.修改
    private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
        Integer commId = Integer.valueOf(req.getParameter("commId"));
        Comm comm = commService.findByPk(commId);
//
        List<Seller> sellers = sellerService.getAllSellers();
        req.setAttribute("sellers", sellers);

        req.setAttribute("comm", comm);
        return "/comm/update_Comm_input.jsp";
    }
//=============================================================
    private String update(HttpServletRequest req, HttpServletResponse res) {
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
//========================================================
        String commIdStr = req.getParameter("commId");

        if (commIdStr == null || commIdStr.trim().isEmpty()) {
            errorMsgs.add("商品ID不能為空");
        }

        Integer commId = Integer.parseInt(commIdStr);

        User user = (User) req.getSession().getAttribute("account");
        if (user == null) {
            errorMsgs.add("無法獲取使用者信息");
        }

        List<Seller> sellers = sellerService.getAllSellers();
        req.setAttribute("sellers", sellers);

        if (!errorMsgs.isEmpty()) {
            return "/comm/update_Comm_input.jsp";
        }

        Comm comm = commService.findByPk(commId);
        if (comm == null) {
            errorMsgs.add("找不到對應的商品");
            req.setAttribute("comm", new Comm());
            return "/comm/update_Comm_input.jsp";
        }

        // 處理圖片檔案
        byte[] commImg = null;
        try {
            InputStream in = req.getPart("commImg").getInputStream();
            if (in.available() != 0) {
                commImg = new byte[in.available()];
                in.read(commImg);
                in.close();
            } else {
                errorMsgs.add("商品圖片: 請上傳照片");
            }
        } catch (IOException | ServletException e) {
            errorMsgs.add("圖片上傳失敗: " + e.getMessage());
        }

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("comm", comm);
            return "/comm/update_Comm_input.jsp";
        }

        
        comm.setCommName(req.getParameter("commName"));
        comm.setCommDesc(req.getParameter("commDesc"));
        comm.setCommState(Integer.parseInt(req.getParameter("commState")));
        comm.setListDatetime(Timestamp.valueOf(req.getParameter("listDatetime")));
        comm.setCommImg(commImg);
        comm.setCommCatId(Integer.parseInt(req.getParameter("commCatId")));
        comm.setCommStock(Integer.parseInt(req.getParameter("commStock")));
        comm.setCommPrice(new BigDecimal(req.getParameter("commPrice")));
        comm.setCommOnsalePrice(new BigDecimal(req.getParameter("commOnsalePrice")));
        comm.setCommViewCount(Integer.parseInt(req.getParameter("commViewCount")));

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("comm", comm);
            return "/comm/update_Comm_input.jsp";
        }

        commService.update(comm);
        req.setAttribute("comm", comm);

        return "/comm/listAllComm.jsp";
    }
//==========================================================================
    //新增
    private String insert(HttpServletRequest req, HttpServletResponse res) {
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);

        String commIdStr = req.getParameter("commId");

        if (commIdStr == null || commIdStr.trim().isEmpty()) {
            errorMsgs.add("商品ID不能為空");
        }

        Integer commId = Integer.parseInt(commIdStr);

        Comm comm = commService.findByPk(commId);
        if (comm == null) {
            errorMsgs.add("找不到對應的商品");
            req.setAttribute("comm", new Comm());
            return "/commImg/addCommImg.jsp";
        }

        // 處理圖片檔案
        byte[] commImg = null;
        try {
            InputStream in = req.getPart("commImg").getInputStream();
            if (in.available() != 0) {
                commImg = new byte[in.available()];
                in.read(commImg);
                in.close();
            } else {
                errorMsgs.add("商品圖片: 請上傳照片");
            }
        } catch (IOException | ServletException e) {
            errorMsgs.add("圖片上傳失敗: " + e.getMessage());
        }

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("commImg", new Comm());
            return "/commImg/addCommImg.jsp";
        }

        // 創建 CommImg 對象並設置其屬性
//        Comm comm = new Comm();
//        comm.setComm(comm);
        comm.setCommImg(commImg);

        commService.add(comm);

        return "/comm/listAllComm.jsp";
    }
//=====================================================================
    private String delete(HttpServletRequest req, HttpServletResponse res) {
        Integer commId = Integer.parseInt(req.getParameter("commId"));
        commService.delete(commId);
        return "/comm/listAllComm.jsp";
    }
}