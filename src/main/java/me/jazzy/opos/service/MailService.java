package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.FeedbackDto;
import me.jazzy.opos.model.Pizza;
import me.jazzy.opos.model.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MailService {

    private final PizzaService pizzaService;
    private final EmailSenderService emailSenderService;

    public ResponseBody feedback(FeedbackDto feedbackDto) {
        Pizza pizza = pizzaService.getById(feedbackDto.getPizzaId());
        String message = "There is a feedback for " + pizza.getName() + ",\n" + feedbackDto.getSubject();
        emailSenderService.sendEmailFrom(feedbackDto.getEmail(), feedbackDto.getSubject(), message);

        return new ResponseBody(
                HttpStatus.OK.value(),
                "Feedback sent successfully",
                LocalDateTime.now()
        );
    }
}