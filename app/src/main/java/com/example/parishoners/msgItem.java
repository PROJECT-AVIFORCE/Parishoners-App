package com.example.parishoners;

public class msgItem {

    String msgID;
    String msgDes;

    public msgItem() {
    }

    public msgItem(String msgID, String msgDes) {
        this.msgID = msgID;
        this.msgDes = msgDes;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getMsgDes() {
        return msgDes;
    }

    public void setMsgDes(String msgDes) {
        this.msgDes = msgDes;
    }
}
