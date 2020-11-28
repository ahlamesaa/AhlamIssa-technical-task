package com.luminous.integration.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.luminous.integration.exception.DigimonException;
import com.luminous.integration.model.Digimon;
import com.luminous.integration.service.impl.DefaultDigimonERPService;

@RestController

public class DigimonController {
	private static final String DIGIMON_CONTROLLER_SOME_THING_WRONG = "[DigimonController] SomeThing Wrong: %s";
	private static final Logger LOG = LoggerFactory.getLogger(DigimonController.class);
	@Autowired
	private DefaultDigimonERPService defaultDigimonService;

	/**
	 * Gets the all digimon.
	 *
	 * @return the all digimon @ the digimon exception
	 */
	@GetMapping("/all")
	public Digimon[] getAllDigimon() {
		System.out.println("api");
		Digimon[] digimons = null;
		try {
			digimons = defaultDigimonService.getAllDigimon();
			return digimons;
		} catch (DigimonException e) {
			LOG.error(DIGIMON_CONTROLLER_SOME_THING_WRONG, e.getMessage());
			return new Digimon[] {};
		}

	}

	/**
	 * Gets the all digimon by name.
	 *
	 * @param name the name
	 * @return the all digimon by name @ the digimon exception
	 */
	@GetMapping("/name/{name}")
	public Digimon[] getAllDigimonByName(@PathVariable(name = "name", required = true) String name) {
		Digimon[] digimons = null;
		try {
			digimons = defaultDigimonService.getAllDigimonByName(name);
			return digimons;
		} catch (DigimonException e) {
			LOG.error(DIGIMON_CONTROLLER_SOME_THING_WRONG, e.getMessage());
			return new Digimon[] {};
		}
	}

	/**
	 * Gets the all digimon by level.
	 *
	 * @param level the level
	 * @return the all digimon by level @ the digimon exception
	 */
	@GetMapping("/level/{level}")
	public Digimon[] getAllDigimonByLevel(@PathVariable(name = "level", required = true) String level) {
		Digimon[] digimons = null;
		try {
			digimons = defaultDigimonService.getAllDigimonByLevel(level);
			return digimons;
		} catch (DigimonException e) {
			LOG.error(DIGIMON_CONTROLLER_SOME_THING_WRONG, e.getMessage());
			return new Digimon[] {};
		}
	}
}
