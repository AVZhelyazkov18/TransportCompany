package org.informatics.service;

import org.informatics.context.ApplicationContext;
import org.informatics.dao.VehicleDao;
import org.informatics.dto.VehicleDto;
import org.informatics.entity.Vehicle;
import org.informatics.enums.VehicleType;

import java.util.List;

public class VehicleService {

    private final VehicleDao vehicleDao;

    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public void createVehicle(ApplicationContext context, VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle(vehicleDto.getType(), vehicleDto.getRegistrationNumber(), context.getCompanyService().getCompany());
        vehicleDao.save(vehicle);
    }

    public boolean deleteVehicle(Long id) {
        try {
            vehicleDao.delete(vehicleDao.findById(id));
        } catch (IllegalArgumentException e) {
            System.out.println("Vehicle doesn't exist with Id: " + id);
            return false;
        }

        return true;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleDao.findAll();
    }
}

