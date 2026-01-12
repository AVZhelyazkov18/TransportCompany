package org.informatics.service;

import org.informatics.context.ApplicationContext;
import org.informatics.dao.WorkerDao;
import org.informatics.dto.WorkerDto;
import org.informatics.entity.Company;
import org.informatics.entity.Worker;
import org.informatics.exceptions.InvalidWorkerException;

public class WorkerService {

    private final WorkerDao workerDao;

    public WorkerService(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }

    public Long createWorker(WorkerDto worker, ApplicationContext context) {
        Company company = context.getCompanyService().getCompany();

        Worker workerEntity = new Worker(worker.getFirstName(), worker.getLastName(), worker.getEmail(), worker.getSalary(), company);

        workerDao.save(workerEntity);

        return workerEntity.getId();
    }

    public Worker getWorkerFromId(Long id) {
        return workerDao.findById(id);
    }

    public String getWorkerDisplayName(Long workerId) {
        Worker worker = workerDao.findById(workerId);

        if (worker == null)
            throw new InvalidWorkerException("Worker doesn't exist with Id: " + workerId);

        return worker.getFirstName() + " " + worker.getLastName();
    }
}