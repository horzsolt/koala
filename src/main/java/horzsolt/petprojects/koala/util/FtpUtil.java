package horzsolt.petprojects.koala.util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import horzsolt.petprojects.KoalaApplication;
import horzsolt.petprojects.KoalaApplication.KoalaContext;
import horzsolt.petprojects.koala.model.Album;
import horzsolt.petprojects.koala.model.MusicFile;

public class FtpUtil {
	
	private FTPClient ftp;
	private static Logger logger = LogManager.getLogger("FtpUtil");

	@Autowired
	private KoalaContext koalaContext;
	
	private void init() throws IOException {

		if ((ftp == null) || (!ftp.isConnected())) {

			if (koalaContext == null) {
				koalaContext = KoalaApplication.context.getBean(KoalaContext.class);
			}
			
			ftp = new FTPClient();
			ftp.connect(koalaContext.getIpAddress(), Integer.parseInt(koalaContext.getPort()));
			ftp.login(koalaContext.getUserName(), koalaContext.getPassword());
			ftp.enterLocalPassiveMode();
		}
	}

	public List<Album> directoryToAlbum(String directory) {

		try {

			init();
			List<Album> result = new ArrayList<>();

			FTPFile[] folders = ftp.listDirectories(directory);

			for (FTPFile folder : folders) {

				if (folder.getName().length() > 3) {

					Album album = new Album();

					String folderName = folder.getName();
					logger.debug("Scanning folder: " + folderName);

					album.setFtpDirectory(directory + folderName);
					album.setTitle(folderName);

					FTPFile[] files = ftp.listFiles(directory + folderName);

					for (FTPFile file : files) {

						if (file.getName().length() > 3) {

							if (file.isDirectory()) {
								StringTokenizer sk = new StringTokenizer(file.getName().replaceAll("-", ""), "[]");

								if (sk.countTokens() == 4) {

									album.setGenre(sk.nextToken().toLowerCase());

									try {
										album.setYear(Integer.parseInt(sk.nextToken()));
										album.setBitrate(Integer.parseInt(sk.nextToken().replaceAll("[^0-9]", "")));
									} catch (Exception e) {
										album.setYear(LocalDate.now().getYear());
										album.setBitrate(256);
									}
								} else {
									logger.warn("CountTokens is not equals with 4: " + sk.countTokens() + " "
											+ file.getName());
									System.out.println("CountTokens is not equals with 4: " + sk.countTokens() + " "
											+ file.getName());
								}
							} else {

								MusicFile musicFile = new MusicFile();
								musicFile.setTitle(file.getName());
								musicFile.setSize(file.getSize());

								int pos = file.getName().lastIndexOf(".");
								musicFile.setExtension(file.getName().substring(pos, pos + 4));

								album.addMusicFile(musicFile);
							}
						}
					}

					result.add(album);
				}
			}

			return result;

		} catch (IOException ex) {
			logger.error("directoryToAlbum exception at: ", ex);
			return null;
		}
	}
}
