package com.saipal.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.saipal.config.security.JwtAuthFilter;
import com.saipal.entity.Person;
import com.saipal.entity.PersonType;
import com.saipal.entity.UserInfo;
import com.saipal.entity.UserType;
import com.saipal.repository.PersonRepository;
import com.saipal.repository.PersonTypeRepository;
import com.saipal.repository.UserInfoRepository;
import com.saipal.repository.UserTypeRepository;
import com.saipal.utils.UniqueIdGenerator;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public JwtAuthFilter JwtFilter() {
		return new JwtAuthFilter();
	}

	@Bean
	SecurityFilterChain dafultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(withDefaults())
				.authorizeHttpRequests(
						request -> request.requestMatchers("/public/api/**", "/", "/WEB-INF/**","/uploads/**", "/image/**")
								.permitAll().anyRequest().authenticated())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(JwtFilter(), UsernamePasswordAuthenticationFilter.class)
				.httpBasic(httpBasic -> httpBasic.disable());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public CommandLineRunner initData(PersonTypeRepository personTypeRepository, PersonRepository personRepository,
			UserTypeRepository userTypeRepository, UserInfoRepository userInfoRepository,
			PasswordEncoder passwordEncoder, UniqueIdGenerator uniqueIdGenerator) {

		return args -> {

			if (userTypeRepository.findAll().isEmpty()) {
				UserType userType = new UserType();
				userType.setId(UniqueIdGenerator.generateUniqueId());
				userType.setUserType("DEVELOPER");
				userType.setApproved(1);
				userTypeRepository.save(userType);

				PersonType pt = new PersonType();
				pt.setId(UniqueIdGenerator.generateUniqueId());
				pt.setPersonType("DEVELOPER");
				pt.setCode("D01");
				personTypeRepository.save(pt);

				Person person = new Person();
				person.setFullName("Saipal Technologies");
				person.setAddress("Kathmandu");
				person.setEmail("info@saipal.org");
				person.setGender(3);
				person.setInstitution("Saipal Technologies");
				person.setMobileNo("9999999999");
				person.setPersonType(pt);
				person.setId(UniqueIdGenerator.generateUniqueId());
				personRepository.save(person);

				UserInfo user = new UserInfo();
				user.setPerson(person);
				user.setUserType(userType);
				user.setLoginName("saipal");
				user.setPassword(passwordEncoder.encode("saipal123"));
				user.setId(UniqueIdGenerator.generateUniqueId());
				user.setUserStatus(1);
				userInfoRepository.save(user);

			}
		};

	}

}
