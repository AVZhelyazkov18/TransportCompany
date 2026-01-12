package org.informatics.context;

import lombok.Getter;
import org.informatics.service.*;

@Getter
public class ApplicationContext {

    private final ClientService clientService;
    private final OrderService orderService;
    private final WorkerService workerService;
    private final VehicleService vehicleService;
    private final CompanyService companyService;
    private final AuthService authService;

    public ApplicationContext(
            ClientService clientService,
            OrderService orderService,
            WorkerService workerService,
            VehicleService vehicleService,
            CompanyService companyService,
            AuthService authService
    ) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.workerService = workerService;
        this.vehicleService = vehicleService;
        this.companyService = companyService;
        this.authService = authService;
    }
}
