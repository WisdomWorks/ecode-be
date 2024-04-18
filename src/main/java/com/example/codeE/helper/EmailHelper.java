package com.example.codeE.helper;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import org.springframework.stereotype.Component;
import java.io.*;
@Component
public class EmailHelper {

    private static final String FROM_EMAIL = "admin@codee.buzz";

    private AmazonSimpleEmailService amazonSimpleEmailService;
    public EmailHelper() {
        try {
            this.amazonSimpleEmailService =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            // Replace US_WEST_2 with the AWS Region you're using for
                            // Amazon SES.
                            .withRegion(Regions.AP_SOUTHEAST_1).build();

        } catch (Exception ex) {
            System.out.println("Cannot create service: "
                    + ex.getMessage());
        }
    }


    public Boolean sendMail(String subject, String message, String toEmail) throws IOException {
        try {
            // Create the draft message
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(toEmail))
                    .withMessage(new com.amazonaws.services.simpleemail.model.Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(message)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(subject)))
                    .withSource(FROM_EMAIL);
            System.out.println("Sending email to: " + toEmail);
            this.amazonSimpleEmailService.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
        return true;
    }
}
