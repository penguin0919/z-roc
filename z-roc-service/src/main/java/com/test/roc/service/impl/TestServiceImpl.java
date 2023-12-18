package com.test.roc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.roc.model.entity.test.Test;
import com.test.roc.repository.TestMapper;
import com.test.roc.service.TestService;
import org.springframework.stereotype.Service;

/**
 * 测试实现类
 *
 * @author z-Roc
 * @date 2023-12-15 16:39
 **/
@Service("zRocTestService")
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {
}
