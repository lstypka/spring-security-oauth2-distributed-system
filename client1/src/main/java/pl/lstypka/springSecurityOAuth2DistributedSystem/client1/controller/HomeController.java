package pl.lstypka.springSecurityOAuth2DistributedSystem.client1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Lukasz Stypka on 2015-11-23.
 */
@RestController
public class HomeController {

    @RequestMapping("/time")
    public String hello() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @RequestMapping("/user")
    public Object getUser() {
        Object principals = SecurityContextHolder.getContext().getAuthentication();
    return principals ;

    }

}
