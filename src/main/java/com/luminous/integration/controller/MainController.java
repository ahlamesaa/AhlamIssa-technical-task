package com.luminous.integration.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luminous.integration.exception.DigimonException;
import com.luminous.integration.model.Digimon;
import com.luminous.integration.model.Favorite;
import com.luminous.integration.service.DigimonService;
import com.luminous.integration.service.impl.DefaultDigimonERPService;

@Controller
@RequestMapping
public class MainController {
	@Autowired
	private DefaultDigimonERPService defaultDigimonERPService;
	@Autowired
	private DigimonService digimonService;

	
	@GetMapping("/reload")
	public String reload(Model model) throws DigimonException {
		digimonService.clearAll();
		digimonService.addAll(Arrays.asList(defaultDigimonERPService.getAllDigimon()));
		
		return "redirect:/";

	}
	
	@GetMapping("/")
	public String getHome(Model model) throws DigimonException {
	
		List<Digimon> allDigimons = digimonService.getAllDigimon();
		model.addAttribute("allDigimons", allDigimons);
		return "HomePage";

	}

	@GetMapping("/favorite/{name}")
	public String getFavoritePage(Model model,@PathVariable("name") String name) {
		System.out.println("id : "+name);
		Favorite favoriteByID = digimonService.getFavoriteByName(name);
		List<Favorite> allDigimons = digimonService.getAllFavorites();
		if (favoriteByID==null) {
			digimonService.addFavorite(digimonService.getDigimonByName(name));
			allDigimons=digimonService.getAllFavorites();
			model.addAttribute("favorites", allDigimons);
			return "FavoritePage";
		}
		else {
		model.addAttribute("favorites", allDigimons);
	
		return "FavoritePage";
		}

	}

	@GetMapping("/digimon/update/{name}")
	public String updateDigimon(Model model, @PathVariable("name") String name) {
		Digimon digimonByName = digimonService.getDigimonByName(name);
		model.addAttribute("digimon", digimonByName);
		digimonService.deleteDigimon(digimonByName.getId());;
		return "UpdateDigimonPage";

	}

	@PostMapping("/preform/digimon/update")
	public String preformDigimonUpdate(@ModelAttribute("digimon") Digimon digimon) {

		digimonService.updateDigimon(digimon);

		return "redirect:/";

	}

	@GetMapping("/favorite/update/{name}")
	public String updateFavorite(Model model, @PathVariable("name") String name) {

		Favorite digimonByName = digimonService.getFavoriteByName(name);		
		digimonService.deleteFavorite(digimonByName.getId());		
		model.addAttribute("favorite", digimonByName);
		return "UpdateFavoritePage";

	}


	@PostMapping("/preform/favorite/update")
	public String preformFavoriteUpdate(@ModelAttribute("favorite") Favorite favorite) {

		digimonService.updateFavorite(favorite);

		return "redirect:/favorite";

	}

	@GetMapping("/digimon/delete/{id}")
	public String deleteDigimon(@PathVariable("id") long id) {

		digimonService.deleteDigimon(id);
		return "redirect:/";

	}
	@GetMapping("/favorite")
	public String getFavorite(Model model ) {

		
		
		List<Favorite> allDigimons = digimonService.getAllFavorites();
			model.addAttribute("favorites", allDigimons);
			return "FavoritePage";
		
		

	}
	@GetMapping("/favorite/delete/{id}")
	public String deleteFavorite(@PathVariable("id") long id) {
		digimonService.deleteFavorite(id);
		return "redirect:/favorite";

	}

}
