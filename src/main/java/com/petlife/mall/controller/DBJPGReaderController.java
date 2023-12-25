package com.petlife.mall.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.petlife.mall.service.CommService;
import com.petlife.mall.service.impl.CommServiceImpl;
//import com.pichill.manage.entity.Manage;

@WebServlet("/comm/DBJPGReader")
public class DBJPGReaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/*");
		ServletOutputStream out = res.getOutputStream();

		try {
			Integer commId = Integer.valueOf(req.getParameter("commId"));
			CommService commService = new CommServiceImpl();
			byte[] b = commService.findByPk(commId).getCommImg();
//			System.out.println(b);
//			if(b == null) {
//				System.out.println("ID:"+manageID);
//			}
			out.write(b);
		} catch (Exception e) {
//			e.printStackTrace();
			InputStream in = getServletContext().getResourceAsStream("/webapp/dist/img/avatar.png");
			if(in == null) {
			    throw new RuntimeException("Unable to find default picture"); 
			  }
			
			byte[] buf = new byte[in.available()];
			
			in.read(buf);
			out.write(buf);
			in.close();

		}
	}

}
