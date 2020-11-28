package com.luminous.integration.dao;

import java.util.List;

import com.luminous.integration.model.Digimon;
import com.luminous.integration.model.Favorite;

public interface DigimonsDAO  {

	public List<Digimon> getAllDigimon();

	public Digimon getDigimonByName(String name);

	public Digimon getDigimonByLevel(String level);

	public Digimon updateDigimon(Digimon digimon);

	public void deleteDigimon(long id);

	public Digimon addDigimon(Digimon digimon);

	public List<Favorite> getAllFavorites();

	public Favorite getFavoriteByName(String name);

	public Favorite getFavoriteByLevel(String level);

	public Favorite updateFavorite(Favorite favorite);

	public void deleteFavorite(long id);

	public Favorite addFavorite(Digimon digimon);

	public List<Digimon> addAll(List<Digimon> digimons);
	public Digimon getDigimonByID(long id);
	public Favorite getFavoriteByID(long id);
	public void clearAll();
}
