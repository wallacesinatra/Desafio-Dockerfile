package estudo.desafio.feign;

import estudo.desafio.dto.ResponseFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "locationMapsClient", url = "${location.maps.url}")
public interface LocationFeign {

    @GetMapping("/search")
    List<ResponseFeign> getGeocode(
            @RequestParam("q") String address,
            @RequestParam("format") String format,
            @RequestParam("limit") int limit);

}
