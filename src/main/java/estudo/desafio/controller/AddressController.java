package estudo.desafio.controller;

import estudo.desafio.dto.PostRequest;
import estudo.desafio.dto.PutRequest;
import estudo.desafio.dto.Response;
import estudo.desafio.manager.AddressManager;
import estudo.desafio.mapper.AddressMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressManager addressManager;

    public AddressController(AddressManager adressManager) {
        this.addressManager = adressManager;
    }

    @PostMapping
    public ResponseEntity include(@RequestBody PostRequest postRequest) {
        var result = addressManager.postAddress(postRequest);
        Response response = AddressMapper.INSTANCE.entityToResponse(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity search() {
        var result = addressManager.getAddress();
        List<Response> response = AddressMapper.INSTANCE.entityListToResponse(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody PutRequest request) {
        var result = addressManager.putAddress(request);
        Response response = AddressMapper.INSTANCE.entityToResponse(result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) {
        addressManager.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted registration number: " + id);
    }
}
