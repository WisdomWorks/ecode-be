package com.example.codeE.service.authentication;

import com.example.codeE.constant.Constant;
import com.example.codeE.helper.EmailHelper;
import com.example.codeE.helper.JWTUtils;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.course.CourseTeacherResponse;
import com.example.codeE.request.user.LoginRequest;
import com.example.codeE.request.user.UserAuthenRequest;
import com.example.codeE.security.BCryptPassword;
import com.example.codeE.service.course.CourseService;
import com.example.codeE.service.exercise.common.SessionExerciseService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

@Service
public class AuthenImpl implements  AuthenService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseService courseService;
    JWTUtils jwtHelper = new JWTUtils();
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserAuthenRequest signIn(LoginRequest signInRequest, HttpServletResponse response) {
        UserAuthenRequest userResponse = new UserAuthenRequest();
        try{
            userResponse = this.checkLoginRequest(signInRequest);
            if(userResponse.getStatusCode() == 404)
            {
                return userResponse;
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUserName(),signInRequest.getPassword()));
            var user = userRepository.findUserByUserName(signInRequest.getUserName());
            if(user.getRole().equals("admin")){
                userResponse.setStatusCode(403);
                userResponse.setMessage("Something wrong when login");
                userResponse.setError("User is admin");
                return userResponse;
            }
            userResponse = this.setResponseResult(user);
            ResponseCookie cookie = ResponseCookie.from("accessTokenUser", userResponse.getToken())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(3600 * 4)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            cookie = ResponseCookie.from("LoginSessionId", UUID.randomUUID().toString())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(3600 * 4)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }catch (Exception e){
            userResponse.setError(e.getMessage());
            if(e.getMessage().equals("Bad credentials")){
                userResponse.setStatusCode(401);
                userResponse.setMessage("Password wrong");
                return userResponse;
            }
            userResponse.setStatusCode(500);
            userResponse.setMessage("Something wrong when login");
        }
        return userResponse;
    }

    @Override
    public UserAuthenRequest createNewSessionAdmin(String token, HttpServletResponse response) {
        UserAuthenRequest userResponse = new UserAuthenRequest();
        try{
            var userName = this.jwtHelper.extractUserName(token);
            if(userName.isBlank())
                throw new Exception();
            var user = userRepository.findUserByUserName(userName);
            userResponse = this.setResponseResult(user);
            ResponseCookie cookie = ResponseCookie.from("accessTokenAdmin", userResponse.getToken())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(3600)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }catch (Exception e){
            userResponse.setError(e.getMessage());
            if(e.getMessage().equals("Bad credentials")){
                userResponse.setStatusCode(401);
                userResponse.setMessage("Password wrong");
                return userResponse;
            }
            userResponse.setStatusCode(500);
            userResponse.setMessage("Something wrong when get new session");
        }
        return userResponse;
    }
    @Override
    public UserAuthenRequest createNewSessionUser(String token, HttpServletResponse response) {
        UserAuthenRequest userResponse = new UserAuthenRequest();
        try{
            var userName = this.jwtHelper.extractUserName(token);
            if(userName.isBlank())
                throw new Exception();
            var user = userRepository.findUserByUserName(userName);
            userResponse = this.setResponseResult(user);
            ResponseCookie cookie = ResponseCookie.from("accessTokenUser", userResponse.getToken())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(3600)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }catch (Exception e){
            userResponse.setError(e.getMessage());
            if(e.getMessage().equals("Bad credentials")){
                userResponse.setStatusCode(401);
                userResponse.setMessage("Password wrong");
                return userResponse;
            }
            userResponse.setStatusCode(500);
            userResponse.setMessage("Something wrong when get new session");
        }
        return userResponse;
    }

    @Override
    public void SendForgetPasswordOTP(String userName, HttpServletResponse response) throws NoSuchMethodException {
        var user = this.userRepository.findUserByUserName(userName);
        System.out.println(user.getEmail());
        if (user == null) throw new NoSuchMethodException("No user found by: " + userName);
        String OTP = RandomNumberGenerator();
        try {
            String messageContent = String.format(Constant.SEND_OTP_MAIL_TEMPLATE, user.getName(), OTP);
            EmailHelper emailHelper = new EmailHelper();
            emailHelper.sendMail(
                    "RESET PASSWORD OTP", messageContent, user.getEmail()
            );
            ResponseCookie cookie = ResponseCookie.from(user.getUserId() + "_OTP", OTP)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(180)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            cookie = ResponseCookie.from("currentUserRestPassword", user.getUserId())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(180)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean CheckOTP(String OTP, String userId, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = userId + "_OTP";
                if (cookieName.equals(cookie.getName())) {
                    if (OTP.equals(cookie.getValue()))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean updatePassword(String userId, String password) {
        try {
            var user = this.userRepository.findUserByUserId(userId);
            password = BCryptPassword.passwordEncoder(password);
            user.setPassword(password);
            this.userRepository.save(user);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    private String RandomNumberGenerator() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(9) + 1; // Generates random numbers between 1 and
            result.append(Integer.toString(randomNumber));
        }
        return result.toString();
    }
    @Override
    public UserAuthenRequest signInAdmin(LoginRequest signInRequest, HttpServletResponse response) {
        UserAuthenRequest userResponse = new UserAuthenRequest();
        try{
            userResponse = this.checkLoginRequest(signInRequest);
            System.out.println(userResponse);
            if(userResponse.getStatusCode() == 404)
            {
                return userResponse;
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUserName(),signInRequest.getPassword()));
            var user = userRepository.findUserByUserName(signInRequest.getUserName());
            if(!user.getRole().equals("admin")){
                userResponse.setStatusCode(403);
                userResponse.setMessage("Something wrong when login");
                userResponse.setError("User is not admin");
                return userResponse;
            }
            userResponse = this.setResponseResult(user);
            ResponseCookie cookie = ResponseCookie.from("accessTokenAdmin", userResponse.getToken())
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(3600)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }catch (Exception e){
            userResponse.setError(e.getMessage());
            if(e.getMessage().equals("Bad credentials")){
                userResponse.setStatusCode(400);
                userResponse.setMessage("Password wrong");
                return userResponse;
            }
            userResponse.setStatusCode(500);
            userResponse.setMessage("Something wrong when login");
        }
        return userResponse;
    }


    private UserAuthenRequest setResponseResult(User user){
        var result = new UserAuthenRequest();
        var jwt = jwtHelper.generateToken(user);
        var refreshToken = jwtHelper.generateRefreshToken(new HashMap<>(), user);
        result.setStatusCode(200);
        result.setToken(jwt);
        result.setUser(user);
        if(user.getRole().equals("student")){
            var course = this.courseService.getCourseByStudentId(user.getUserId());
            if(!course.isEmpty()) {
                var courseResponseList = new ArrayList<CourseTeacherResponse>();
                for(var item: course){
                    var teacher = this.userRepository.getTeacherInCourse(item.getCourseId());
                    var temp = new CourseTeacherResponse(
                            item.getCourseId(),
                            item.getCourseName(),
                            item.getSemester(),
                            item.getDescription(),
                            item.getCreatedDate(),
                            item.getUpdatedDate(),
                            teacher
                    );
                    courseResponseList.add(temp);
                }
                result.setCourses(courseResponseList);
            }
        }
        if(user.getRole().equals("teacher")){
            var course = this.courseService.getCourseByTeacherId(user.getUserId());
            if(!course.isEmpty()){
                var courseResponseList = new ArrayList<CourseTeacherResponse>();
                for(var item: course){
                    var teacher = this.userRepository.getTeacherInCourse(item.getCourseId());
                    var temp = new CourseTeacherResponse(
                            item.getCourseId(),
                            item.getCourseName(),
                            item.getSemester(),
                            item.getDescription(),
                            item.getCreatedDate(),
                            item.getUpdatedDate(),
                            teacher
                    );
                    courseResponseList.add(temp);
                }
                result.setCourses(courseResponseList);
            }
        }
        result.setRefreshToken(refreshToken);
        result.setExpirationTime("1 hour");
        result.setMessage("Sign In successful");
        return result;
    }
    private UserAuthenRequest checkLoginRequest(LoginRequest signInRequest){
        var response = new UserAuthenRequest();
        var user = this.userRepository.findUserByUserName(signInRequest.getUserName());
        if (user == null){
            response.setStatusCode(404);
            response.setMessage("User does not exist");
            response.setError("Can not find this user by this user name: "+ signInRequest.getUserName());
            return response;
        }
        response.setStatusCode(200);
        return response;
    }
}

