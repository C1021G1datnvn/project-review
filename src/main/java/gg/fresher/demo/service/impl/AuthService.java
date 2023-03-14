//package gg.fresher.demo.service.impl;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import error.InvalidCredentialException;
//import gg.fresher.demo.dtos.req.LoginReq;
//import gg.fresher.demo.dtos.req.RegisterReq;
//import gg.fresher.demo.dtos.res.LoginRes;
//import gg.fresher.demo.entities.User;
//import gg.fresher.demo.entities.UserRole;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import security.CustomUserDetails;
//import security.JWT.TokenProvider;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//    
//    @Autowired
//    private TokenProvider tokenProvider;
//    
//    @Autowired
//    private AuthenticationManagerBuilder authenticationManagerBuilder;
//    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserService userService;
//    
//    @Autowired
//    private UserRoleService userRoleService;
//    
//    public Long register(final RegisterReq req) {
//        log.info("Register user");
//
//        User user = userService.save(
//                User.builder()
//                        .username(req.getUsername())
//                        .password(passwordEncoder.encode(req.getPassword()))
//                        .name(req.getName())
//                        .build());
//
//        userRoleService.save(UserRole.builder()
//                .userId(user.getId())
//                .roleId(req.getRoleId())
//                .build());
//        return user.getId();
//    }
//
//    public LoginRes login(LoginReq req) {
//        String username = req.getUsername();
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                username,
//                req.getPassword()
//        );
//        CustomUserDetails customUserDetails;
//
//        try {
//            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//            customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//        } catch (BadCredentialsException e) {
//            throw new InvalidCredentialException();
//        }
//
//        String roles = customUserDetails.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        String accessToken = tokenProvider.createToken(customUserDetails.getId(), username, roles);
//
//        return LoginRes.builder()
//                .accessToken(accessToken)
//                .roles(Set.of(roles.split(",")))
//                .build();
//    }
//}
