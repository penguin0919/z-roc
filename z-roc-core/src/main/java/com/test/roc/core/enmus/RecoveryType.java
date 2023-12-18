package com.test.roc.core.enmus;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * 回收明细类型
 *
 * @author z-Roc
 * @date 2023-12-04 15:17
 **/
@Getter
public enum RecoveryType implements IEnum<Integer> {
    /**
     * 枚举值
     */
    MANAGE(1, "管理端"),
    CLIENT(2, "客户端");


    private final Integer type;
    private final String desc;

    RecoveryType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.type;
    }
}
