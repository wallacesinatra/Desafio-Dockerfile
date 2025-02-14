package estudo.desafio.entity;

import estudo.desafio.dto.PostRequest;
import jakarta.persistence.*;


@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STREET_NAME")
    private String streetName;

    @Column(name = "NUMBER")
    private Long number;

    @Column(name = "COMPLEMENT")
    private String complement;

    @Column(name = "NEIGHBOURHOOD")
    private String neighbourhood;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "ZIPCODE")
    private String zipcode;

    @Column(name = "LATITUDE")
    private String latitude;

    @Column(name = "LONGITUDE")
    private String longitude;

    public Address(PostRequest postRequest) {
        this.streetName = postRequest.getStreetName();
        this.number = postRequest.getNumber();
        this.complement = postRequest.getComplement();
        this.neighbourhood = postRequest.getNeighbourhood();
        this.city = postRequest.getCity();
        this.state = postRequest.getState();
        this.country = postRequest.getCountry();
        this.zipcode = postRequest.getZipcode();
        this.latitude = postRequest.getLatitude();
        this.longitude = postRequest.getLongitude();
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
