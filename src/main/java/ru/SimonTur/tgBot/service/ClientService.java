package ru.SimonTur.tgBot.service;

import ru.SimonTur.tgBot.model.Client;
import java.util.List;

public interface ClientService {
    List<Client> searchClientsByName(String name);
}