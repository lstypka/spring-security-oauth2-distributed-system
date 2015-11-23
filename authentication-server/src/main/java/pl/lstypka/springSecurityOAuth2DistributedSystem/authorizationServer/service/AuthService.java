package pl.lstypka.springSecurityOAuth2DistributedSystem.authorizationServer.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.lstypka.springSecurityOAuth2DistributedSystem.authorizationServer.bo.SecurityUser;
import pl.lstypka.springSecurityOAuth2DistributedSystem.authorizationServer.dto.UserDto;
import pl.lstypka.springSecurityOAuth2DistributedSystem.authorizationServer.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("authService")
public class AuthService implements UserDetailsService {

	@Override
	public SecurityUser loadUserByUsername(String username) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		if("admin".equals(username)) {
			authorities.add(()-> "ROLE_ADMIN");
			return new SecurityUser(1L, username, "s3cr3t", authorities);
		}
		if("user".equals(username)) {
			authorities.add(()-> "ROLE_USER");
			return new SecurityUser(2L, username, "s3cr3t", authorities);
		}

		throw new UserNotFoundException("User %s not found".format(username));
	};

	public UserDto getLoggedUser() {
		SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new UserDto(securityUser.getUserNo(), securityUser.getUsername(), securityUser.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()));
	};

}