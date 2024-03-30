package me.jazzy.opos.controller;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.ContactDto;
import me.jazzy.opos.dto.FeedbackDto;
import me.jazzy.opos.model.ResponseBody;
import me.jazzy.opos.service.EmailSenderService;
import me.jazzy.opos.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mails")
@AllArgsConstructor
public class MailController {

    private final EmailSenderService service;
    private final MailService mailService;

    @PostMapping("/contact")
    public ResponseEntity<ResponseBody> contact(@RequestBody ContactDto contactDto) {
        return new ResponseEntity<>(
                mailService.contact(contactDto),
                HttpStatus.OK
        );
    }

    @PostMapping("/feedback")
    public ResponseEntity<ResponseBody> feedback(@RequestBody FeedbackDto feedbackDto) {
        return new ResponseEntity<>(mailService.feedback(feedbackDto), HttpStatus.OK);
    }
}