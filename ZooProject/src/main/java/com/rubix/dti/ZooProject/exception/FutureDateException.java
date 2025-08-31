package com.rubix.dti.ZooProject.exception;

public class FutureDateException extends RuntimeException {

        public FutureDateException() {
            super("Date cannot be in the future.");
        }
}
