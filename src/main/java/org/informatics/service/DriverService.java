package org.informatics.service;

import org.informatics.dao.DriverDao;
import org.informatics.entity.Driver;
import org.informatics.enums.Qualification;

import java.util.List;
import java.util.Set;

public class DriverService {

    private final DriverDao driverDao;

    public DriverService(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    public void createDriver(
            String firstName,
            String lastName,
            Set<Qualification> qualifications
    ) {
        Driver driver = new Driver();
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.getQualifications().addAll(qualifications);
        driverDao.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverDao.findAll();
    }

    public void deleteDriver(Long id) {
        driverDao.delete(driverDao.findById(id));
    }
}

