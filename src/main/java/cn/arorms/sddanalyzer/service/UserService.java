package cn.arorms.sddanalyzer.service;

import cn.arorms.sddanalyzer.entity.UserEntity;
import org.springframework.stereotype.Service;
import cn.arorms.sddanalyzer.mapper.UserMapper;

@Service
public class UserService {
    private final UserMapper userMapper;
    public UserService (UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public UserEntity getUserByName(String username) {
        return userMapper.getUserByName(username);
    }
}
