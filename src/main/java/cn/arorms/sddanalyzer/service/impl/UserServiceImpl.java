// src/main/java/cn/arorms/SDDAnalyzer/service/impl/UserServiceImpl.java
package cn.arorms.sddanalyzer.service.impl;

import cn.arorms.sddanalyzer.entity.User;
import cn.arorms.sddanalyzer.mapper.UserMapper;
import cn.arorms.sddanalyzer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}