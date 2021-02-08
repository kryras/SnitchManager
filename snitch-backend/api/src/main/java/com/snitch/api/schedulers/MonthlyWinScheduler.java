package com.snitch.api.schedulers;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.snitch.api.schedulers.helpers.MonthlyWinSchedulerHelper;
import com.snitch.api.service.IEmailService;
import com.snitch.entities.model.User;

import javassist.NotFoundException;

@Component
public class MonthlyWinScheduler {

    @Autowired
    private MonthlyWinSchedulerHelper monthlyWinSchedulerHelper;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Scheduled(cron="1 0 1 * * ?") //<-pierwszy dzien miesiąca np 2021-03-01 00:01:00, 2021-04-01 00:01:00
    //robi sie co minute dla testow
//    @Scheduled(cron="0 0/5 * * * ?")
    public void sendMailToPreviousMonthWinners() throws NotFoundException {
        Map<User, Integer> winnersWithPoints = monthlyWinSchedulerHelper.getThreeBestUsersFromPreviousMonthWithPointsMap();
        boolean emailForWinner = true;
        for (Entry<User, Integer> entry : winnersWithPoints.entrySet()) {
            String context = createResultsEmailBody(winnersWithPoints, emailForWinner);
            emailService.sendResultsEmail(entry.getKey().getEmail(), context);
            if (emailForWinner) {
                emailForWinner = false;
            }
        }
    }

    private String createResultsEmailBody(Map<User, Integer> winnersWithPointsMap, boolean emailForWinner) {
        Context context = new Context();
        int index = 1;
        context.setVariable("info", getInfoText(emailForWinner));
        for (Entry<User, Integer> entry : winnersWithPointsMap.entrySet()) {
            context.setVariable("w" + index, getResultLineText(entry, index));
            index++;
        }

        return templateEngine.process("resultsMail", context);
    }

    private String getResultLineText(Entry<User, Integer> entry, int index) {
        return index + ". " + entry.getKey().getFirstName() + " " + entry.getKey().getLastName() + "(" + entry.getValue() + "pkt)";
    }

    private String getInfoText(boolean emailForWinner) {
        return emailForWinner ? "Wygrałeś!" : "Nie tym razem :(";
    }
}
