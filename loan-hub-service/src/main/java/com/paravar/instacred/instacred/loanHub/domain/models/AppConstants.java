package com.paravar.instacred.instacred.loanHub.domain.models;

import java.net.URI;

public interface AppConstants {
    URI NOT_FOUND_TYPE = URI.create("https://api.instacred.com/errors/not-found");
    URI ISE_FOUND_TYPE = URI.create("https://api.instacred.com/errors/server-error");
    String SERVICE_NAME = "loan-hub-service";
    URI BAD_REQUEST_TYPE = URI.create("https://api.bookstore.com/errors/bad-request");
}
