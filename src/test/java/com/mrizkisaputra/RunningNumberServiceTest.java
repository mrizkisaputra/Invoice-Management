package com.mrizkisaputra;

import com.mrizkisaputra.services.RunningNumberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/delete-running-number.sql"})
public class RunningNumberServiceTest {

    @Autowired
    RunningNumberService runningNumberService;

    @Test
    void testGetNumber() {
        Long number = runningNumberService.getNumber("test");
        Assertions.assertNotNull(number);
        System.out.println("Hasil: "+number);
    }

    @Test
    void testGetNumberOfMultiThreaded() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Long number = runningNumberService.getNumber("test");
                System.out.println("Thread: "+Thread.currentThread().getName()+" Hasil: "+number);
                }
            };

            new Thread(runnable).start();
        }
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i = 1; i <= 10; i++) {
//            executorService.execute(() -> {
//                Long number = runningNumberService.getNumber("test");
//                Assertions.assertNotNull(number);
//                System.out.println("Thread: "+Thread.currentThread().getName()+" Hasil: "+number);
//            });
//        }

        Thread.sleep(2000);
    }


}
