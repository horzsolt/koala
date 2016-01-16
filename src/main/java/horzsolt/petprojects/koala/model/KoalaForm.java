package horzsolt.petprojects.koala.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class KoalaForm {
	
	@NotNull
    private LocalDate startDate;
	@NotNull
    private LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate dStart) {
        this.startDate = dStart;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate dEnd) {
        this.endDate = dEnd;
    }
}
