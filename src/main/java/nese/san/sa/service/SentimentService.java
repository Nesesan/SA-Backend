package nese.san.sa.service;

import nese.san.sa.entites.Client;
import nese.san.sa.entites.Sentiment;
import nese.san.sa.enums.TypeSentiment;
import nese.san.sa.repository.SentimentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class SentimentService {
    private ClientService clientService;
    private SentimentRepository sentimentRepository;

    public SentimentService(ClientService clientService, SentimentRepository sentimentRepository) {
        this.clientService = clientService;
        this.sentimentRepository = sentimentRepository;
    }

    public void creer(Sentiment sentiment){
        Client client = this.clientService.lireOuCreer(sentiment.getClient());
        sentiment.setClient(client);
        if (sentiment.getTexte().contains("pas")){
            sentiment.setType(TypeSentiment.NEGATIF);
        }else {
            sentiment.setType(TypeSentiment.POSITIF);
        }
        this.sentimentRepository.save(sentiment);
    }

    public List<Sentiment> rechercher(TypeSentiment type) {
        if(type ==  null){
            return  this.sentimentRepository.findAll();
        }else{
            return this.sentimentRepository.findByType(type);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public void supprimer(@PathVariable int id) {
        this.sentimentRepository.deleteById(id);
    }
}
