package co.edu.usergioarboleda.alquiler.cabin.app.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "score")
public class Score implements Serializable {

    private static final long serialVersionUID = 7L;

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;
    private Integer score;
    private String message;

    @ManyToOne
    @JoinColumn(name = "reservationId")
    @JsonIgnoreProperties("scores")
    private Reservation reservation;
    /*
     * @OneToMany(cascade = { CascadeType.PERSIST }, mappedBy = "client")
     * 
     * @JsonIgnoreProperties("client")
     * private List<Message> messages;
     * 
     */
}
