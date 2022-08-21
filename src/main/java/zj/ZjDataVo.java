package zj;

import lombok.Data;

/**
 * 描述：返回前端展示的一行Table数据
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON            REASON
 *  1    2022/8/21         01390559         Create
 * ****************************************************************************
 * </pre>
 *
 * @author Chris Cai
 * @version 1.0
 */
@Data
public class ZjDataVo {
    /**
     * 上报时间
     */
    private String reportTm;
    /**
     * 环节名称
     */
    private String nodeName;
    /**
     * 信息类型
     */
    private String dataType;
    /**
     * 信息内容
     */
    private String dataContent;
    /**
     * 信息内容类型
     */
    private ZjDataContentTypeEnum dataContentType;

    /**
     * 上报系统 用作前端国际化
     */
    private String reportSystem;
}
