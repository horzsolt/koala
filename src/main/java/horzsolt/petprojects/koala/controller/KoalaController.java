package horzsolt.petprojects.koala.controller;

import java.time.LocalDate;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import horzsolt.petprojects.koala.db.ConfigPersister;
import horzsolt.petprojects.koala.db.DownloadProperties;
import horzsolt.petprojects.koala.model.KoalaForm;
import horzsolt.petprojects.koala.util.HULocalDateFormatter;

@Controller
public class KoalaController {

	private static Logger logger = LogManager.getLogger(KoalaController.class.getName());

	@ModelAttribute("dateFormat")
	public String localeFormat(Locale locale) {
		return HULocalDateFormatter.HU_PATTERN;
	}

	@RequestMapping("/")
	public String getRootPage(KoalaForm koalaForm) {

		logger.debug("------------------------------ Start");
		ConfigPersister persister = new ConfigPersister();

		DownloadProperties props = persister.readConfig("fav");
		koalaForm.setStartDate(props.getStart());
		koalaForm.setEndDate(props.getEnd());

		koalaForm.setStartDate(LocalDate.now());
		koalaForm.setEndDate(LocalDate.now().plusDays(5));

		return "rootPage";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String saveProfile(@Valid KoalaForm koalaForm, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "rootPage";
		}
		
		System.out.println("save ok" + koalaForm);
		return "redirect:/";
	}

}
