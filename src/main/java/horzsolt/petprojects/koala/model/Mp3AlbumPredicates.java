package horzsolt.petprojects.koala.model;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 *
 * @author horzsolt
 */
public class Mp3AlbumPredicates {

    public static String cleanFolderName(String folder) {
        String cleanFolderName = folder;

        if (cleanFolderName.contains("_EP")) {
            cleanFolderName = cleanFolderName.substring(0, cleanFolderName.indexOf("_EP"));
        }

        if (cleanFolderName.contains("WEB-20")) {
            cleanFolderName = cleanFolderName.substring(0, cleanFolderName.indexOf("WEB-20"));
        }

        if (cleanFolderName.contains("-20")) {
            cleanFolderName = cleanFolderName.substring(0, cleanFolderName.indexOf("-20"));
        }

        if (cleanFolderName.contains("_20")) {
            cleanFolderName = cleanFolderName.substring(0, cleanFolderName.indexOf("_20"));
        }

        if (cleanFolderName.startsWith(("VA"))) {
            cleanFolderName = cleanFolderName.substring(2, cleanFolderName.length());
        }

        if (cleanFolderName.contains("[")) {
            cleanFolderName = cleanFolderName.substring(0, cleanFolderName.indexOf("["));
        }

        if (cleanFolderName.contains("(")) {
            cleanFolderName = cleanFolderName.substring(0, cleanFolderName.indexOf("("));
        }

        cleanFolderName = cleanFolderName.replaceAll("[\\s\\-]", "");
        cleanFolderName = cleanFolderName.replaceAll("[^\\P{Punct}(]+", "").replaceAll("\\d+[,/%]?\\d*", "");

        return cleanFolderName.toUpperCase();
    }

    private static boolean isFavourite(String folder) {

        String cleanFolderName = cleanFolderName(folder.toUpperCase());
        boolean result = Arrays.asList(Constants.favs).stream().anyMatch(item -> cleanFolderName.contains(item));
        
        if (result) {
            System.out.println("Favourite: " + cleanFolderName);
        } else {
            System.out.println("Non favourite: " + cleanFolderName);
        }
        
        return result;
    }

    public static Predicate<Mp3Album> is0day2() {
        return p -> isFavourite(p.getTitle());
    }
    
    public static Predicate<Mp3Album> isVA() {
        return p -> p.getTitle().startsWith("VA");
    }    
}
