package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class CustomerClientTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void testGetCustomerById() {
        CustomerDto dto = customerClient.getCustomerById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void saveNewCustomer() {
        CustomerDto dto = customerClient.getCustomerById(UUID.randomUUID());
        URI uri = customerClient.saveNewCustomer(dto);
        assertNotNull(uri);
        log.info("Saved URI: {}" + uri.toString());
    }

    @Test
    void updateCustomer() {
        CustomerDto dto = CustomerDto.builder().name("New Customer").build();
        customerClient.updateCustomer(UUID.randomUUID(), dto);
    }

    @Test
    void deleteCustomer() {
        customerClient.deleteCustomer(UUID.randomUUID());
    }
}