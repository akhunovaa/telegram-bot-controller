package com.botmasterzzz.controller;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;

public class Server {

    private static final int DEFAULT_PORT = 7109;
    private final Tomcat tomcat;

    public Server() throws ServletException {
        this(getPort());
    }


    public Server(int port) throws ServletException {
        tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        tomcat.addWebapp("/controller", System.getProperty("java.io.tmpdir"));
    }

    public static void main(String[] args) throws Exception {
        new Server().run();
    }

    public static int getPort() {
        return System.getenv("BOTMASTERZZZ.TELEGRAM.CONTROLLER.TOMCAT.PORT") == null ? DEFAULT_PORT : Integer.parseInt(System.getenv("BOTMASTERZZZ.TELEGRAM.CONTROLLER.TOMCAT.PORT"));
    }

    public void run() throws Exception {
        tomcat.start();
        tomcat.getServer().await();
    }

    public void start() throws LifecycleException {
        tomcat.start();

    }

    public void stop() throws LifecycleException {
        try {
            tomcat.stop();
        } finally {
            tomcat.destroy();
        }
    }
}