package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 模板查询条件
 *
 * @author 吧嘻小米
 * @date 2020/05/05
 */
@Data
public class QueryTemplateRequest extends RequestData {
    /**
     * 站点id
     */
    @ApiModelProperty(value = "站点id")
    private String siteId;
    /**
     * 模板id
     */
    @Id
    @ApiModelProperty(value = "模板id")
    private String templateId;
    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")

    private String templateName;
    /**
     * 模板参数
     */
    @ApiModelProperty(value = "模板参数")

    private String templateParameter;

    /**
     * 模板文件id
     */
    @ApiModelProperty(value = "模板文件id")
    private String templateFileId;
}
