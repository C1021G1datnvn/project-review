//package helper;
//
//
//import java.util.Optional;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//
//public final class SecurityHelper {
//    private SecurityHelper() {
//    }
//
//    public static Long getCurrentUserId() {
//        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
//                .filter(authentication -> authentication.getPrincipal() instanceof CustomUserDetails)
//                .map(authentication -> ((CustomUserDetails) authentication.getPrincipal()).getId())
//                .orElseThrow(() -> new ObjectNotFoundException("currentUserId"));
//    }
//
//    public static String getCurrentUsername() {
//        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
//                .filter(authentication -> authentication.getPrincipal() instanceof CustomUserDetails)
//                .map(authentication -> ((CustomUserDetails) authentication.getPrincipal()).getUsername())
//                .orElse(null);
//    }
//}
