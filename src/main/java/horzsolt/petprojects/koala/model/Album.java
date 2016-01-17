package horzsolt.petprojects.koala.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import horzsolt.petprojects.KoalaApplication;
import horzsolt.petprojects.KoalaApplication.KoalaContext;

public class Album {

	private String title;
	private int year;
	private String genre;
	private int bitrate;
	private int fileCount;
	private long size;
	private List<MusicFile> mp3Files = new ArrayList<>();
	private String ftpDirectory;
	private long sumSize;

	private static final Logger logger = LogManager.getLogger("Album");
	
	@Autowired
	private KoalaContext koalaContext;	

	public void write() {
		
		try {
			
			if (koalaContext == null) {
				logger.debug("context is null");
				koalaContext = KoalaApplication.context.getBean(KoalaContext.class);
			}
			
			XMLEncoder encoder = new XMLEncoder(
					new BufferedOutputStream(new FileOutputStream(koalaContext.getSerializationRoot() + title + ".xml")));
			encoder.writeObject(this);
			encoder.close();
			
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	public static Album read(String filename) throws Exception {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		Album o = (Album) decoder.readObject();
		decoder.close();
		return o;
	}

	public long getSumSize() {
		return sumSize;
	}

	public Album() {
		sumSize = 0;
	}

	public String toString() {
		return ftpDirectory + " " + title;
	}

	public void addMusicFile(MusicFile musicFile) {
		mp3Files.add(musicFile);
		sumSize += musicFile.getSize();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getBitrate() {
		return bitrate;
	}

	public void setBitrate(int bitrate) {
		this.bitrate = bitrate;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public List<MusicFile> getMp3Files() {
		return mp3Files;
	}

	public void setMusicFiles(List<MusicFile> musicFiles) {
		this.mp3Files = musicFiles;
	}

	public String getFtpDirectory() {
		return ftpDirectory;
	}

	public void setFtpDirectory(String ftpDirectory) {
		this.ftpDirectory = ftpDirectory;
	}
}
