package com.simulator.globalController;


import com.simulator.authentification.AuthenticationRequest;
import com.simulator.authentification.RegisterRequest;
import com.simulator.config.GlobalVars;
import com.simulator.config.LogoutService;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.globalService.AuthenticationService;
import com.simulator.globalService.UserManagementService;
import jakarta.annotation.PostConstruct;
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

  //  HAMZA 12.02.2024
  /*@PostMapping("/register")
  public ResponseApiJson<AuthenticationResponse> register(
      @RequestBody RegisterRequest request,HttpServletRequest httpServletRequest
  ) {
    String s = service.getClientIpAddress(httpServletRequest);
    request.setIpAddress(s);
    return service.register(request);
  }*/

  @PostConstruct
  public void initAdmin() {
    service.initAdmin();
  }


  @PostMapping("/addUser")
  public  ResponseApiJson addUser(@RequestBody RegisterRequest userManagementRequest){
    ResponseApiJson<?> apiJson = service.register(userManagementRequest);
    return apiJson;
  }
  // end HAMZA 12.02.2024
  @PostMapping("/authenticate")
  public ResponseApiJson<?> authenticate(
      @RequestBody AuthenticationRequest request, HttpServletRequest req
  ) {
    logger.info("welcome to  your userCode is: "+request.getUserCode());
    //logger.info("Logged User contoller " + SecurityContextHolder.getContext().getAuthentication().getName());
    String ipAddress = req.getRemoteAddr();
    logger.info("IP address: " + ipAddress);
    return service.authenticate(request,ipAddress);
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
