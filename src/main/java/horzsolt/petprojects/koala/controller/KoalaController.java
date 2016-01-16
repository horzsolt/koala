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
import horzsolt.petprojects.koala.model.KoalaModel;
import horzsolt.petprojects.koala.util.HULocalDateFormatter;

@Controller
public class KoalaController {

	private static Logger logger = LogManager.getLogger("KoalaController");

	@ModelAttribute("dateFormat")
	public String localeFormat(Locale locale) {
		return HULocalDateFormatter.HU_PATTERN;
	}

	@RequestMapping("/")
	public String getRootPage(KoalaModel model) {

		logger.debug("Start");
		KoalaModel koalaModel = new KoalaModel();
		ConfigPersister persister = new ConfigPersister();

		DownloadProperties props = persister.readConfig("fav");
		// koalaModel.setStartDate(props.getStart());
		// koalaModel.setEndDate(props.getEnd());

		koalaModel.setStartDate(LocalDate.now());
		koalaModel.setStartDate(LocalDate.now().plusDays(5));

		// model.addAttribute("model", koalaModel);
		// model.addAttribute("message", "Hello from the controller");
		return "rootPage";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String saveProfile(@Valid KoalaModel koalaForm, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "rootPage";
		}
		
		System.out.println("save ok" + koalaForm);
		return "redirect:/";
	}

}
