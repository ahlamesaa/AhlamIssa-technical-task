package com.luminous.integration.service;

import com.luminous.integration.exception.DigimonException;
import com.luminous.integration.model.Digimon;


/**
 * The Interface DigimonService.
 *
 * @author Ahlam-issa
 */
public interface DigimonERPService {

	/**
	 * Gets the all digimon.
	 *
	 * @return the all digimon
	 * @throws DigimonException the digimon exception
	 */
	public Digimon[] getAllDigimon() throws DigimonException;

	/**
	 * Gets the all digimon by name.
	 *
	 * @param name the name
	 * @return the all digimon by name
	 * @throws DigimonException the digimon exception
	 */
	public Digimon[] getAllDigimonByName(String name) throws DigimonException;

	/**
	 * Gets the all digimon by level.
	 *
	 * @param level the level
	 * @return the all digimon by level
	 * @throws DigimonException the digimon exception
	 */
	public Digimon[] getAllDigimonByLevel(String level) throws DigimonException;

}
