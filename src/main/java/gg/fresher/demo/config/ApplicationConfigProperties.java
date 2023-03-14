//package gg.fresher.demo.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
//public class ApplicationConfigProperties {
//    private Jwt jwt;
//    private Cors cors;
//
//    @Getter
//    @Setter
//    public static class Jwt {
//        private String secretKey;
//        private Long expiration;
//    }
//
//    @Getter
//    @Setter
//    public static class Cors {
//        private String allowedOrigin;
//    }
//}
