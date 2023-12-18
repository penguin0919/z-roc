package com.test.roc.core.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExcelListener
 * @Description TODO
 * @Author cosmo
 * @DATE 2023-05-24 17:02
 * @Version 1.0
 */
@Slf4j
public class ExcelListener<T> extends AnalysisEventListener<T> {

    private List<T> data = new ArrayList<>();

    @Override
    public void invoke(T o, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSONObject.toJSONString(o));
        data.add(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 入库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", data.size());
        //这个方法自己实现  能完成保存数据入库即可
        log.info("存储数据库成功！");
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
