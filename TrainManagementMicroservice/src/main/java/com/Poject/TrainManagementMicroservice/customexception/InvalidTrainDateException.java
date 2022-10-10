package com.Poject.TrainManagementMicroservice.customexception;

public class InvalidTrainDateException extends RuntimeException {

    private String message;
    public InvalidTrainDateException(String message) {
        super(message);
        this.message=message;

    }

    public InvalidTrainDateException() {

    }


}
