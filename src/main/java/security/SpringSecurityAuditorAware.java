//package security;
//
//import java.util.Objects;
//import java.util.Optional;
//
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.stereotype.Component;
//
//import helper.SecurityHelper;
//@Component
//public class SpringSecurityAuditorAware implements AuditorAware<String> {
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        String userName = SecurityHelper.setCurrentUsername();
//        return Optional.of(Objects.nonNull(userName) ? userName : "SYSTEM");
//    }
//}
