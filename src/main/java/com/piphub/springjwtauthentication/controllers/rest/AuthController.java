package com.piphub.springjwtauthentication.controllers.rest;

import com.piphub.springjwtauthentication.constants.Constants;
import com.piphub.springjwtauthentication.exception.TokenRefreshException;
import com.piphub.springjwtauthentication.models.*;
import com.piphub.springjwtauthentication.payload.request.LogOutReq;
import com.piphub.springjwtauthentication.payload.request.LoginReq;
import com.piphub.springjwtauthentication.payload.request.RegisterReq;
import com.piphub.springjwtauthentication.payload.request.TokenRefreshReq;
import com.piphub.springjwtauthentication.payload.response.JwtRes;
import com.piphub.springjwtauthentication.payload.response.MessageRes;
import com.piphub.springjwtauthentication.repository.RoleRepository;
import com.piphub.springjwtauthentication.repository.UserRepository;
import com.piphub.springjwtauthentication.security.jwt.JwtUtils;
import com.piphub.springjwtauthentication.security.services.RefreshTokenService;
import com.piphub.springjwtauthentication.security.services.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/oauth")
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    @Value("${spring.otp.sms.enbaled}")
    private String otpEnable;
    private MessageRes messageRes;
    @Value("${spring.otp.sms.template}")
    protected String smsTemplate;
    @Value("${post.free.app.jwtExpirationMs}")
    private String expiredToken;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginReq req) {
        log.info("Intercept request oath token {}", req);
        MessageRes messageRes = new MessageRes();
        if (req.getPassword().equals("") || null == req.getPassword() || "".equals(req.getPassword()) || req.getPhoneNumber().equals("") || null == req.getPhoneNumber()) {
            messageRes.badRequest("Error: Invalid username and password");
            return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
        }
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getPhoneNumber(), req.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String jwt = jwtUtils.generateJwtToken(userDetails);

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

            JwtRes jwtRes = new JwtRes();
            jwtRes.setExpiresIn(Integer.parseInt(expiredToken));
            jwtRes.setAccessToken(jwt);
            jwtRes.setRefreshToken(refreshToken.getToken());
            jwtRes.setTokenType(Constants.BEARER);
            Optional<User> findUser = userRepository.findByUsername(userDetails.getUsername());
            findUser.get().setPassword("******");
            findUser.ifPresent(jwtRes::setUser);
            return ResponseEntity.ok(jwtRes);
        } finally {
            log.info("While response oath token final result {}", messageRes);
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody LogOutReq req) {
        refreshTokenService.deleteByUserId(req.getUserId());
        return ResponseEntity.ok(new MessageRes("Log out successful!", null));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageRes> registerUser(@RequestBody RegisterReq req) {
        MessageRes messageRes = new MessageRes();
        log.info("Intercept create account req {}", req);
        try {
            if (req.getFirstName().equals("") || req.getLastName().equals("") || req.getPhoneNumber().equals("") || req.getEmail().equals("") || req.getPassword().equals("") || null == req.getPassword() || null == req.getConfirmPassword() || "".equals(req.getPassword()) || "".equals(req.getConfirmPassword())) {
                messageRes.badRequest("");
                return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
            }
            if (!req.getConfirmPassword().equals(req.getPassword())) {
                messageRes.setConfirmPasswordNotMatch();
                return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
            }
            if (userRepository.existsByUsernameAndStatus(req.getUsername(), Constants.STATUS_ACTIVE)) {
                messageRes.setNameAlreadyUse();
                return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
            }

            if (userRepository.existsByEmailAndStatus(req.getEmail(), Constants.STATUS_ACTIVE)) {
                messageRes.setEmailAlreadyUse();
                return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
            }

            if (userRepository.existsByPhoneNumberAndStatus(req.getPhoneNumber(), Constants.STATUS_ACTIVE)) {
                messageRes.setPhoneAlreadyUse();
                return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
            }

            User user = new User(req.getUsername(), req.getEmail(), encoder.encode(req.getPassword()), req.getPhoneNumber());
            Set<Role> roles = new HashSet<>();
            Role role = roleRepository.findByName(UserRole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(role);
            user.setRoles(roles);
            user.setFirstName(req.getFirstName());
            user.setLastName(req.getLastName());
            user.setStatus(Constants.STATUS_ACTIVE);
            user.setChangePassword(Constants.No);
            user.setProfile(req.getProfile());
            userRepository.save(user);
            messageRes.setMessageCreateSuccess("User Open Account successfully!");
            return new ResponseEntity<>(messageRes, HttpStatus.OK);
        } catch (Throwable e) {
            log.info("Error open account req ", e);
            messageRes.internalServerError(null);
            return new ResponseEntity<>(messageRes, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            log.info("Open account req final result {}", messageRes);
        }
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshReq request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken).map(refreshTokenService::verifyExpiration).map(RefreshToken::getUser).map(user -> {
            String token = jwtUtils.generateTokenFromUsername(user.getUsername());
            JwtRes jwtRes = new JwtRes();
            jwtRes.setExpiresIn(Integer.parseInt(expiredToken));
            jwtRes.setAccessToken(token);
            jwtRes.setRefreshToken(requestRefreshToken);
            jwtRes.setTokenType(Constants.BEARER);
            user.setPassword("******");
            jwtRes.setUser(user);
            return ResponseEntity.ok(jwtRes);
        }).orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!"));
    }


}
