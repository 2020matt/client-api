package za.co.api.processor.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import za.co.api.processor.client.model.Client;
import za.co.api.processor.client.repository.ClientRepository;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientRestController.class)
public class ClientControllerTest {

    @MockBean
    ClientRepository clientRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnNotFoundClient() throws Exception {
        String firstName = "JohnDoe";

        when(clientRepository.findByFirstName(firstName)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/clients/{name}", firstName))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldReturnClient() throws Exception {

        Client client = new Client();
        client.setFirstName("Matthews");
        client.setLastName("Ramolibe");
        client.setIdNumber(8910246009088L);
        client.setId(1);
        client.setMobileNumber("0745417429");
        client.setAddress("JHB");

        when(clientRepository.findByFirstName(client.getFirstName())).thenReturn(Optional.of(client));
        mockMvc.perform(get("/api/clients/{name}", client.getFirstName()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldReturnClientByIdNumber() throws Exception {

        Client client = new Client();
        client.setFirstName("Matthews");
        client.setLastName("Ramolibe");
        client.setIdNumber(8910246109088L);
        client.setId(1);
        client.setMobileNumber("0745417429");
        client.setAddress("JHB");

        when(clientRepository.findByIdNumber(client.getId())).thenReturn(Optional.of(client));
        mockMvc.perform(get("/api/clients/id?id=" + client.getIdNumber()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldNoReturnClientByIdNumber() throws Exception {

        Client client = new Client();
        client.setFirstName("Matthews");
        client.setLastName("Ramolibe");
        client.setIdNumber(123456L);
        client.setId(1);
        client.setMobileNumber("0745417429");
        client.setAddress("JHB");

        when(clientRepository.findByIdNumber(client.getId())).thenReturn(Optional.of(client));
        mockMvc.perform(get("/api/clients/id" , client.getIdNumber()))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundByPhoneNumber() throws Exception {

        Client client = new Client();
        client.setFirstName("Matthews");
        client.setLastName("Ramolibe");
        client.setIdNumber(8910246109088L);
        client.setId(1);
        client.setMobileNumber("07454174");
        client.setAddress("JHB");
        String pNumber = client.getMobileNumber();

        when(clientRepository.findByMobileNumber(pNumber)).thenReturn(Optional.of(client));
        mockMvc.perform(get("/api/clients/phoneNumber?pNumber="+pNumber))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldReturnClientByPhoneNumber() throws Exception {

        Client client = new Client();
        client.setFirstName("Matthews");
        client.setLastName("Ramolibe");
        client.setIdNumber(8910246109088L);
        client.setId(1);
        client.setMobileNumber("0745417429");
        client.setAddress("JHB");
        String pNumber = client.getMobileNumber();

        when(clientRepository.findByMobileNumber(pNumber)).thenReturn(Optional.of(client));
        mockMvc.perform(get("/api/clients/phoneNumber?pNumber="+pNumber))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
