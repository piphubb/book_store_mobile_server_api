//package com.dinsaren.oneposappserverapi.controllers.rest;
//
//import com.dinsaren.oneposappserverapi.constants.Constants;
//import com.dinsaren.oneposappserverapi.models.User;
//import com.dinsaren.oneposappserverapi.models.req.ChangePasswordReq;
//import com.dinsaren.oneposappserverapi.payload.response.MessageRes;
//import com.dinsaren.oneposappserverapi.repository.UserRepository;
//import com.dinsaren.oneposappserverapi.services.AuthenticationUtilService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/app/user")
//@Slf4j
//@PreAuthorize("hasRole('USER') or hasRole('CUSTOMER') or hasRole('ADMIN')")
//public class UserController {
//    private final AuthenticationUtilService authenticationUtilService;
//    private MessageRes messageRes;
//    private final PasswordEncoder encoder;
//    private final UserRepository userRepository;
//
//    public UserController(AuthenticationUtilService authenticationUtilService, PasswordEncoder encoder, UserRepository userRepository) {
//        this.authenticationUtilService = authenticationUtilService;
//        this.encoder = encoder;
//        this.userRepository = userRepository;
//    }
//
//    @PostMapping("/update")
//    public ResponseEntity<MessageRes> update(@RequestBody User req) {
//        messageRes = new MessageRes();
//        log.info("Intercept Update user req {}", req);
//        try {
//            User user = userRepository.getById(req.getId());
//            if (user.getUsername() == null) {
//                messageRes.getUserNotFound();
//                return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
//            }
//            if (!user.getUsername().equals(req.getUsername())) {
//                if (userRepository.existsByUsernameAndStatus(req.getUsername(), Constants.STATUS_ACTIVE)) {
//                    messageRes.setNameAlreadyUse();
//                    return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
//                }
//            }
//            if (!user.getEmail().equals(req.getEmail())) {
//                if (userRepository.existsByEmailAndStatus(req.getEmail(), Constants.STATUS_ACTIVE)) {
//                    messageRes.setEmailAlreadyUse();
//                    return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
//                }
//            }
//            if (!user.getPhoneNumber().equals(req.getPhoneNumber())) {
//                if (userRepository.existsByPhoneNumberAndStatus(req.getPhoneNumber(), Constants.STATUS_ACTIVE)) {
//                    messageRes.setPhoneAlreadyUse();
//                    return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
//                }
//            }
//            req.setPassword(user.getPassword());
//            userRepository.save(req);
//            messageRes.setMessageCreateSuccess("Update User successfully!");
//            return new ResponseEntity<>(messageRes, HttpStatus.OK);
//        } catch (Throwable e) {
//            log.info("Error Update user req ", e);
//            messageRes.internalServerError(null);
//            return new ResponseEntity<>(messageRes, HttpStatus.INTERNAL_SERVER_ERROR);
//        } finally {
//            log.info("Update user req final result {}", messageRes);
//        }
//    }
//
//    @PostMapping("/change/password")
//    public ResponseEntity<MessageRes> changePassword(@RequestBody ChangePasswordReq req) {
//        messageRes = new MessageRes();
//        User user;
//        try {
//            if (!req.getPassword().equals(req.getConfirmPassword())) {
//                messageRes.setConfirmPasswordNotMatch();
//                return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
//            }
//            user = authenticationUtilService.checkUser();
//            log.info("Intercept user change password req {}", req);
//            if (null == user) {
//                messageRes.getUserNotFound();
//                return new ResponseEntity<>(messageRes, HttpStatus.BAD_GATEWAY);
//            }
//            boolean checkPassword = encoder.matches(req.getOldPassword(), user.getPassword());
//            if (!checkPassword) {
//                messageRes.setOldPasswordNotMatch();
//                return new ResponseEntity<>(messageRes, HttpStatus.BAD_REQUEST);
//            }
//            Optional<User> userUpdate = userRepository.findByPhoneNumberAndStatus(user.getPhoneNumber(), Constants.STATUS_ACTIVE);
//            if (userUpdate.isEmpty()) {
//                messageRes.getUserNotFound();
//                return new ResponseEntity<>(messageRes, HttpStatus.BAD_GATEWAY);
//            }
//            if (!userUpdate.get().getPassword().equals(req.getPassword())) {
//                userUpdate.get().setPassword(encoder.encode(req.getPassword()));
//            }
//            userUpdate.get().setStatus(Constants.STATUS_ACTIVE);
//            userRepository.save(userUpdate.get());
//            messageRes.setMessageSuccess(user);
//        } catch (Throwable e) {
//            log.info("While user change password ", e);
//        } finally {
//            log.info("Final user change password response {}", messageRes);
//        }
//        return new ResponseEntity<>(messageRes, HttpStatus.OK);
//    }
//
//    @PostMapping("/{id}")
//    public ResponseEntity<Object> getById(@RequestBody BasePostReq req, @PathVariable("id") Integer id) {
//        try {
//            MessageRes messageRes = new MessageRes();
//            log.info("Intercept get user by req {}", req);
//            List<String> statusList = new ArrayList<>();
//            statusList.add("ACT");
//            statusList.add("DEL");
//            User userFind = userRepository.findByIdAndStatusIn(id, statusList);
//            messageRes.setMessageSuccess(userFind);
//            return new ResponseEntity<>(messageRes, HttpStatus.OK);
//        } catch (Throwable e) {
//            log.info("Error get user by id req ", e);
//            messageRes.internalServerError(null);
//            return new ResponseEntity<>(messageRes, HttpStatus.INTERNAL_SERVER_ERROR);
//        } finally {
//            log.info("Get user by id final result {}", messageRes);
//        }
//    }
//
//}
