package com.yedam.product.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.product.service.ProductService;
import com.yedam.product.service.ProductServiceMybatis;


public class ProductDetail implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String code = req.getParameter("productCode");
		
		ProductService service = new ProductServiceMybatis();
		req.setAttribute("vo", service.getProduct(code));
		
		req.setAttribute("vo2", service.relateList());
		
		return "product/product.tiles";
	}

}
