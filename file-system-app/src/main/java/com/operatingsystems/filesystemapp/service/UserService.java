package com.operatingsystems.filesystemapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.operatingsystems.filesystemapp.model.ActionResult;

public interface UserService {

    ActionResult createUser(final String username, final String password) throws JsonProcessingException;

    ActionResult userLogIn(final String username, final String password);

}
