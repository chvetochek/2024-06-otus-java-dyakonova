package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.domain.Client;
import ru.otus.domain.Phone;
import ru.otus.services.ClientService;

@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(
            ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping({"/", "/client/list"})
    public String clientsListView(Model model) {
        var newClient = new Client();
        newClient.getPhones().add(new Phone());
        model.addAttribute("newclient", newClient);

        var clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "index";
    }

    @PostMapping("/client/save")
    public RedirectView clientSave(Client client) {
        clientService.saveClient(client);
        return new RedirectView("/", true);
    }
}
