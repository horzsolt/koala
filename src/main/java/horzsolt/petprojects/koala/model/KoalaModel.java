package horzsolt.petprojects.koala.model;

import org.springframework.beans.factory.annotation.Value;

public class KoalaModel {
	@Value("${koala.ip}")
    private String ipAddress;
	@Value("${koala.port}")
    private String port;
	@Value("${koala.username}")
    private String userName;
	@Value("${koala.password}")	
    private String password;
	
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
