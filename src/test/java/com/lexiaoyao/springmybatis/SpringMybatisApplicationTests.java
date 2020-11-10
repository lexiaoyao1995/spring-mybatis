package com.lexiaoyao.springmybatis;

import com.lexiaoyao.springmybatis.mapper.OneMapper;
import com.lexiaoyao.springmybatis.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringMybatisApplicationTests {

    @Autowired
    private OneMapper oneMapper;

    @Autowired
    private TestMapper testMapper;

    @Test
    void contextLoads() {
        System.out.println(testMapper.test());
        System.out.println(oneMapper.getOne());
    }

}
