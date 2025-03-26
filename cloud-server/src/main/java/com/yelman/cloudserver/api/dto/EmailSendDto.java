package com.yelman.cloudserver.api.dto;

import java.io.Serializable;

public class EmailSendDto implements Serializable {
    private String recipient;//alıcı
    private String msgBody;//mesaj body

    public EmailSendDto() {

    }

    private String subject;//konusu
    private String attachment;//eki

    public EmailSendDto(String recipient, String msgBody, String subject, String attachment) {
        this.recipient = recipient;
        this.msgBody = msgBody;
        this.subject = subject;
        this.attachment = attachment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}