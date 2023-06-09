package gg.fresher.demo.security.jwt;
//package security.JWT;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.util.StringUtils;
//
//public class JWTFilter {
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//    private final TokenProvider tokenProvider;
//
//    public JWTFilter(final TokenProvider tokenProvider) {
//        this.tokenProvider = tokenProvider;
//    }
//
////    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String jwt = resolveToken(httpServletRequest);
//
//        if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
//            Authentication authentication = this.tokenProvider.getAuthentication(jwt);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    private String resolveToken(final HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}
