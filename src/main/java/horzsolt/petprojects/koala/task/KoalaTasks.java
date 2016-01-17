package horzsolt.petprojects.koala.task;

import org.springframework.scheduling.annotation.Async;

import horzsolt.petprojects.koala.model.KoalaForm;
import horzsolt.petprojects.koala.util.DirectoryUtil;
import horzsolt.petprojects.koala.util.FtpUtil;

public class KoalaTasks {
	
	@Async
	public static void doListing(KoalaForm koalaForm) {
		DirectoryUtil directoryUtil = new DirectoryUtil();
		FtpUtil ftpUtil = new FtpUtil();
		directoryUtil.GetDaysBetweenDates(koalaForm.getStartDate(), koalaForm.getEndDate()).stream()
				.forEach(item -> ftpUtil.directoryToAlbum(item).stream().forEach(album -> album.write()));
	}
}
