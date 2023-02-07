package br.com.sicred.core;

import io.restassured.http.ContentType;


public interface Constantes {
    String APP_BASE_URL = "http://localhost/api/v1/";
    Integer APP_PORT = 8080;
    String APP_BASE_PATH = "api/v1/";

    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 20000L;

        }
