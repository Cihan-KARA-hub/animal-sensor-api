package com.yelman.cloudserver.utils.mail.mails;

import com.yelman.cloudserver.api.dto.EmailSendDto;

public interface EmailService {
    String sendSimpleMail(EmailSendDto details);

    void sendMailWithAttachment(EmailSendDto details);
}
