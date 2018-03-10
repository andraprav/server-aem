package com.aem.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * This is a service which dispatches
 * processing to a pool of threads
 */

@Service
public class MessageService {

    @Value("${thread.pool.size}")
    private Integer size;

    private static final Logger logger = Logger.getLogger(MessageService.class.getName());

    private ExecutorService threadPool;

    private Callable<String> callable;

    @PostConstruct
    private void init() {

        threadPool = Executors.newFixedThreadPool(size);
        callable = () -> {
            try {
                logger.info("begin...");
                long time = System.currentTimeMillis();

                //pretending to process
                Thread.sleep(1000);
                logger.info("end " + time);

                return "This is a server message at " + time;
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        };
    }

    public Future<String> process() {
        return this.threadPool.submit(callable);
    }
}
