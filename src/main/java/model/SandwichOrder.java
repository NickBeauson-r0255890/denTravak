package model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
public class SandwichOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID sandwichId;
    private String name;
    public BreadType breadType;
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS")
    public LocalDateTime creationDate;  //LocalDate!
    private BigDecimal price;
    private String mobilePhoneNumber;

    public enum BreadType {
        BOTERHAMMEKES,
        TURKS_BROOD,
        WRAP
    }

    public SandwichOrder() {

    }

    public String getCreationDate() {
        return this.creationDate.toString();
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSandwichId() {
        return this.sandwichId;
    }

    public void setSandwichId(UUID sandwichId) {
        this.sandwichId = sandwichId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }
}
