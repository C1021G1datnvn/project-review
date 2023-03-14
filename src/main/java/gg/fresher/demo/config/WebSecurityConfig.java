//package gg.fresher.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import enums.RoleType;
//import lombok.RequiredArgsConstructor;
//import security.JWT.JWTConfigurer;
//import security.JWT.TokenProvider;
//
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//    
//    @Autowired
//    private TokenProvider tokenProvider;
//    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http)
//            throws Exception {
//        http.cors();
//        http.csrf().disable();
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.authorizeRequests()
//                .antMatchers("/api/auth/**").permitAll()
//                .antMatchers("/api/admin/**").hasAuthority(RoleType.ADMIN.name())
//                .antMatchers("/api/**").authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .apply(securityConfigurerAdapter());
//    }
//
//    private JWTConfigurer securityConfigurerAdapter() {
//        return new JWTConfigurer(tokenProvider);
//    }
//}
