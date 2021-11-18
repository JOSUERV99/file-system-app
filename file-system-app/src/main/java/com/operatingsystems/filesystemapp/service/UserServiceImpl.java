package com.operatingsystems.filesystemapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import com.operatingsystems.filesystemapp.handler.FileHandler;
import com.operatingsystems.filesystemapp.handler.FileUtils;
import com.operatingsystems.filesystemapp.handler.JSONUtils;
import com.operatingsystems.filesystemapp.handler.ModelUtils;
import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.Drive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Component(value = "userService")
public class UserServiceImpl implements UserService {

    /**
     * Create the user, generate the json file with the credentials of the given user
     * @param username the name of the user, it has to be an unique username
     * @param password the password of the user
     * @return ActionResult, with result of the success of the user creation
     */
    @Override
    public ActionResult createUser(String username, String password) throws JsonProcessingException {
        return null;
    }

    /**
     * Get the user log in, generate the json file with the credentials of the given user
     * @param username the name of the user
     * @param password the password of the user
     * @return ActionResult, with result of the success of the log in action
     */
    @Override
    public ActionResult userLogIn(String username, String password) {

        //TODO: Verify user credentials
        return ActionResult.instance().setSuccess(true);
    }
}
