package horzsolt.petprojects.koala.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import horzsolt.petprojects.KoalaApplication;
import horzsolt.petprojects.KoalaApplication.KoalaContext;

public class ConfigPersister {

	private static Logger logger = LogManager.getLogger(ConfigPersister.class.getName());
	private Connection connection = null;
	
	@Autowired
	private KoalaContext koalaContext;
	
	private void connect() {

		try {

			//koalaApplication.KoalaContext
			if (koalaContext == null) {
				logger.debug("context is null");
				koalaContext = KoalaApplication.context.getBean(KoalaContext.class);
			}
			
			String jdbc_url = koalaContext.getJdbc_Url();
			
			if (connection != null) {
				if (!connection.isClosed()) {
					return;
				}
			}

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbc_url);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	public DownloadProperties readConfig(String type) {

		connect();
		PreparedStatement pstmt = null;
		DownloadProperties result = new DownloadProperties();

		try {

			pstmt = connection.prepareStatement("select * from koaladownloader.koalaconfig where type=?");
			pstmt.setString(1, type);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			result.setEnd(rs.getDate("end").toLocalDate());
			result.setStart(rs.getDate("start").toLocalDate());

			rs.close();

		} catch (Exception ex) {
			logger.error(ex);
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception ex) {
					logger.error(ex);
				}
			}
		}

		return result;
	}

	public void saveConfig(String type, Date start, Date end) {

		connect();
		PreparedStatement pstmt = null;

		try {

			pstmt = connection
					.prepareStatement("update koaladownloader.koalaconfig set start = ?, end = ? where type=?");
			pstmt.setDate(1, start);
			pstmt.setDate(2, end);
			pstmt.setString(3, type);

			pstmt.execute();

		} catch (Exception ex) {
			logger.error(ex);
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception ex) {
					logger.error(ex);
				}
			}
		}
	}
}
