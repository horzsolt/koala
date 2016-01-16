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

import horzsolt.petprojects.koala.model.KoalaModel;
import horzsolt.petprojects.koala.model.Mp3Album;
import horzsolt.petprojects.koala.model.Mp3File;

public class FtpUtil {
	
	private static FTPClient ftp;
	private static Logger logger = LogManager.getLogger("FtpUtil");

	private static void init() throws IOException {

		if ((ftp == null) || (!ftp.isConnected())) {
			KoalaModel model = new KoalaModel();
			ftp = new FTPClient();
			ftp.connect(model.getIpAddress(), Integer.parseInt(model.getPort()));
			ftp.login(model.getUserName(), model.getPassword());
			ftp.enterLocalPassiveMode();
		}
	}

	public static List<Mp3Album> ftpDirectoryToMp3Album(String directory) {

		try {

			init();
			List<Mp3Album> result = new ArrayList<>();

			FTPFile[] mp3Folders = ftp.listDirectories(directory);

			for (FTPFile mp3Folder : mp3Folders) {

				if (mp3Folder.getName().length() > 3) {

					Mp3Album mp3Album = new Mp3Album();

					String folderName = mp3Folder.getName();
					logger.debug("Scanning folder: " + folderName);

					mp3Album.setFtpDirectory(directory + folderName);
					mp3Album.setTitle(folderName);

					FTPFile[] files = ftp.listFiles(directory + folderName);

					for (FTPFile file : files) {

						if (file.getName().length() > 3) {

							if (file.isDirectory()) {
								StringTokenizer sk = new StringTokenizer(file.getName().replaceAll("-", ""), "[]");

								if (sk.countTokens() == 4) {

									mp3Album.setGenre(sk.nextToken().toLowerCase());

									try {
										mp3Album.setYear(Integer.parseInt(sk.nextToken()));
										mp3Album.setBitrate(Integer.parseInt(sk.nextToken().replaceAll("[^0-9]", "")));
									} catch (Exception e) {
										mp3Album.setYear(LocalDate.now().getYear());
										mp3Album.setBitrate(256);
									}
								} else {
									logger.warn("CountTokens is not equals with 4: " + sk.countTokens() + " "
											+ file.getName());
									System.out.println("CountTokens is not equals with 4: " + sk.countTokens() + " "
											+ file.getName());
								}
							} else {

								Mp3File mp3File = new Mp3File();
								mp3File.setTitle(file.getName());
								mp3File.setSize(file.getSize());

								// System.out.println("Getting extension: " +
								// file.getName());
								int pos = file.getName().lastIndexOf(".");
								mp3File.setExtension(file.getName().substring(pos, pos + 4));

								// System.out.println("Extension: " +
								// mp3File.getExtension());

								mp3Album.addMp3File(mp3File);
							}
						}
					}

					result.add(mp3Album);
				}
			}

			return result;

		} catch (IOException ex) {
			logger.error("ftpDirectoryToMp3Album exception at: ", ex);
			return null;
		}
	}
}
