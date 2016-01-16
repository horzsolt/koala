package horzsolt.petprojects.koala.model;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

public class KoalaModel {
	
	@Value("${koala.ip}")
    private String ipAddress;
	@Value("${koala.port}")
    private String port;
	@Value("${koala.username}")
    private String userName;
	@Value("${koala.password}")	
    private String password;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
