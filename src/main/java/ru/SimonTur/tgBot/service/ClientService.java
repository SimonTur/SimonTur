package ru.SimonTur.tgBot.service;

import org.springframework.stereotype.Service;
import ru.SimonTur.tgBot.model.Client;
import java.util.List;
@Service
public interface ClientService {
    List<Client> searchClientsByName(String name);
}