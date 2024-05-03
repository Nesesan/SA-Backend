package nese.san.sa.service;

import nese.san.sa.entites.Client;
import nese.san.sa.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {


    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void creer(Client client) {
       Client clientDansLaBDD = this.clientRepository.findByEmail(client.getEmail());
       if (clientDansLaBDD == null) {
           this.clientRepository.save(client);
       }

    }

    public List<Client> rechercher(){
       return  this.clientRepository.findAll();
    }

    public Client lire(int id) {
        Optional<Client> optionalClient = this.clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    public Client lireOuCreer(Client clientAcreer) {
        Client clientDansLaBDD = this.clientRepository.findByEmail(clientAcreer.getEmail());
        if (clientDansLaBDD == null) {
            clientDansLaBDD = this.clientRepository.save(clientAcreer);
        }
        return clientDansLaBDD;
    }

    public void modifier(int id, Client client) {
        Client clientDansLaBDD = this.lire(id);
        if(clientDansLaBDD.getId() == client.getId()) {
            clientDansLaBDD.setEmail(client.getEmail());
            clientDansLaBDD.setTelephone(client.getTelephone());
            this.clientRepository.save(clientDansLaBDD);
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public void supprimer(@PathVariable int id) {

        this.clientRepository.deleteById(id);
    }
}
