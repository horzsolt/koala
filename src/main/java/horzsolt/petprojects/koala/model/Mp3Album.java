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

/**
 *
 * @author horzsolt
 */
public class Mp3Album {

    private String title;
    private int year;
    private String genre;
    private int bitrate;
    private int fileCount;
    private long size;
    private List<Mp3File> mp3Files = new ArrayList<>();
    private String ftpDirectory;
    private long sumSize;
    
    private static final Logger logger = LogManager.getLogger("Mp3Album");

    public void write() {
        try {
            XMLEncoder encoder
                    = new XMLEncoder(
                            new BufferedOutputStream(
                                    new FileOutputStream(Constants.serializationRoot + title + ".xml")));
            encoder.writeObject(this);
            encoder.close();
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    public static Mp3Album read(String filename) throws Exception {
        XMLDecoder decoder
                = new XMLDecoder(new BufferedInputStream(
                                new FileInputStream(filename)));
        Mp3Album o = (Mp3Album) decoder.readObject();
        decoder.close();
        return o;
    }

    public long getSumSize() {
        return sumSize;
    }

    public Mp3Album() {
        sumSize = 0;
    }

    public String toString() {
        return ftpDirectory + " " + title;
    }

    public void addMp3File(Mp3File mp3File) {
        mp3Files.add(mp3File);
        sumSize += mp3File.getSize();
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

    public List<Mp3File> getMp3Files() {
        return mp3Files;
    }

    public void setMp3Files(List<Mp3File> mp3Files) {
        this.mp3Files = mp3Files;
    }

    public String getFtpDirectory() {
        return ftpDirectory;
    }

    public void setFtpDirectory(String ftpDirectory) {
        this.ftpDirectory = ftpDirectory;
    }
}
