package com.pingpongx.fx;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import quickfix.FieldNotFound;
import quickfix.Message;
import quickfix.field.MsgType;
import quickfix.field.RawData;
import quickfix.field.RawDataLength;
import quickfix.field.SenderCompID;
import quickfix.field.TargetCompID;
import quickfix.fix44.Logon;

/**
 * @author zhengkk
 * @since 2019-03-18 14:28
 **/
public class FIXSign {

    private FIXSign() {

    }

    public static void logonSign(Message message, String account) throws Exception {

        if (!isMessageOfType(message, MsgType.LOGON)) {
            throw new MessageNotFitException();
        }

        Map<String, String> map = new HashMap<>();
        map.put(SenderCompID.class.getSimpleName(), message.getHeader().getString(SenderCompID.FIELD));
        map.put(TargetCompID.class.getSimpleName(), message.getHeader().getString(TargetCompID.FIELD));
        map.put("$secretKey", account);
        String splicer = ",";
        SortedSet<String> sortedSet = new TreeSet(map.keySet());
        StringBuilder sortedStr = new StringBuilder();
        sortedSet.forEach(item -> sortedStr.append(map.get(item)).append(splicer));
        if (sortedStr.lastIndexOf(splicer) > 0) {
            sortedStr.deleteCharAt(sortedStr.length() - 1);
        }
        String sign = Md5Util.getMD5(sortedStr.toString());
        message.setField(new RawData(sign));
        message.setField(new RawDataLength(sign.length()));
    }

    /**
     * 匹配消息头
     */
    private static boolean isMessageOfType(Message message, String type) {
        try {
            return type.equals(message.getHeader().getField(new MsgType()).getValue());
        } catch (FieldNotFound e) {
            return false;
        }
    }

}
