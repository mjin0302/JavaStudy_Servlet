package com.yedam.product.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.product.service.*;
import com.yedam.product.vo.ProductVO;

public class ProductList implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProductService service = new ProductServiceMybatis();
		List<ProductVO> list = service.productList();
		
		req.setAttribute("list", list);
		
		return "product/productList.tiles";
		
	}

}
