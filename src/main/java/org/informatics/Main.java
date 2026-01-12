package org.informatics;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.informatics.context.ApplicationContext;
import org.informatics.dao.*;
import org.informatics.dao.implementation.*;
import org.informatics.flow.company.CreateCompanyFlow;
import org.informatics.flow.Flow;
import org.informatics.flow.MainMenuFlow;
import org.informatics.service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerInstance = new Scanner(System.in);
        ApplicationContext context = null;

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Transport");){
            EntityManager manager = emf.createEntityManager();

            ClientDao clientDao = new ClientDaoImpl(manager);
            CompanyDao companyDao = new CompanyDaoImpl(manager);
            WorkerDao workerDao = new WorkerDaoImpl(manager);
            VehicleDao vehicleDao = new VehicleDaoImpl(manager);
            OrderDao orderDao = new OrderDaoImpl(manager);

            context = new ApplicationContext(
                    new ClientService(clientDao),
                    new OrderService(orderDao, clientDao),
                    new WorkerService(workerDao),
                    new VehicleService(vehicleDao),
                    new CompanyService(companyDao),
                    new AuthService()
            );

            if (!context.getCompanyService().companyExists()) {
                System.out.println("== No company found. Creating new company ==");
                new CreateCompanyFlow().execute(scannerInstance, context);
            }

            Flow flow = new MainMenuFlow();
            flow.execute(scannerInstance, context);
        }
    }
}