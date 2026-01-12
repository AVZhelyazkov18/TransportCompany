package org.informatics.service;

import org.informatics.context.ApplicationContext;
import org.informatics.dao.DriverDao;
import org.informatics.dto.DriverDto;
import org.informatics.entity.Driver;
import org.informatics.enums.Qualification;

import java.util.List;
import java.util.Set;

public class DriverService {

    private final DriverDao driverDao;

    public DriverService(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    public void createDriver(ApplicationContext context, DriverDto driverDto) {
        Driver driver = new Driver(driverDto.getFirstName(),
                driverDto.getLastName(),
                driverDto.getEmail(),
                driverDto.getSalary(),
                context.getCompanyService().getCompany(),
                driverDto.getQualifications());
        driverDao.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverDao.findAll();
    }

    public boolean deleteDriver(Long id) {
        try {
            driverDao.delete(driverDao.findById(id));
        } catch (IllegalArgumentException e) {
            System.out.println("Driver doesn't exist with Id: " + id);
            return false;
        }
        return true;
    }
}

