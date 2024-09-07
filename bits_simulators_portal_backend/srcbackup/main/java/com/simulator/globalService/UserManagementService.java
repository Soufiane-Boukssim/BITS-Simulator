package com.simulator.globalService;

import com.simulator.config.GlobalVars;
import com.simulator.entities.UserManagement;
import com.simulator.globalController.AuthenticationController;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.models.UpdatePasswordRequest;
import com.simulator.models.UserDeleteAndGetRequest;
import com.simulator.repository.UserManagementRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Configuration
@EnableScheduling
public class UserManagementService {
    private Logger logger = LogManager.getLogger(AuthenticationController.class);
    @Autowired
    UserManagementRepository userManagementRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseApiJson<UserManagement> updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        String idRequest = "UP_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> user = userManagementRepository.findByUserCodeAndStatus(updatePasswordRequest.getUserCode(), "A");
        Date currentDate = new Date();

        // Create a Calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        // Add 90 days to the calendar
        calendar.add(Calendar.DAY_OF_MONTH, 90);

        // Convert the updated calendar back to a Date
        Date newDate = calendar.getTime();
        try {
            if (user.isPresent()) {
                if (passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.get().getPassword())) {
                    user.get().setPassword(AuthenticationService.passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
                    user.get().setDateStartPass(new Date());
                    user.get().setDateEndPass(getDateAfter(90));
                    user.get().setFirstConnection('N');
                    user.get().setExpiredPassword("N");
                    user.ifPresent(userManagementRepository::save);
                    return new ResponseApiJson<>(
                            idRequest,
                            GlobalVars.SUCCESS,
                            "Password updated successfully !"
                    );
                } else {

                    return new ResponseApiJson<>(
                            idRequest,
                            "001",
                            "Old password is incorrect !"
                    );
                }
            } else {
                return new ResponseApiJson<>(
                        idRequest,
                        GlobalVars.NOTEXIST,
                        "User does not exist or not approved !"
                );
            }
        } catch (Exception e) {
            logger.info("Exception of Update password " + e.getMessage());
            return new ResponseApiJson<>(
                    idRequest,
                    GlobalVars.EXCEPTIONSISSUE,
                    "some issues in updatePassword please check with your provider !"
            );
        }
    }


    public Date getDateAfter(int numberOfDays) {
        Date currentDate = new Date();

        // Create a Calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        // Add 90 days to the calendar
        calendar.add(Calendar.DAY_OF_MONTH, numberOfDays);

        // Convert the updated calendar back to a Date
        Date newDate = calendar.getTime();
        return newDate;
    }

    public ResponseApiJson<?> updateUser(UserManagement userManagement) {
        String idRequest = "UP_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> user = userManagementRepository.findById(userManagement.getUserCode());
        try {
            if (user.isPresent()) {
                UserManagement management = constructUserForUpdate(userManagement, user.get());
                userManagementRepository.save(management);
                return new ResponseApiJson<>(
                        idRequest,
                        GlobalVars.SUCCESS,
                        "User updated successfully !"
                );
            }
            return new ResponseApiJson<>(
                    idRequest,
                    GlobalVars.NOTEXIST,
                    "User does not exist !"
            );
        } catch (Exception e) {
            logger.info("update User Exception");
            e.printStackTrace();
            return new ResponseApiJson<>(
                    idRequest,
                    GlobalVars.EXCEPTIONSISSUE,
                    "some issues in updateUser please check with your provider !"
            );
        }
    }

    public UserManagement constructUserForUpdate(UserManagement userFrom, UserManagement userTo) {
        if (userFrom.getUserBankCode() != null && !userFrom.getUserBankCode().isEmpty()) {
            userTo.setUserBankCode(userFrom.getUserBankCode());
        }

        if (userFrom.getUserType() != null) {
            userTo.setUserType(userFrom.getUserType());
        }

        if (userFrom.getAddress() != null && !userFrom.getAddress().isEmpty()) {
            userTo.setAddress(userFrom.getAddress());
        }

        if (userFrom.getNumberOfTries() != null) {
            userTo.setNumberOfTries(userFrom.getNumberOfTries());
        }

        if (userFrom.getFirstConnection() != null) {
            userTo.setFirstConnection(userFrom.getFirstConnection());
        }

        if (userFrom.getNumberOfTriesAllowed() != null) {
            userTo.setNumberOfTriesAllowed(userFrom.getNumberOfTriesAllowed());
        }

        if (userFrom.getIpAddress() != null && !userFrom.getIpAddress().isEmpty()) {
            userTo.setIpAddress(userFrom.getIpAddress());
        }

        if (userFrom.getConnected() != null && !userFrom.getConnected().isEmpty()) {
            userTo.setConnected(userFrom.getConnected());
        }

        if (userFrom.getNbrSessionAllowed() != null) {
            userTo.setNbrSessionAllowed(userFrom.getNbrSessionAllowed());
        }

        if (userFrom.getNbrSessionConnected() != null) {
            userTo.setNbrSessionConnected(userFrom.getNbrSessionConnected());
        }

        if (userFrom.getLengthPassword() != null) {
            userTo.setLengthPassword(userFrom.getLengthPassword());
        }

        if (userFrom.getComplexityFlag() != null) {
            userTo.setComplexityFlag(userFrom.getComplexityFlag());
        }

        if (userFrom.getExpirationPassword() != null) {
            userTo.setExpirationPassword(userFrom.getExpirationPassword());
        }

        if (userFrom.getDateStartPass() != null) {
            userTo.setDateStartPass(userFrom.getDateStartPass());
        }

        if (userFrom.getDateEndPass() != null) {
            userTo.setDateEndPass(userFrom.getDateEndPass());
        }

        if (userFrom.getBlockAccess() != null) {
            userTo.setBlockAccess(userFrom.getBlockAccess());
        }

        if (userFrom.getLanguageCode() != null && !userFrom.getLanguageCode().isEmpty()) {
            userTo.setLanguageCode(userFrom.getLanguageCode());
        }

        if (userFrom.getLast4Pwd() != null && !userFrom.getLast4Pwd().isEmpty()) {
            userTo.setLast4Pwd(userFrom.getLast4Pwd());
        }

        if (userFrom.getEmail() != null && !userFrom.getEmail().isEmpty()) {
            userTo.setEmail(userFrom.getEmail());
        }

        if (userFrom.getMobileNumber() != null && !userFrom.getMobileNumber().isEmpty()) {
            userTo.setMobileNumber(userFrom.getMobileNumber());
        }

        if (userFrom.getForgotPwdRequest() != null) {
            userTo.setForgotPwdRequest(userFrom.getForgotPwdRequest());
        }

        if (userFrom.getStatus() != null && !userFrom.getStatus().isEmpty()) {
            userTo.setStatus(userFrom.getStatus());
        }

        if (userFrom.getSecretKey() != null && !userFrom.getSecretKey().isEmpty()) {
            userTo.setSecretKey(userFrom.getSecretKey());
        }

        if (userFrom.getAccessedModules() != null && !userFrom.getAccessedModules().isEmpty()) {
            userTo.setAccessedModules(userFrom.getAccessedModules());
        }

        return userTo;
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void checkExpiryUserAtMidnight() {
        logger.info("start Check expiry Password day: " + new Date());
        List<UserManagement> userManagements = userManagementRepository.findAll();
        for (UserManagement user :
                userManagements) {
            if (user.getDateEndPass().before(new Date()) && user.getExpiredPassword().equals("N")) {
                logger.info("User " + user.getUserCode() + " has been expired");
                user.setExpiredPassword("Y");
                userManagementRepository.save(user);
            }
        }
    }

    public ResponseApiJson<?> deleteUser(String userCode) {
        String idRequest = "DU_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<UserManagement> userManagement = userManagementRepository.findById(userCode);
            userManagement.ifPresent(userManagementRepository::delete);
            return new ResponseApiJson<>(
                    idRequest,
                    GlobalVars.SUCCESS,
                    "User deleted successfully !"
            );
        } catch (Exception e) {
            return new ResponseApiJson<>(
                    idRequest,
                    GlobalVars.EXCEPTIONSISSUE,
                    "deleteUser terminated with issue"
            );
        }
    }

    public ResponseApiJson<List<UserManagement>> getListOfUser(UserDeleteAndGetRequest userDeleteAndGetRequest) {
        String idRequest = "GU_" + new Date().getTime() + (Math.random() * 9999);
        List<UserManagement> userManagementList;
        try {
            if (userDeleteAndGetRequest.getStatus() != null) {
                userManagementList = userManagementRepository.findByStatus(userDeleteAndGetRequest.getStatus());
            }
            if (userDeleteAndGetRequest.getUserCode() != null) {
                userManagementList = userManagementRepository.findByUserCode(userDeleteAndGetRequest.getUserCode());
            }
            else {
                userManagementList = userManagementRepository.findAll();
            }
            return new ResponseApiJson<>(
                    idRequest,
                    GlobalVars.SUCCESS,
                    "get Users terminated with successfully",
                    userManagementList
            );
        } catch (Exception e) {
            return new ResponseApiJson<>(
                    idRequest,
                    GlobalVars.EXCEPTIONSISSUE,
                    "getListOfUsers terminated with issue"
            );
        }
    }
}
