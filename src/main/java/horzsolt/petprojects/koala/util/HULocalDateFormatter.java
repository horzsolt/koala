package horzsolt.petprojects.koala.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class HULocalDateFormatter implements Formatter<LocalDate> {
	public static final String HU_PATTERN = "yyyy.MM.dd";

	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		return LocalDate.parse(text, DateTimeFormatter.ofPattern(HU_PATTERN));
	}

	@Override
	public String print(LocalDate object, Locale locale) {
		return DateTimeFormatter.ofPattern(HU_PATTERN).format(object);
	}
}
