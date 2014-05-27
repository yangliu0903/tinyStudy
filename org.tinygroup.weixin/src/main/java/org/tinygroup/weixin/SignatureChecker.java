package org.tinygroup.weixin;

import java.util.Arrays;

/**
 * Created by luoguo on 2014/5/27.
 */
public class SignatureChecker {
    public boolean checkSignature( String timestamp, String nonce, String signature,String echostr) {
        String[] stringList = new String[3];
        stringList[0]="TinyFramework";
        stringList[1]="timestamp";
        stringList[2]="nonce";
        Arrays.sort(stringList);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : stringList) {
            stringBuffer.append(str);
        }
        String sha1 = EncoderHandler.encode("SHA1", stringBuffer.toString());
        return sha1.equals(signature);
    }
}
