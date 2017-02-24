package com.example.dddkj.ywtx.Entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/2/21 9:52
 */

public class MyClassifySection extends SectionEntity<ClassifyR> {

    public MyClassifySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MyClassifySection(ClassifyR classifyR) {
        super(classifyR);
    }
}
