package fm.ua.bacs.testtaskrestservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.regex.Pattern;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String contact;
    private Pattern name;

    public Pattern getName() {
        return name;
    }

    public void setName(Pattern name) {
        this.name = name;
    }
}