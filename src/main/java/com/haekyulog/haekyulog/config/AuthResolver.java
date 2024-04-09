//package com.haekyulog.haekyulog.config;
//
//import com.haekyulog.haekyulog.config.data.UserSession;
//import com.haekyulog.haekyulog.exception.Unauthorized;
//import com.haekyulog.haekyulog.repository.SessionRepository;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//@Slf4j
//@RequiredArgsConstructor
//public class AuthResolver implements HandlerMethodArgumentResolver {
//
//    private final SessionRepository sessionRepository;
//    private final AppConfig appConfig;
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.getParameterType().equals(UserSession.class);
//    }
//
//    //토큰방식
////    @Override
////    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
////        String accessToken = webRequest.getHeader("Authorization");
////
////        if (accessToken == null || accessToken.equals("")) {
////            throw new Unauthorized();
////        }
////
////        Session session = sessionRepository.findByAccessToken(accessToken)
////                .orElseThrow(Unauthorized::new);
////
////        return new UserSession(session.getUsers().getId());
////    }
//    //쿠키방식
////    @Override
////    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
////        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
////
////        if (servletRequest == null) {
////            log.error("servletRequest null");
////            throw new Unauthorized();
////        }
////
////        Cookie[] cookies = servletRequest.getCookies();
////
////        if (cookies.length == 0) {
////            log.error("no cookie");
////            throw new Unauthorized();
////        }
////
////        String accessToken = cookies[0].getValue();
////
////        Session session = sessionRepository.findByAccessToken(accessToken)
////                .orElseThrow(Unauthorized::new);
////
////        return new UserSession(session.getUsers().getId());
////    }
//    //JWT
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
//
//        log.info(">>> {}" , appConfig.toString());
//
//        String jws = webRequest.getHeader("Authorization");
//
//        if (jws == null || jws.equals("")) {
//            throw new Unauthorized();
//        }
//
//        try {
//            Jws<Claims> claimsJws = Jwts.parser()
//                    //.verifyWith(decodeKey)
//                    .setSigningKey(appConfig.getJwtKey())
//                    .build()
//                    .parseSignedClaims(jws);
//
//            String userId = claimsJws.getBody().getSubject();
//            return new UserSession(Long.parseLong(userId));
//        } catch (JwtException e) {
//            throw new Unauthorized();
//        }
//    }
//}
