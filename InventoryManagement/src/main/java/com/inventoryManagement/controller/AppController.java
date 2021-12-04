package com.inventoryManagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.inventoryManagement.config.AppConfig;
import com.inventoryManagement.constants.ActionType;
import com.inventoryManagement.entity.Brand;
import com.inventoryManagement.entity.Items;
import com.inventoryManagement.entity.Models;
import com.inventoryManagement.service.BrandService;
import com.inventoryManagement.service.ItemService;
import com.inventoryManagement.service.ModelService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping
@Slf4j
public class AppController {

	public static Gson gson = new Gson();

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	@Autowired
	private BrandService brandService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private ItemService itemsService;

	/**
	 * get Brand object with id from a prototype bean
	 * 
	 * @param id
	 * @return Brand
	 */
	private Brand getBrandWithId(int id) {
		return (Brand) context.getBean("newBrand", id);
	}

	/**
	 * get Models object with id from a prototype bean
	 * 
	 * @param id
	 * @return Models
	 */
	private Models getModelsWithId(int id) {
		return (Models) context.getBean("newModels", id);
	}

	/**
	 * get Items object with id from a prototype bean
	 * 
	 * @param id
	 * @return Items
	 */
	private Items getItemsWithId(int id) {
		return (Items) context.getBean("newItmes", id);
	}

	/**
	 * get the home page
	 * 
	 * @param theModel
	 * @return
	 */
	@GetMapping("/home")
	public String home(Model theModel) {
		return "home";
	}

	/**
	 * get the Brand home page with all brand list
	 * 
	 * @param theModel
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/brandHome")
	public String brandHome(Model theModel) throws Exception {
		List<Brand> brandList = brandService.handleSingleRequest(null, ActionType.SELECT_ALL.toString());
		theModel.addAttribute("brandList", brandList);
		return "brand-home";
	}

	/**
	 * get add new brand page
	 * 
	 * @param theModel
	 * @return
	 */
	@GetMapping("/showBrandForm")
	public String showFormToAddBrand(Model theModel) {
		Brand brand = new Brand();
		theModel.addAttribute("brand", brand);
		return "brand-form";
	}

	/**
	 * save new brand or update brand and go to brand home page
	 * 
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/saveBrand")
	public String saveBrand(@ModelAttribute("brand") Brand brand) throws Exception {
		brandService.handleSingleRequest(brand, ActionType.SAVE.toString());
		return "redirect:/brandHome";
	}

	/**
	 * get update brand page, with all data of a brand
	 * 
	 * @param id
	 * @param theModel
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/updateBrand")
	public String showFormToUpdateBrand(@RequestParam("id") int id, Model theModel) throws Exception {
		Brand brand = getBrandWithId(id);
		List<Brand> brandList = brandService.handleSingleRequest(brand, ActionType.SELECT.toString());
		theModel.addAttribute("brand", brandList.get(0));
		return "brand-form-update";
	}

	/**
	 * delete a existing brand and go to brand home page
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/deleteBrand")
	public String deleteBrand(@RequestParam("id") int id) throws Exception {
		Brand brand = getBrandWithId(id);
		brandService.handleSingleRequest(brand, ActionType.DELETE.toString());
		return "redirect:/brandHome";
	}

	@GetMapping("/modelHome")
	public String modelHome(Model theModel) throws Exception {
		List<Models> modelList = modelService.handleSingleRequest(null, ActionType.SELECT_ALL.toString());
		theModel.addAttribute("modelList", modelList);
		return "model-home";
	}

	@GetMapping("/showModelForm")
	public String showFormToAddmodel(Model theModel) throws Exception {
		Models model = new Models();
		List<Brand> brandList = brandService.handleSingleRequest(null, ActionType.SELECT_ALL.toString());
		model.setBrandList(brandList);
		theModel.addAttribute("models", model);
		return "model-form";
	}

	@PostMapping("/saveModel")
	public String savemodel(@ModelAttribute("model") Models model) throws Exception {
		modelService.handleSingleRequest(model, ActionType.SAVE.toString());
		return "redirect:/modelHome";
	}

	@GetMapping("/updateModel")
	public String showFormToUpdateModel(@RequestParam("id") int id, Model theModel) throws Exception {
		Models model = getModelsWithId(id);
		List<Models> modelList = modelService.handleSingleRequest(model, ActionType.SELECT.toString());
		List<Brand> brandList = brandService.handleSingleRequest(null, ActionType.SELECT_ALL.toString());
		modelList.get(0).setBrandList(brandList);
		theModel.addAttribute("models", modelList.get(0));
		return "model-form-update";
	}

	@GetMapping("/deleteModel")
	public String deletemodel(@RequestParam("id") int id) throws Exception {
		Models model = getModelsWithId(id);
		modelService.handleSingleRequest(model, ActionType.DELETE.toString());
		return "redirect:/modelHome";
	}

	@GetMapping("/getModelsByBrandId")
	public String getModelsByBrandId(@RequestParam("id") int id) throws Exception {
		Models model = getModelsWithId(id);
		List<Models> modelList = modelService.handleSingleRequest(model, ActionType.SELECT_BY_BRAND_ID.toString());
		return gson.toJson(modelList);
	}

	@GetMapping("/itemsHome")
	public String itemsHome(Model theModel) throws Exception {
		List<Items> itemsList = itemsService.handleSingleRequest(null, ActionType.SELECT_ALL.toString());
		theModel.addAttribute("itemsList", itemsList);
		return "items-home";
	}

	@GetMapping("/showItemsForm")
	public String showFormToAddItems(Model theModel) throws Exception {
		Items items = new Items();
		List<Brand> brandList = brandService.handleSingleRequest(null, ActionType.SELECT_ALL.toString());
		items.setBrandList(brandList);
		theModel.addAttribute("items", items);
		return "items-form";
	}

	@PostMapping("/saveItems")
	public String saveItems(@ModelAttribute("items") Items items) throws Exception {
		itemsService.handleSingleRequest(items, ActionType.SAVE.toString());
		return "redirect:/itemsHome";
	}

	@GetMapping("/updateItems")
	public String showFormToUpdateItems(@RequestParam("id") int id, Model theModel) throws Exception {
		Items items = getItemsWithId(id);
		List<Items> itemsList = itemsService.handleSingleRequest(items, ActionType.SELECT.toString());
		theModel.addAttribute("items", itemsList.get(0));
		return "items-form-update";
	}

	@GetMapping("/deleteItems")
	public String deleteItems(@RequestParam("id") int id) throws Exception {
		Items items = getItemsWithId(id);
		itemsService.handleSingleRequest(items, ActionType.DELETE.toString());
		return "redirect:/itemsHome";
	}

	/**
	 * Handle exception and show in a page
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex) {
		log.error("Requested URL=" + request.getRequestURL());
		log.error("Exception Raised=" + ex);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", request.getRequestURL());

		modelAndView.setViewName("error");
		return modelAndView;
	}
}
