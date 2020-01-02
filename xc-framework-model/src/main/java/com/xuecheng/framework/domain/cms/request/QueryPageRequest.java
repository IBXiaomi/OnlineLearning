package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import lombok.Data;

/**
 * 定义查询页面的条件实体类
 *
 * @author wjw
 * @since JDK1.9
 */
@Data
public class QueryPageRequest extends RequestData {

    private String name;

    private String pageId;
}
