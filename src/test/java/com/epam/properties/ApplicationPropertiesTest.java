package com.epam.properties;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApplicationPropertiesTest {

    @Test
    public void getUrl() {
        ApplicationProperties applicationProperties = ApplicationProperties.getApplicationProperties();
        String received = applicationProperties.getUrl();
        String excepted = "jdbc:mysql://localhost/music?useUnicode=true&serverTimezone=UTC";
        assertNotNull(received);
    }

    @Test
    public void getUsername() {
        ApplicationProperties applicationProperties = ApplicationProperties.getApplicationProperties();
        String received = applicationProperties.getUsername();
        String excepted = "root";
        assertEquals(excepted, received);
    }

    @Test
    public void getPassword() {
        ApplicationProperties applicationProperties = ApplicationProperties.getApplicationProperties();
        String received = applicationProperties.getPassword();
        String excepted = "admin";
        assertEquals(excepted, received);
    }

    @Test
    public void getDriver() {
        ApplicationProperties applicationProperties = ApplicationProperties.getApplicationProperties();
        String received = applicationProperties.getDriver();
        String excepted = "com.mysql.cj.jdbc.Driver";
        assertEquals(excepted, received);
    }

}