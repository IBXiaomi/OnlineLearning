package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 页面模板数据字段
 *
 * @author 吧嘻小米
 * @date 2020/04/28
 */
@Data
@ToString
@Document(collection = "cms_template")
public class CmsTemplate {

    /**
     * 站点id
     */
    private String siteId;
    /**
     * 模板id
     */
    @Id
    private String templateId;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板参数
     */
    private String templateParameter;

    /**
     * 模板文件id
     */
    private String templateFileId;
}
