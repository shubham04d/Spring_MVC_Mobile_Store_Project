package com.mysql_mvc.controllers;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mysql_mvc.models.Mobile;
import com.mysql_mvc.repositories.MobileRepository;

@Controller
public class MobileController {

	@Autowired
	MobileRepository mobileRepository;

	@GetMapping("/")
	public String index(Model model) {
		Iterable<Mobile> data = mobileRepository.findAll();
		Iterator<Mobile> all_mobiles = data.iterator();
		model.addAttribute("all_mobiles", all_mobiles);
		return "index";
	}

	@GetMapping("/add-mobile")
	public String add_mobile(Model model) {
		model.addAttribute("mobile_object", new Mobile());
		return "add_mobile";
	}

	@PostMapping("/add-mobile")
	public String add_mobile(@ModelAttribute Mobile mobile, Model model) {
		System.out.println(mobile.getId());
		System.out.println(mobile.getName());
		System.out.println(mobile.getPrice());
		mobileRepository.save(mobile);
		
		Iterable<Mobile> data = mobileRepository.findAll();
		Iterator<Mobile> all_mobiles = data.iterator();
		model.addAttribute("all_mobiles", all_mobiles);
		return "redirect:/";
	}

	@GetMapping("/edit-mobile")
	public String edit_mobile(@RequestParam("id") int mobileId, Model model) {
		Mobile mobile = mobileRepository.findById(mobileId).orElse(null);
		model.addAttribute("mobile_object", mobile);
		return "edit_mobile";
	}

	@PostMapping("/edit-mobile")
	public String update_mobile(@ModelAttribute("mobile_object") Mobile updatedMobile) {
		Mobile existingMobile = mobileRepository.findById(updatedMobile.getId()).orElse(null);
		if (existingMobile != null) {
			existingMobile.setName(updatedMobile.getName());
			existingMobile.setPrice(updatedMobile.getPrice());
			mobileRepository.save(existingMobile);
		}
		return "redirect:/";
	}

	@GetMapping("/delete-mobile")
	public String deleteMobile(@RequestParam("id") int mobileId) {
		mobileRepository.deleteById(mobileId);
		return "redirect:/";
	}
}