package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * 页面名称、别名、访问地址、类型（静态/动态）、页面模版、状态
 * mongodb和springboot的对应，使用@Document注解一一对应
 *
 * @author 吧嘻小米
 * @date 2020/04/19
 */
@Data
@ToString
@Document(collection = "cms_page")
public class CmsPage {

    /**
     * 页面id
     */
    @Id
    private String pageId;
    /**
     * 站点id
     */
    private String siteId;

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
     * 静态化页面需要的数据地址
     */
    private String dataUrl;

}
