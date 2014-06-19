package org.tinygroup.weixin;

import org.tinygroup.xmlparser.XmlNodeType;
import org.tinygroup.xmlparser.node.XmlNode;
import org.tinygroup.xmlparser.parser.XmlStringParser;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by luoguo on 2014/5/27.
 */
public class SignatureChecker {
    static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");

    public String checkSignature(String timestamp, String nonce, String signature, String echostr, String tinyRequestRemoteHost, Object postData) {
        if (tinyRequestRemoteHost != null) {
            System.out.println("from :" + tinyRequestRemoteHost);
        }
        if (postData != null) {
            return processPostData(new String((byte[])postData));
        } else {
            if (checkSign(timestamp, nonce, signature)) {
                return echostr;
            } else {
                return "";
            }
        }
    }

    private String processPostData(String postData) {
        XmlNode input = new XmlStringParser().parse(postData).getRoot();
        XmlNode output = new XmlNode("xml");
        output.addNode(new XmlNode("ToUserName").setContent(input.getSubNode("FromUserName").getPureText()));
        output.addNode(new XmlNode("FromUserName").setContent("TinyFramework"));
        output.addNode(new XmlNode("CreateTime").setContent(format.format(new Date())));
        output.addNode(new XmlNode("MsgType").setContent("text"));
        if (input.getSubNode("MsgType").getPureText().equalsIgnoreCase("text")) {
            addContent(output, "您输入的信息是：" + input.getSubNode("Content").getPureText());
        } else {
            addContent(output, "对不起，我暂时还不认识文本之外的信息。");
        }
        return output.toString();
    }

    private void addContent(XmlNode output, String content) {
        output.addNode(new XmlNode("Content").addNode(
                new XmlNode(XmlNodeType.CDATA)
                        .setContent(content)
        ));
    }

    private boolean checkSign(String timestamp, String nonce, String signature) {
        String[] stringList = new String[3];
        stringList[0] = "TinyFramework";
        stringList[1] = timestamp;
        stringList[2] = nonce;
        Arrays.sort(stringList);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : stringList) {
            stringBuffer.append(str);
        }
        String sha1 = EncoderHandler.encode("SHA1", stringBuffer.toString());
        return sha1.equals(signature);
    }

    public static void main(String[] args) {
        SignatureChecker c = new SignatureChecker();
        String out = c.processPostData("<xml><ToUserName>toUser</ToUserName> <FromUserName>fromUser</FromUserName>  <CreateTime>1348831860</CreateTime><MsgType>text</MsgType> <Content>this is a test</Content> <MsgId>1234567890123456</MsgId> </xml>");
        System.out.println(out);
    }
}
