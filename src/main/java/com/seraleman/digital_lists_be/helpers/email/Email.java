package com.seraleman.digital_lists_be.helpers.email;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

    /**
     * Envía un email HTML con imágenes en línea
     * 
     * @param host            SMTP host
     * @param port            SMTP port
     * @param userName        dirección de correo electrónico de la cuenta del
     *                        remitente
     * @param password        contraseña de la cuenta del remitente
     * @param toAddress       dirección de correo electrónico del destinatario
     * @param subject         asunto del email
     * @param htmlBody        contenido del correo electrónico con etiquetas HTML
     * @param mapInlineImages
     *                        key: Id del contenido
     *                        value: ruta del archivo de la imagen
     * @throws AddressException
     * @throws MessagingException
     */
    public static void send(
            String host,
            String port,
            final String userName,
            final String password,
            String toAddress,
            String subject,
            String htmlBody,
            Map<String, String> mapInlineImages)
            throws AddressException, MessagingException {

        // establece las propiedades del servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.user", userName);
        props.put("mail.password", password);

        // crea una nueva sesión con un autenticador
        Authenticator authenticator = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(props, authenticator);

        // crea un nuevo mensaje de correo electrónico
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        message.setRecipients(Message.RecipientType.TO, toAddresses);
        message.setSubject(subject);
        message.setSentDate(new Date());

        // crea cuerpo del mensaje
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(htmlBody, "text/html");

        // crea múltiples partes
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // agrega imagen adjunta en línea
        if (mapInlineImages != null && mapInlineImages.size() > 0) {
            Set<String> setImageID = mapInlineImages.keySet();

            for (String contentId : setImageID) {
                MimeBodyPart imagePart = new MimeBodyPart();
                imagePart.setHeader("Content-ID", "<" + contentId + ">");
                imagePart.setDisposition(MimeBodyPart.INLINE);

                String imageFilePath = mapInlineImages.get(contentId);
                try {
                    imagePart.attachFile(imageFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                multipart.addBodyPart(imagePart);
            }
        }
        message.setContent(multipart);
        Transport.send(message);
    }

    public static void sendMessage(String recipient) {
        // información y credenciales
<<<<<<< HEAD
        // String host = "smtp.gmail.com";
=======
>>>>>>> ea18b41 (Se corrige envío de QR y se implementa funcionalidad para que mismo usuario con misma razón no se cree.)
        String host = "outlook.office365.com";
        String port = "587";
        String mailFrom = "testhubdl@outlook.es";
        String password = "probando123";

        // información del mensaje
        String mailTo = recipient;
        String subject = "Confirmación de inscripción HUB - Sena";
        StringBuffer body = new StringBuffer(
                "<html>Este mensaje es una prueba de envío de imágenes desde java.<br>")
                .append("Enviando código QR:<br>")
                .append("<img src=\"cid:qr\" width=\"50%\" height=\"50%\" /><br>")
                .append("Fin del mensaje")
                .append("</html>");

        // imágenes en linea
        Map<String, String> inlineImages = new HashMap<String, String>();
        inlineImages.put("qr", "./src/main/resources/qrCodes/QRCode.png");

        try {
            send(host, port, mailFrom, password, mailTo, subject, body.toString(), inlineImages);
            System.out.println("Email enviado");
        } catch (Exception e) {
            System.err.println("Email no enviado");
            e.printStackTrace();
        }
    }
}
