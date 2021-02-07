package com.botmasterzzz.controller.service;

import java.io.IOException;

public interface TelegramReportingService {

    String generateNewUsersReport(Long telegramUserId) throws IOException;

//    String generatePDFFile(String excelFile, Long telegramUserId) throws IOException;

}
