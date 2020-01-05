package com.xuecheng.framework.utils.traverse;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 工具类，遍历集合
 *
 * @author wjw
 * @since JDK1.9
 */
@Slf4j
public class TraverseCollections {

    public static void traverseCollection(List list) {
        if (null != list && list.size() != 0) {
            log.info("list is not null");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }else{
            log.info("list is null");
        }
    }

}
