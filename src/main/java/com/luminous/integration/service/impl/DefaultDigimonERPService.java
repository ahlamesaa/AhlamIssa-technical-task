package com.luminous.integration.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luminous.integration.exception.DigimonException;
import com.luminous.integration.exception.enums.DigimonExceptionType;
import com.luminous.integration.model.Digimon;
import com.luminous.integration.service.DigimonERPService;
import com.luminous.integration.util.WebServiceApiUtil;

/**
 * The Class DefaultDigimonService.
 *
 * @author Ahlam-issa
 */
@Service
public class DefaultDigimonERPService implements DigimonERPService {
	private static final String SOME_THING_WRONG = "SomeThing Wrong: ";
	private static final String DIGIMONS_RETRIEVED_SUCCESSFULLY = "Digimons Retrieved successfully .... ";
	private static final Logger LOG = LoggerFactory.getLogger(DefaultDigimonERPService.class);
	private static final String URL = "https://digimon-api.herokuapp.com/api/digimon";
	private static final String NAME = "/name/";
	private static final String LEVEL = "/level/";

	/**
	 * Gets the all digimon.
	 *
	 * @return the all digimon
	 * @throws DigimonException the digimon exception
	 */
	@Override
	public Digimon[] getAllDigimon() throws DigimonException {
		LOG.info("Retrieving all Digimon .... ");
		try {
			ResponseEntity<?> digimons = WebServiceApiUtil.httpGet(URL, null, Digimon[].class);
			LOG.info(DIGIMONS_RETRIEVED_SUCCESSFULLY);
			return (Digimon[]) digimons.getBody();
		} catch (Exception e) {
			LOG.error(SOME_THING_WRONG, e.getMessage());
			return new Digimon[] {};
		}
	}

	/**
	 * Gets the all digimon by name.
	 *
	 * @param name the name
	 * @return the all digimon by name
	 * @throws DigimonException the digimon exception
	 */
	@Override
	public Digimon[] getAllDigimonByName(String name) throws DigimonException {
		if (StringUtils.isBlank(name)) {
			throw new DigimonException(DigimonExceptionType.INVALID_NAME.getMessage(),
					DigimonExceptionType.INVALID_NAME);

		}
		try {
			LOG.info("Retrieving all Digimon By name .... ");
			ResponseEntity<?> digimons = WebServiceApiUtil.httpGet(URL + NAME+name, null, Digimon[].class);
			LOG.info(DIGIMONS_RETRIEVED_SUCCESSFULLY);
			return (Digimon[]) digimons.getBody();
		} catch (Exception e) {
			LOG.error(SOME_THING_WRONG, e.getMessage());
			return new Digimon[] {};
		}

	}

	/**
	 * Gets the all digimon by level.
	 *
	 * @param level the level
	 * @return the all digimon by level
	 * @throws DigimonException the digimon exception
	 */
	@Override
	public Digimon[] getAllDigimonByLevel(String level) throws DigimonException {
		if (StringUtils.isBlank(level)) {
			throw new DigimonException(DigimonExceptionType.INVALID_LEVEL.getMessage(),
					DigimonExceptionType.INVALID_LEVEL);
		}
		try {
			LOG.info("Retrieving all Digimon By Level .... ");
			ResponseEntity<?> digimons = WebServiceApiUtil.httpGet(URL + LEVEL+level, null, Digimon[].class);
			LOG.info(DIGIMONS_RETRIEVED_SUCCESSFULLY);
			return (Digimon[]) digimons.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(SOME_THING_WRONG, e.getMessage());
			return new Digimon[] {};
		}
	}

}
