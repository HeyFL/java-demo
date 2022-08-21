package zj;

import lombok.Getter;

/**
 * 描述：证据链
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
@Getter
public enum ZjNodeEnum {
    LSYS(PjysHandler.class),
    ;
    private Class clazz;

    ZjNodeEnum(Class clazz) {
        this.clazz = clazz;
    }


    public static Class getClazz(String code) {
        for (ZjNodeEnum e : ZjNodeEnum.values()) {
            if (e.name().equals(code)) {
                return e.getClazz();
            }
        }
        return null;
    }


}
