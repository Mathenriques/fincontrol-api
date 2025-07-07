package com.fincontrol.service;

import com.fincontrol.model.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User save(User user);
    User getUserData(ObjectId poid);
    User editUserData(User newUserData);
}
