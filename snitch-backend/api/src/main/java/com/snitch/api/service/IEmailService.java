package com.snitch.api.service;

public interface IEmailService {
    void sendAdminAccountRecoverEmail(String emailTo, String password);
    void sendVictimEmail(String emailTo);
    void sendResultsEmail(String emailTo, String context);
}
