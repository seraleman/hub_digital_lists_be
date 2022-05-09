package com.seraleman.digital_lists_be.helpers.email;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;

public class Email {

    BodyPart createInlineImagePart(byte[] base64EncodedImageContentByteArray) throws MessagingException {
        InternetHeaders headers = new InternetHeaders();
        headers.addHeader("Content-Type", "image/jpeg");
        headers.addHeader("Content-Transfer-Encoding", "base64");
        MimeBodyPart imagePart = new MimeBodyPart(headers, base64EncodedImageContentByteArray);
        imagePart.setDisposition(MimeBodyPart.INLINE);
        imagePart.setContentID("&lt;image&gt;");
        imagePart.setFileName("image.jpg");
        return imagePart;
    }
}
