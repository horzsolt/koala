package horzsolt.petprojects.koala.db;

import java.time.LocalDate;

public class DownloadProperties {
	
	public LocalDate getStart() {
		return start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	private LocalDate start;
	private LocalDate end;

}
