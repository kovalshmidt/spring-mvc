package com.epam.springmvc.security;

import com.epam.springmvc.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Setter
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isRegisteredUser = false;
        boolean isBookingManager = false;
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());


        if (authorities.contains("BOOKING_MANAGER")) {
            isBookingManager = true;
        }

        if (authorities.contains("REGISTERED_USER")) {
            isRegisteredUser = true;
        }

        if (isRegisteredUser) {
            int phoneUserId = userService.findByEmail(authentication.getName()).getPhoneUserId();
            return "/user/" + phoneUserId;
        } else if (isBookingManager) {
            return "/users";
        } else {
            throw new IllegalStateException();
        }
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
