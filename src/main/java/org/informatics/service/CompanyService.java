package org.informatics.service;

import org.informatics.context.ApplicationContext;
import org.informatics.dao.CompanyDao;
import org.informatics.dto.CompanyDto;
import org.informatics.entity.Company;
import org.informatics.exceptions.OwnerExistsException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CompanyService {

    private final CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public boolean companyExists() {
        return companyDao.count() > 0;
    }

    public void createCompany(CompanyDto company, ApplicationContext context) {
        Company companyEntity = new Company(
                company.getName(),
                company.getRegisteredOnDate()
        );

        companyDao.save(companyEntity);
    }

    public void updateCompanyOwner(CompanyDto company, ApplicationContext context) {
        if (getCompany().getOwner() == null)
            getCompany().setOwner(context.getWorkerService().getWorkerFromId(company.getOwnerId()));
        else
            throw new OwnerExistsException("Owner already exists for the company.");
    }

    public Company getCompany() {
        return companyDao.findAll().getFirst();
    }

    public String generateCompanyTranscript(ApplicationContext context, LocalDate from, LocalDate to) {
        try {
            Files.createDirectories(Paths.get("transcripts"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create transcripts directory", e);
        }

        String fileName = String.format("transcripts/transcript-%s.txt", LocalDateTime.now().toString().replace(":", "-"));

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {

            writer.write("=== COMPANY REPORT ===");
            writer.newLine();

            writer.write("Total transports: " +
                    context.getOrderService().getTotalTransports());
            writer.newLine();

            writer.write("Total revenue: " +
                    context.getOrderService().getTotalRevenue());
            writer.newLine();

            writer.write("Revenue for period " + from + " - " + to + ": " +
                    context.getOrderService().getRevenueForPeriod(from, to));
            writer.newLine();

            writer.write("Transports per driver:");
            writer.newLine();

            for (var entry : context.getOrderService().getTransportsPerDriver().entrySet()) {
                String name = context.getWorkerService().getWorkerDisplayName(entry.getKey());

                writer.write(name + " -> " + entry.getValue());
                writer.newLine();
            }

            writer.newLine();
            writer.write("Revenue per driver for period:");
            writer.newLine();

            for (var entry : context.getOrderService().getRevenuePerDriverForPeriod(from, to).entrySet()) {
                String name = context.getWorkerService().getWorkerDisplayName(entry.getKey());

                writer.write(name + " -> " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate transcript", e);
        }

        return fileName;
    }
}

