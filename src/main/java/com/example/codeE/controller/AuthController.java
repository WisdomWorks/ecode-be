package com.example.codeE.controller;

import com.example.codeE.helper.JWTUtils;
import com.example.codeE.request.auth.CheckOTPRequest;
import com.example.codeE.request.auth.SendOTPRequest;
import com.example.codeE.request.user.LoginRequest;
import com.example.codeE.request.user.UserAuthenRequest;
import com.example.codeE.request.user.forgetPasswordRequest;
import com.example.codeE.service.authentication.AuthenService;
import com.example.codeE.service.exercise.common.SessionExerciseService;
import com.example.codeE.service.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenService authenService;
    @Autowired
    private JWTUtils jwtHelper;
    @Autowired
    private UserService userService;
    @Autowired
    private SessionExerciseService SessionExerciseService;


    @PostMapping
    @RequestMapping(value = "/login/user", method = RequestMethod.POST)
    public ResponseEntity<?> signInUser(@RequestBody LoginRequest signInRequest, HttpServletResponse response) {
        var result = authenService.signIn(signInRequest, response);
        if (result.getStatusCode() == 200) return ResponseEntity.status(HttpStatus.OK).body(result);
        else if(result.getStatusCode() == 403) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
        else if(result.getStatusCode() == 404) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        else if (result.getStatusCode() == 401) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
    @PostMapping
    @RequestMapping(value = "/login/admin", method = RequestMethod.POST)
    public ResponseEntity<?> signInAdmin(@RequestBody LoginRequest signInRequest, HttpServletResponse response) {
        var result = authenService.signInAdmin(signInRequest, response);
        if (result.getStatusCode() == 200) return ResponseEntity.status(HttpStatus.OK).body(result);
        else if(result.getStatusCode() == 403) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
        else if(result.getStatusCode() == 404) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        else if (result.getStatusCode() == 401) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
    @PostMapping
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> Logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        clearAuthenticationTokens(request, response);
        var responseResult = new UserAuthenRequest();
        responseResult.setStatusCode(200);
        responseResult.setMessage("Logout Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responseResult);
    }

    @GetMapping
    @RequestMapping(value = "/check-session/user", method = RequestMethod.GET)
    public ResponseEntity<?> checkSessionUser(HttpServletRequest request, HttpServletResponse response) {
        String token = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessTokenUser".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
            var checkExpired = jwtHelper.isTokenExpired(token);
            if(checkExpired){
                var responseResult = new UserAuthenRequest();
                responseResult.setStatusCode(401);
                responseResult.setMessage("token has been expired, please login again");
                responseResult.setError("UNAUTHORIZED");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseResult);
            }
            var responseResult = this.authenService.createNewSessionUser(token, response);
            this.createNewLoginSession(request, response);
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        }else {
            var responseResult = new UserAuthenRequest();
            responseResult.setStatusCode(401);
            responseResult.setMessage("No user has been saved in cookie, please login again");
            responseResult.setError("UNAUTHORIZED");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseResult);
        }
    }
    @GetMapping
    @RequestMapping(value = "/check-session/admin", method = RequestMethod.GET)
    public ResponseEntity<?> checkSessionAdmin(HttpServletRequest request, HttpServletResponse response) {
        String token = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessTokenAdmin".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
            var checkExpired = jwtHelper.isTokenExpired(token);
            if(checkExpired){
                var responseResult = new UserAuthenRequest();
                responseResult.setStatusCode(401);
                responseResult.setMessage("token has been expired, please login again");
                responseResult.setError("UNAUTHORIZED");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseResult);
            }
            var responseResult = this.authenService.createNewSessionAdmin(token, response);
            return ResponseEntity.status(HttpStatus.OK).body(responseResult);
        }else {
            var responseResult = new UserAuthenRequest();
            responseResult.setStatusCode(401);
            responseResult.setMessage("No user has been saved in cookie, please login again");
            responseResult.setError("UNAUTHORIZED");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseResult);
        }
    }

    @PostMapping
    @RequestMapping(value = "send-otp", method = RequestMethod.POST)
    public ResponseEntity<?> sendOTP(@RequestBody SendOTPRequest otpRequest, HttpServletResponse response) throws NoSuchMethodException {
        String userName = otpRequest.getUserName();
        if (userName.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "User Name can not blank"));
        this.authenService.SendForgetPasswordOTP(userName, response);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Send OTP success, please check your mail box"));
    }

    @PostMapping
    @RequestMapping(value = "check-otp", method = RequestMethod.POST)
    public ResponseEntity<?> checkOTP(@RequestBody CheckOTPRequest otpRequest, HttpServletRequest request) {
        String userId = getUserId(request);
        String OTP = otpRequest.getOtp();
        if (userId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(Map.of("message","You request has been time out"));
        }
        if (this.authenService.CheckOTP(OTP, userId, request)) {
            var userInfo = this.userService.getById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(userInfo);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "OTP does not match"));
        }
    }
    @PutMapping
    @RequestMapping(value = "change-password", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserPassword(@RequestBody forgetPasswordRequest changePasswordRequest, HttpServletRequest request, HttpServletResponse response) {
        if (this.authenService.updatePassword(changePasswordRequest.getUserId(), changePasswordRequest.getPassword())) {
            this.clearForgetPasswordCookie(request, response, changePasswordRequest.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","Change password successful."));
        }else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "some thing wrong, when request change password!"));
    }

    private String getUserId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("currentUserRestPassword".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    private void clearForgetPasswordCookie(HttpServletRequest request, HttpServletResponse response, String userId) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessTokenUser".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                if ((userId + "_OTP").equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
    private void clearAuthenticationTokens(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessTokenUser".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                if ("accessTokenAdmin".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                if ("LoginSessionId".equals(cookie.getName())) {
                    this.SessionExerciseService.removeSession(response, request);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
    private void createNewLoginSession(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("LoginSessionId".equals(cookie.getName())) {
                    this.SessionExerciseService.removeSession(response, request);
                    cookie.setMaxAge(3600 * 6);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
}
