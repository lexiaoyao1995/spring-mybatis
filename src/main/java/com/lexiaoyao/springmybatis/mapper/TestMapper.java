package com.lexiaoyao.springmybatis.mapper;

import com.lexiaoyao.springmybatis.annos.Mapper;
import com.lexiaoyao.springmybatis.annos.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TestMapper {

    @Select("test")
    String test();

}
