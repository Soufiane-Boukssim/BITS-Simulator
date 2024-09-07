package com.simulator.globalController;


import com.simulator.authentification.AuthenticationRequest;
import com.simulator.authentification.AuthenticationResponse;
import com.simulator.authentification.RegisterRequest;
import com.simulator.config.GlobalVars;
import com.simulator.config.LogoutService;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.globalService.AuthenticationService;
import com.simulator.globalService.UserManagementService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/api/o/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private static final Logger logger = LogManager.getLogger(AuthenticationController.class);
  private final AuthenticationService service;
  private final LogoutService logoutService;
  @Autowired
  UserManagementService userManagementService;

  @PostMapping("/register")
  public ResponseApiJson<AuthenticationResponse> register(
      @RequestBody RegisterRequest request,HttpServletRequest httpServletRequest
  ) {
    String s = service.getClientIpAddress(httpServletRequest);
    request.setIpAddress(s);
    return service.register(request);
  }
  @PostMapping("/authenticate")
  public ResponseApiJson<?> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    logger.info("welcome to market place your userCode is: "+request.getUserCode());
    return service.authenticate(request);
  }

  @PostMapping("/signOut")
  public ResponseApiJson<String> signout() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    logoutService.logout(request, response, authentication);
    return new ResponseApiJson<>(
            "REQSignout",
            GlobalVars.SUCCESS,
            "User logged out successfully !"
    );
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }
}
