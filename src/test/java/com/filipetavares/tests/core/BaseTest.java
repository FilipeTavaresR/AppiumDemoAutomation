package com.filipetavares.tests.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

public abstract class BaseTest {
    protected static AndroidDriver driver;
    private static final String APP_PATH = Paths.get("src/test/resources/app-debug.apk").toAbsolutePath().toString();
    private static final String DEVICE_NAME = "emulator-5554";
    private static final String PLATFORM_NAME = "Android";
    private static final String PLATFORM_VERSION = "11";
    private static final String APP_PACKAGE = "com.saucelabs.mydemoapp.android";
    private static final String APP_ACTIVITY = "com.saucelabs.mydemoapp.android.view.activities.SplashActivity";
    private static final int COMMAND_TIMEOUT = 300;
    private static final Duration TIMEOUT = Duration.ofSeconds(60);

    @BeforeEach
    public void setUp() throws IOException {
        try {
            installAPK();
            UiAutomator2Options options = new UiAutomator2Options()
                    .setDeviceName(DEVICE_NAME)
                    .setPlatformName(PLATFORM_NAME)
                    .setPlatformVersion(PLATFORM_VERSION)
                    .setAutomationName("UiAutomator2")
                    .setApp(APP_PATH)
                    .setAppPackage(APP_PACKAGE)
                    .setAppActivity(APP_ACTIVITY)
                    .setNoReset(true)
                    .setUiautomator2ServerLaunchTimeout(TIMEOUT)
                    .setNewCommandTimeout(Duration.ofSeconds(COMMAND_TIMEOUT));

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Falha ao configurar o driver: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                driver = null; // Garante que um novo driver será criado no próximo teste
            } catch (Exception e) {
                System.err.println("Erro ao fechar o driver: " + e.getMessage());
            }
        }
    }

    private static void installAPK() throws IOException, InterruptedException {
        // Comando ADB para forçar a instalação
        ProcessBuilder processBuilder = new ProcessBuilder(
                "adb", "install", "-t", APP_PATH
        );
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        process.waitFor(); // Aguarda a execução do comando
    }
}

