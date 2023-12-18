package com.test.roc.core.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author z-Roc
 * @date 2022-07-18 15:50
 */
@Getter
@AllArgsConstructor
public enum OperateTypeEnum {
    /**
     * 类型
     */
    CREATE("create", "创建"),
    SELECT("select", "查询"),
    UPDATE("update", "修改"),
    DELETE("delete", "删除");

    private final String type;
    private final String desc;

}
