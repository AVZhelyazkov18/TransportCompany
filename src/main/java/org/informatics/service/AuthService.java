package org.informatics.service;

import lombok.Getter;
import org.informatics.context.ApplicationContext;
import org.informatics.exceptions.InvalidWorkerException;

@Getter
public class AuthService {

    private Long loggedWorkerId;

    public void login(Long workerId, ApplicationContext context) {
        if (context.getWorkerService().getWorkerFromId(workerId) == null)
            throw new InvalidWorkerException("Worker doesn't exist with Id: " + workerId);

        this.loggedWorkerId = workerId;
        System.out.println("Logged in as: " + workerId);
    }

    public void logout() {
        this.loggedWorkerId = null;
    }

    public boolean isLoggedIn() {
        return loggedWorkerId != null;
    }

}
