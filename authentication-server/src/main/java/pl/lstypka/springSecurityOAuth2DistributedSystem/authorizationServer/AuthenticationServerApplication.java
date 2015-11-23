package pl.lstypka.springSecurityOAuth2DistributedSystem.authorizationServer;
/**
 * Created by Lukasz Stypka on 2015-11-20.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AuthenticationServerApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(AuthenticationServerApplication.class, args);
    }

}