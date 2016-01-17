package horzsolt.petprojects.koala.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import horzsolt.petprojects.koala.db.ConfigPersister;
import horzsolt.petprojects.koala.db.DownloadProperties;
import horzsolt.petprojects.koala.model.KoalaForm;
import horzsolt.petprojects.koala.task.KoalaTasks;
import horzsolt.petprojects.koala.util.HULocalDateFormatter;

@Controller
public class KoalaController {

	private static Logger logger = LogManager.getLogger(KoalaController.class.getName());

	@ModelAttribute("dateFormat")
	private String localeFormat(Locale locale) {
		return HULocalDateFormatter.HU_PATTERN;
	}

	@RequestMapping("/")
	private String getRootPage(KoalaForm koalaForm) {

		ConfigPersister persister = new ConfigPersister();

		DownloadProperties props = persister.readConfig("0day2");
		koalaForm.setStartDate(props.getStart());
		koalaForm.setEndDate(props.getEnd());

		return "rootPage";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	private String saveProfile(@Valid KoalaForm koalaForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "rootPage";
		}

		KoalaTasks.doListing(koalaForm);

		return "redirect:/status";
	}
	
	@RequestMapping(value = "/status")
	private String fetchStatus(Model model) {
		model.addAttribute("message", "Valami");
		
		return "resultPage";
	}

}
