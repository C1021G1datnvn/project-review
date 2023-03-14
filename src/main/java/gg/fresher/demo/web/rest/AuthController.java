//package gg.fresher.demo.web.rest;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import gg.fresher.demo.dtos.req.LoginReq;
//import gg.fresher.demo.dtos.req.RegisterReq;
//import gg.fresher.demo.service.ContinentService;
//import gg.fresher.demo.service.impl.AuthService;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//    @Autowired
//    private AuthService service;
//    
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@Valid @RequestBody final RegisterReq req) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(service.register(req));
//    }
//    
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody final LoginReq req) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(service.login(req));
//    }
//
//}
