package top.tangtian.springbean.springbean02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author tangtian
 * @version 1.0
 * @className User
 * @description @Component注解表示该类会作为一个组件类，并告知Spring要为这个类创建bean。
 * 自动化装配，其从两个角度实现自动化装配：
 * 组件扫描：Spring会自动发现应用上下文中的bean
 * 自动装配：Spring自动满足bean之间的依赖
 * @date 2020/11/16 7:08 AM
 **/
@Component
public class User2 {
    private static final Logger LOG = LoggerFactory.getLogger(User2.class);
    private String name = "张三";
    private int id = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
