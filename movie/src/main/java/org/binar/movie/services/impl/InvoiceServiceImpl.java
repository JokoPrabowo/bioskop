package org.binar.movie.services.impl;

import org.binar.movie.dto.TicketData;
import org.binar.movie.entities.Ticket;
import org.binar.movie.repositories.TicketRepo;
import org.binar.movie.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    TicketRepo ticketRepo;

    public byte[] generateFile(TicketData data){
        log.info("Creating a ticket");
        try{
            File file = ResourceUtils.getFile("classpath:Invoice.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            List<Object> state = new ArrayList<>();
            state.add(data);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("data", "myData");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JRBeanCollectionDataSource(state));
            log.info("Ticket has been created");
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }catch(IOException | JRException e){
            log.error("Error detected {}", e.getMessage());
            return null;
        }
    }

    @Scheduled(cron = " 0 0 * * SUN")
    public byte[] generateReports() {
        log.info("Creating a report");
        try{
            File file = ResourceUtils.getFile("classpath:InvoiceReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            List<Ticket> state = ticketRepo.findAll();
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("data", "myData");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JRBeanCollectionDataSource(state));
            log.info("Ticket has been created");
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }catch(IOException | JRException e){
            log.error("Error detected {}", e.getMessage());
            return null;
        }
    }
}
