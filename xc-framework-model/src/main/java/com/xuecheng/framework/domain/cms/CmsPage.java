package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * CMS页面管理实体类@Data注解完成getter，setter等
 * 方法的注解，@Document是mongodb数据库集合的注解
 *
 * @author wjw
 * @since JDK1.8
 */
@Data
@ToString
@Document(collection = "cms_page")
public class CmsPage {
    /**
     * 站点id
     */
    private String siteId;
    /**
     * 页面id
     */
    @Id
    private String pageId;

    /**
     * 页面名称
     */
    private String pageName;
    /**
     * 别名
     */
    private String pageAliase;
    /**
     * 访问地址
     */
    private String pageWebPath;
    /**
     * 页面参数
     */
    private String pageParameter;
    /**
     * 页面的物理路径
     */
    private String pagePhysicalPath;
    /**
     * 页面分类
     */
    private String pageType;
    /**
     * 页面模板
     */
    private String pageTemplate;
    /**
     * 页面静态化内容
     */
    private String pageHtml;
    /**
     * 页面状态
     */
    private String pageStatus;
    /**
     * 页面创建时间
     */
    private Date pageCreateTime;
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 页面参数
     */
    private List<CmsPageParam> pageParams;
    /**
     * 模板文件id
     */
    private String templateFileId;
    /**
     * 静态文件id
     */
    private String htmlFileId;
    /**
     * 数据url
     */
    private String dataUrl;

}
