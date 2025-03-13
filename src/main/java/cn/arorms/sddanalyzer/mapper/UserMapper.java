package cn.arorms.sddanalyzer.mapper;

import cn.arorms.sddanalyzer.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT COUNT(*) FROM users")
    int countUsers();

    @Select("SELECT * FROM users WHERE username = #{username}")
    @Result(column = "user_id", property = "userId")
    UserEntity getUserByName(String username);

    @Insert("INSERT INTO users (username, password) VALUES (#{username}, #{password})")
    void insertUser(UserEntity user);
}
