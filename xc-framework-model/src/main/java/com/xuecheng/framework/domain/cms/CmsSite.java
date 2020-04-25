package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * cms_site的collection属性类
 *
 * @author 吧嘻小米
 * @date 2020/04/25
 */
@Data
@ToString
@Document(collection = "cms_site")
public class CmsSite {

    /**
     * 站点id
     */
    @Id
    private String siteId;
    /**
     * 站点名称
     */
    private String siteName;
    /**
     * 站点ip
     */
    private String siteDomain;
    /**
     * 站点port
     */
    private String sitePort;
    /**
     * 站点的WebPath
     */
    private String siteWebPath;
    /**
     * 创建时间
     */
    private Date siteCreateTime;

}
