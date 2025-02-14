package estudo.desafio.mapper;

import estudo.desafio.dto.Response;
import estudo.desafio.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Response entityToResponse (Address address);

    List<Response> entityListToResponse (List<Address> address);
}
