package com.Online.ParkigManagement.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "spring.app")
public class jwtconfig {
    private long jwtExpirationMs;

    public long getJwtExpirationMs() {
        return jwtExpirationMs;
    }
    public void setJwtExpirationMs(long jwtExpirationMs) {
        this.jwtExpirationMs = jwtExpirationMs;
    }
}
