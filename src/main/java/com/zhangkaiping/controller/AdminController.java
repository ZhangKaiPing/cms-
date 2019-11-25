package com.zhangkaiping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhangkaiping.domain.Article;
import com.zhangkaiping.domain.ArticleWithBLOBs;
import com.zhangkaiping.domain.Links;
import com.zhangkaiping.domain.User;
import com.zhangkaiping.service.ArticleService;
import com.zhangkaiping.service.LinksService;
import com.zhangkaiping.service.UserService;
import com.zhangkaiping.util.Result;
import com.zhangkaiping.util.ResultUtil;

import net.sf.jsqlparser.util.AddAliasesVisitor;
@RequestMapping("admin")
@Controller
public class AdminController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private LinksService linksService;
	
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 维护友情链接-列表
	 * @param model
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	
	@GetMapping("links/selects")
	public String selects(Model model,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
		PageInfo<Links> info = linksService.selects(page, pageSize);
		
		model.addAttribute("info",info);
		
		return "admin/links/links";
		
	}

	/**
	 * 
	 * @Title: add 
	 * @Description: 跳转到增加友情链接页面
	 * @return
	 * @return: String
	 */
	@GetMapping("links/add")
	public String add() {
		return "admin/links/add";
	}
	
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 跳转到增加友情链接页面
	 * @return
	 * @return: String
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping("links/add")
	public Result<Links> add(Links links) {
		linksService.insert(links);
		return ResultUtil.success();
	}
	
	
	@RequestMapping("user/users")
	public String users(User user,Model model,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
		PageInfo<User> info = userService.selects(user, page, pageSize);
		model.addAttribute("pg",info);
		model.addAttribute("user", user);
		model.addAttribute("info", info);
		return "admin/user/users";
		
	}
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("user/update")
	@ResponseBody
	public boolean update(User user) {
		boolean update = userService.update(user);
		return update;
	}
	
	
/**
 * 
 * @Title: detail 
 * @Description: 查询文章详情
 * @param model
 * @param id
 * @return
 * @return: String
 */
	@RequestMapping("article/article")
	public String detail(Model model,Integer id) {
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article",article);
		return "admin/article/article";
	}
	
	
	
	
	
	@RequestMapping("article/articles")
	public String articles(Model model,Article article,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
		PageInfo<Article> info=articleService.selects(article, page, pageSize);
		model.addAttribute("article",article);
		model.addAttribute("info",info);
		model.addAttribute("nums",info.getNavigatepageNums());
		return "admin/article/articles";
	}
	
	
	@ResponseBody
	@RequestMapping("article/update")
	public boolean update(ArticleWithBLOBs article) {
		
		System.out.println(article.getId()+"444444444444444444444"+article.getHot());
		boolean i=articleService.update(article);
		return i;
		
	}
	
	/**
	 * 
	 * @Title: index 
	 * @Description:后台页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("index")
	public String index() {
		return "admin/index";
	}
	
}
