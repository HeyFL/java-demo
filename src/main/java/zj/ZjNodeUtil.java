package zj;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述：证据链节点映射工具类
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
public class ZjNodeUtil {
    /**
     * opcode to node Mapping string ,rule:
     * opcode1,opcode2:nodeA;opcode4,opcode5:nodeB;
     */
    private String opCodeToNodeStr = "54,50:LSYS;";
    private Map<String, String> opCodeToNodeMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        new ZjNodeUtil().getOpCodeToNodeMap().forEach((k, v) -> {
            System.out.println(k + ":" + v);
            System.out.println(ZjNodeEnum.getClazz(v));
        });
    }

    public Map<String, String> getOpCodeToNodeMap() {

        ReadWriteLock lock = new ReentrantReadWriteLock();
        lock.
                String[] opCodeToNodesArr = opCodeToNodeStr.split(";");

        // put opcode as key node as value to opCodeToNodeMap
        //for(String opCodeToNodes : opCodeToNodesArr){
        //    String[] opCodeToNode = opCodeToNodes.split(":");
        //    String[] opCodes =opCodeToNode[0].split(",");
        //    String node = opCodeToNode[1];
        //    for (String opCode : opCodes) {
        //        opCodeToNodeMap.put(opCode,node);
        //    }
        //}
        Arrays.stream(opCodeToNodeStr.split(";")).forEach(opCodeToNodes -> {
            String[] opCodeToNode = opCodeToNodes.split(":");
            String[] opCodes = opCodeToNode[0].split(",");
            String node = opCodeToNode[1];
            for (String opCode : opCodes) {
                opCodeToNodeMap.put(opCode, node);
            }
        });
        return opCodeToNodeMap;
    }


}
