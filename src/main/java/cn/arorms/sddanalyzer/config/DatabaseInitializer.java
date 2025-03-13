package cn.arorms.sddanalyzer.config;

import cn.arorms.sddanalyzer.entity.UserEntity;
import cn.arorms.sddanalyzer.mapper.UserMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public DatabaseInitializer(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public void run(String... args) {
        System.out.println("🔄 正在检查数据库表是否存在...");

        // 自动创建表
        String createTableSql = """
                CREATE TABLE IF NOT EXISTS users (
                    user_id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) NOT NULL UNIQUE,
                    password VARCHAR(255) NOT NULL
                );
                """;
        jdbcTemplate.execute(createTableSql);
        System.out.println("✅ 表结构检查完成。");

        // 检查是否有用户数据
        if (userMapper.countUsers() == 0) {
            System.out.println("✅ 数据库为空，正在初始化默认用户...");

            // 插入默认用户
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword("123456");
            userMapper.insertUser(admin);

            System.out.println("🎉 默认用户初始化完成！");
        } else {
            System.out.println("✅ 数据库已有数据，无需初始化。");
        }
    }
}
