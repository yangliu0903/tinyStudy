package org.tinygroup.weixin;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by luoguo on 2014/5/27.
 */
@XStreamAlias("xml")
public class TextMessage {
    @XStreamAlias("ToUserName")
    String toUserName;
    @XStreamAlias("ToUserName")
    String fromUserName;
    @XStreamAlias("CreateTime")
    String createTime;
    @XStreamAlias("MsgType")
    String msgType;
    @XStreamAlias("Content")
    String content;
    @XStreamAlias("MsgId")
    String msgId;
}
