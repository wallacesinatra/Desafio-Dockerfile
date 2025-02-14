package estudo.desafio.manager;

import estudo.desafio.dto.PostRequest;
import estudo.desafio.dto.PutRequest;
import estudo.desafio.dto.ResponseFeign;
import estudo.desafio.entity.Address;
import estudo.desafio.exeptions.RequiredFieldMissingException;
import estudo.desafio.exeptions.ResourceNotFoundException;
import estudo.desafio.feign.LocationFeign;
import estudo.desafio.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AddressManager {

    private final AddressRepository addressRepository;
    private final LocationFeign feign;

    public AddressManager(AddressRepository addressRepository, LocationFeign feign) {
        this.addressRepository = addressRepository;
        this.feign = feign;
    }

    public Address postAddress(PostRequest postRequest) {

        if (!validateNotNull(postRequest.getStreetName(), postRequest.getNumber(), postRequest.getNeighbourhood(),
                postRequest.getCity(), postRequest.getState(), postRequest.getCountry(), postRequest.getZipcode())) {
            throw new RequiredFieldMissingException("Required fields");
        }

        if (!validateNotNull(postRequest.getLongitude(), postRequest.getLatitude())) {

            var result = getGeocodeByAddressObject(postRequest);

            result.forEach(x -> {
                postRequest.setLongitude(x.getLon());
                postRequest.setLatitude(x.getLat());
            });

        }

        return addressRepository.save(new Address(postRequest));
    }

    private List<ResponseFeign> getGeocodeByAddressObject(PostRequest postRequest) {

        String address = postRequest.getStreetName() + " " + postRequest.getCity();

        try {
            String encodedAddress = URLEncoder.encode(address, "UTF-8");
            return feign.getGeocode(encodedAddress, "json", 1);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding the address", e);
        }
    }

    public List<Address> getAddress() {
        return addressRepository.findAll();
    }

    public Address putAddress(PutRequest putRequest) {

        Optional<Address> aux = Optional.ofNullable(addressRepository.findById(putRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Address with ID " + putRequest.getId() + " not found.")));

        if (!validateNotNull(putRequest.getStreetName(), putRequest.getNumber(), putRequest.getNeighbourhood(),
                putRequest.getCity(), putRequest.getState(), putRequest.getCountry(), putRequest.getZipcode())) {
            throw new RequiredFieldMissingException("Required fields");
        }

        Address address = aux.get();
        address.setStreetName(putRequest.getStreetName());
        address.setNumber(putRequest.getNumber());
        address.setComplement(putRequest.getComplement());
        address.setNeighbourhood(putRequest.getNeighbourhood());
        address.setCity(putRequest.getCity());
        address.setState(putRequest.getState());
        address.setCountry(putRequest.getCountry());
        address.setZipcode(putRequest.getZipcode());
        address.setLatitude(putRequest.getLatitude());
        address.setLongitude(putRequest.getLongitude());

        return addressRepository.saveAndFlush(address);
    }

    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address with ID " + id + " not found."));
        addressRepository.delete(address);
    }

    private boolean validateNotNull(Object... objects) {
        return Arrays.stream(objects).anyMatch(Objects::nonNull);
    }
}
