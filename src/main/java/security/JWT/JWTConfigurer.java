//package security.JWT;
//
//import javax.servlet.Filter;
//
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
//    private final TokenProvider tokenProvider;
//
//    public JWTConfigurer(final TokenProvider tokenProvider) {
//        this.tokenProvider = tokenProvider;
//    }
//
//    @Override
//    public void configure(final HttpSecurity http) {
//        JWTFilter customFilter = new JWTFilter(tokenProvider);
//        http.addFilterBefore((Filter) customFilter, UsernamePasswordAuthenticationFilter.class);
//        http.addFilterAfter((Filter) customFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
