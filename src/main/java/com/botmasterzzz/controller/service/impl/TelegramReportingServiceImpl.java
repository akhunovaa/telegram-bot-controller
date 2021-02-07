package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.controller.entity.TelegramBotUserEntity;
import com.botmasterzzz.controller.service.TelegramReportingService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import com.spire.xls.FileFormat;
//import com.spire.xls.Workbook;

@Service
public class TelegramReportingServiceImpl implements TelegramReportingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramReportingServiceImpl.class);

    private final TelegramUserDAO telegramUserDAO;

    @Value("${report.file.upload.path}")
    private String path;

    @Value("${jenkins.build.number}")
    private String applicationVersion;

    public TelegramReportingServiceImpl(TelegramUserDAO telegramUserDAO) {
        this.telegramUserDAO = telegramUserDAO;
    }

    @Override
    @Cacheable(value = "user-report-xls", key = "#telegramUserId")
    public String generateNewUsersReport(Long telegramUserId) throws IOException {

        List<TelegramBotUserEntity> telegramBotUserEntityList = telegramUserDAO.getReferralsListForUser(telegramUserId);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("report");

        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setShrinkToFit(true);
        sheet.setAutobreaks(true);
        sheet.setFitToPage(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        Row headerRow = sheet.createRow(0);
        headerRow.setRowStyle(style);

        Cell idCell = headerRow.createCell(0);
        idCell.setCellValue("ID");
        sheet.setColumnWidth(0, 25 * 200);
        headerRow.getCell(0).setCellStyle(style);

        Cell timeCell = headerRow.createCell(1);
        timeCell.setCellValue("TIME");
        sheet.setColumnWidth(1, 25 * 200);
        headerRow.getCell(1).setCellStyle(style);

        Cell userCell = headerRow.createCell(2);
        userCell.setCellValue("USER");
        sheet.setColumnWidth(2, 25 * 256);
        headerRow.getCell(2).setCellStyle(style);

        Cell nameCell = headerRow.createCell(3);
        nameCell.setCellValue("NAME");
        sheet.setColumnWidth(3, 25 * 256);
        headerRow.getCell(3).setCellStyle(style);

        Cell surnameCell = headerRow.createCell(4);
        surnameCell.setCellValue("SURNAME");
        sheet.setColumnWidth(4, 25 * 256);
        headerRow.getCell(4).setCellStyle(style);

        for (int i = 0; i < telegramBotUserEntityList.size(); i++) {

            XSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            XSSFCellStyle bottomCellStyle = workbook.createCellStyle();
            bottomCellStyle.setBorderBottom(BorderStyle.THIN);
            bottomCellStyle.setBorderRight(BorderStyle.THIN);
            bottomCellStyle.setBorderLeft(BorderStyle.THIN);
            bottomCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bottomCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            TelegramBotUserEntity telegramBotUserEntity = telegramBotUserEntityList.get(i);
            int index = i + 1;

            Row row = sheet.createRow(index);

            Cell id = row.createCell(0);
            String userTelegramId = String.valueOf(telegramBotUserEntity.getTelegramId());
            id.setCellValue(userTelegramId);
            if (i >= telegramBotUserEntityList.size() - 1) {
                id.setCellStyle(bottomCellStyle);
            } else {
                id.setCellStyle(cellStyle);
            }


            Timestamp timestamp = null != telegramBotUserEntity.getAudWhenCreate() ? telegramBotUserEntity.getAudWhenCreate() : telegramBotUserEntity.getAudWhenUpdate();
            String commentTimestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timestamp.getTime() + TimeUnit.HOURS.toMillis(3));
            Cell time = row.createCell(1);
            time.setCellValue(commentTimestamp);
            if (i >= telegramBotUserEntityList.size() - 1) {
                time.setCellStyle(bottomCellStyle);
            } else {
                time.setCellStyle(cellStyle);
            }

            Cell user = row.createCell(2);
            String userLink = null != telegramBotUserEntity.getUsername() ? "https://t.me/" + telegramBotUserEntity.getUsername() : " ";
            user.setCellValue(userLink);
            if (i >= telegramBotUserEntityList.size() - 1) {
                user.setCellStyle(bottomCellStyle);
            } else {
                user.setCellStyle(cellStyle);
            }

            Cell name = row.createCell(3);
            String userFirstName = null != telegramBotUserEntity.getFirstName() ? telegramBotUserEntity.getFirstName() : " ";
            name.setCellValue(userFirstName);
            if (i >= telegramBotUserEntityList.size() - 1) {
                name.setCellStyle(bottomCellStyle);
            } else {
                name.setCellStyle(cellStyle);
            }

            Cell surname = row.createCell(4);
            String userLastName = null != telegramBotUserEntity.getLastName() ? telegramBotUserEntity.getLastName() : " ";
            surname.setCellValue(userLastName);
            if (i >= telegramBotUserEntityList.size() - 1) {
                surname.setCellStyle(bottomCellStyle);
            } else {
                surname.setCellStyle(cellStyle);
            }

        }

        Row dataCountRow = sheet.createRow(telegramBotUserEntityList.size() + 2);
        Row footerRow = sheet.createRow(telegramBotUserEntityList.size() + 4);
        Row footerLinkRow = sheet.createRow(telegramBotUserEntityList.size() + 5);
        Row footerDateLinkRow = sheet.createRow(telegramBotUserEntityList.size() + 6);

        XSSFFont boldFont = workbook.createFont();
        boldFont.setColor(IndexedColors.BLACK.getIndex());
        boldFont.setBold(true);
        boldFont.setItalic(false);

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFont(boldFont);

        XSSFCellStyle cellRightStyle = workbook.createCellStyle();
        cellRightStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellRightStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellRightStyle.setFont(boldFont);

        footerRow.setRowStyle(cellStyle);
        footerLinkRow.setRowStyle(cellStyle);

        Cell dataCount = dataCountRow.createCell(0);
        dataCount.setCellValue("Кол-во: " + telegramBotUserEntityList.size());
        dataCount.setCellStyle(cellStyle);

        Cell copyRight = footerRow.createCell(4);
        copyRight.setCellValue("BotmasterZzz © 2020");
        copyRight.setCellStyle(cellRightStyle);

        Cell link = footerLinkRow.createCell(4);
        link.setCellValue("@tiktiktokrobot");
        link.setCellStyle(cellRightStyle);

        Cell date = footerDateLinkRow.createCell(4);
        String reportDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date().getTime() + TimeUnit.HOURS.toMillis(3));
        date.setCellValue(reportDate);
        date.setCellStyle(cellRightStyle);

        Cell version = footerDateLinkRow.createCell(4);
        version.setCellValue("Version: 1.7." + this.applicationVersion);
        version.setCellStyle(cellRightStyle);

        String time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Timestamp(new Date().getTime()).getTime() + TimeUnit.HOURS.toMillis(3));

        String reportPathFullLocation = path + "/" + "report_" + telegramUserId + "(" + time + ").xlsx";

        try (FileOutputStream outputStream = new FileOutputStream(new File(reportPathFullLocation))) {
            workbook.write(outputStream);
        }
        return reportPathFullLocation;
    }

}
