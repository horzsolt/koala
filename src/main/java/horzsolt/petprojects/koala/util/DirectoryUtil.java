package horzsolt.petprojects.koala.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config.properties")
public class DirectoryUtil {
	
	@Value("${koala.DAY_HOME}")
    private static String DAY_HOME;
    
    public static List<String> GetDaysBetweenDates(String start, String end) {

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);

        int numberOfDaysBetween = Math.toIntExact(ChronoUnit.DAYS.between(startDate, endDate));
        List<String> result = new ArrayList<>(numberOfDaysBetween);

        LocalDate tempDate = startDate;

        while (endDate.isAfter(tempDate)) {
            result.add(addZero(tempDate.getMonth().getValue()) + addZero(tempDate.getDayOfMonth()));
            tempDate = tempDate.plusDays(1);
        }

        result.add(addZero(tempDate.getMonth().getValue()) + addZero(tempDate.getDayOfMonth()));
        return result.stream().map(item -> DAY_HOME + item + "/").collect(Collectors.toList());
    }

    private static String addZero(int value) {

        String valueAsString = Integer.toString(value);
        return value < 10 ? "0" + valueAsString : valueAsString;
    }    
}
