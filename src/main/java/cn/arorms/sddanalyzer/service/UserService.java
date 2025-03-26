// src/main/java/cn/arorms/SDDAnalyzer/service/UserService.java
package cn.arorms.sddanalyzer.service;

import cn.arorms.sddanalyzer.entity.User;

public interface UserService {
    User getUserById(Integer id);
    User getUserByUsername(String username);
}