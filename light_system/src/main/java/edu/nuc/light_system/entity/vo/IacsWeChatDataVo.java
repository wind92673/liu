package edu.nuc.light_system.entity.vo;

/**
 * @ClassName IacsWeChatDataVo
 * @Description // TODO: 企业微信消息数据实体类
 * @Author 19265
 * @Date 2026/4/15 10:11
 * @Version 1.0.0
 */
public class IacsWeChatDataVo {
    String touser;
    String msgtype;
    int agentid;
    Object text;//实际接收Map类型数据

    public Object getText() {
        return text;
    }
    public void setText(Object text) {
        this.text = text;
    }
    public String getMsgtype() {
        return msgtype;
    }
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
    public int getAgentid() {
        return agentid;
    }
    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }
    public String getTouser() {
        return touser;
    }
    public void setTouser(String touser) {
        this.touser = touser;
    }

}
