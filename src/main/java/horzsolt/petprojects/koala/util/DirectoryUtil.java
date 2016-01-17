package horzsolt.petprojects.koala.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import horzsolt.petprojects.KoalaApplication;
import horzsolt.petprojects.KoalaApplication.KoalaContext;

public class DirectoryUtil {
	
	@Autowired
	private KoalaContext koalaContext;
	
    public List<String> GetDaysBetweenDates(LocalDate start, LocalDate end) {

		if (koalaContext == null) {
			koalaContext = KoalaApplication.context.getBean(KoalaContext.class);
		}
		
        int numberOfDaysBetween = Math.toIntExact(ChronoUnit.DAYS.between(start, end));
        List<String> result = new ArrayList<>(numberOfDaysBetween);

        LocalDate tempDate = start;

        while (end.isAfter(tempDate)) {
            result.add(addZero(tempDate.getMonth().getValue()) + addZero(tempDate.getDayOfMonth()));
            tempDate = tempDate.plusDays(1);
        }

        result.add(addZero(tempDate.getMonth().getValue()) + addZero(tempDate.getDayOfMonth()));
        return result.stream().map(item -> koalaContext.getDAY_HOME() + item + "/").collect(Collectors.toList());
    }

    private String addZero(int value) {

        String valueAsString = Integer.toString(value);
        return value < 10 ? "0" + valueAsString : valueAsString;
    }    
}
