package nese.san.sa.entites;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true)
    private String email;
    private String telephone;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Sentiment> sentiments;
    public Client() {

    }

    public Client(int id, String email, String telephone) {
        this.id = id;
        this.email = email;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
