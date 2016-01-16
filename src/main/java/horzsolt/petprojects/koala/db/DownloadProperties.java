package horzsolt.petprojects.koala.db;

import java.util.Date;

public class DownloadProperties {
	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	private Date start;
	private Date end;

}
