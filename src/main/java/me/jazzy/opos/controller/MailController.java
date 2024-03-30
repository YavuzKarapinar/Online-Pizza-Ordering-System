package me.jazzy.opos.controller;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.ContactDto;
import me.jazzy.opos.model.ResponseBody;
import me.jazzy.opos.service.EmailSenderService;
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

    @PostMapping("/contact")
    public ResponseEntity<ResponseBody> contact(@RequestBody ContactDto contactDto) {
        String message = "Dear " + contactDto.getName() + ",\n" + contactDto.getMessage();
        return new ResponseEntity<>(
                service.sendEmailFrom(contactDto.getEmail(), contactDto.getSubject(), message),
                HttpStatus.OK
        );
    }

}