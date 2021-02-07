package com.botmasterzzz.controller.dao;

import com.botmasterzzz.controller.entity.UserEntity;

public interface UserDAO {

    UserEntity loadUser(long id);

}
