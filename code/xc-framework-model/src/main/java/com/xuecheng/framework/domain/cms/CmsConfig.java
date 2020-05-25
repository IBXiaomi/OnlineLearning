package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * cms_config数据库字段
 *
 * @author 吧嘻小米
 * @date 2020/05/03
 */
@Data
@ToString
@Document(collection = "cms_config")
public class CmsConfig {

    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 模型,存储的数据
     */
    private List<CmsConfigModel> model;

}
