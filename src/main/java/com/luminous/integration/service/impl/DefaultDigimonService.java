package com.luminous.integration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luminous.integration.dao.DigimonsDAO;
import com.luminous.integration.model.Digimon;
import com.luminous.integration.model.Favorite;
import com.luminous.integration.service.DigimonService;

@Service
public class DefaultDigimonService implements DigimonService {
	@Autowired
	private DigimonsDAO digimonsDAO;

	@Override
	public List<Digimon> getAllDigimon() {
		 
		return digimonsDAO.getAllDigimon();
	}

	@Override
	public Digimon getDigimonByName(String name) {
		 
		return digimonsDAO.getDigimonByName(name);
	}

	@Override
	public Digimon getDigimonByLevel(String level) {
		 
		return digimonsDAO.getDigimonByLevel(level);
	}

	@Override
	public Digimon updateDigimon(Digimon digimon) {
		 
		return digimonsDAO.updateDigimon(digimon);
	}

	@Override
	public void deleteDigimon(long id) {
		 
		digimonsDAO.deleteDigimon(id);
	}

	@Override
	public Digimon addDigimon(Digimon digimon) {
		 
		return digimonsDAO.addDigimon(digimon);
	}

	@Override
	public List<Favorite> getAllFavorites() {
		 
		return digimonsDAO.getAllFavorites();
	}

	@Override
	public Favorite getFavoriteByName(String name) {
		 
		return digimonsDAO.getFavoriteByName(name);
	}

	@Override
	public Favorite getFavoriteByLevel(String level) {
		 
		return digimonsDAO.getFavoriteByLevel(level);
	}

	@Override
	public Favorite updateFavorite(Favorite favorite) {
		 
		return digimonsDAO.updateFavorite(favorite);
	}

	@Override
	public void deleteFavorite(long id) {
		digimonsDAO.deleteFavorite(id);

	}

	@Override
	public Favorite addFavorite(Digimon digimon) {
		 
		return digimonsDAO.addFavorite(digimon);
	}

	@Override
	public List<Digimon> addAll(List<Digimon> digimons) {
		 
		return digimonsDAO.addAll(digimons);
	}

	@Override
	public Digimon getDigimonByID(long id) {
		// TODO Auto-generated method stub
		return digimonsDAO.getDigimonByID(id);
	}

	@Override
	public Favorite getFavoriteByID(long id) {
		// TODO Auto-generated method stub
		return digimonsDAO.getFavoriteByID(id);
	}
@Override
public void clearAll() {
	digimonsDAO.clearAll();
	
}
}
