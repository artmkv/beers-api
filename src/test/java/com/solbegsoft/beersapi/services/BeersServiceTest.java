package com.solbegsoft.beersapi.services;


import com.solbegsoft.beersapi.models.RootBeer;
import com.solbegsoft.beersapi.models.dto.RootBeerDto;
import com.solbegsoft.beersapi.models.requests.RequestRootBeerDto;
import com.solbegsoft.beersapi.repositories.PunkApiRepository;
import com.solbegsoft.beersapi.utils.RootBeerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test BeerService
 */
@SpringBootTest
@AutoConfigureMockMvc
class BeersServiceTest {

    /**
     * @see PunkApiRepository
     */
    @MockBean
    private PunkApiRepository punkApiRepository;

    /**
     * @see BeersService
     */
    @Autowired
    private BeersService beersService;

    /**
     * @see RootBeerMapper
     */
    @Autowired
    private RootBeerMapper rootBeerMapper;

    /**
     * Test method {@link BeersService#getBeers(RequestRootBeerDto)}
     * With parts fields request should return List with {@link RootBeer}
     */
    @Test
    void testGetBeers_WithEmptyRequest_ShouldReturnList() {
        RequestRootBeerDto request = new RequestRootBeerDto();
        List<RootBeer> beers = new ArrayList<>();
        beers.add(createRootBeer(2, "Beer"));
        beers.add(createRootBeer(3, "Zywec"));
        List<RootBeerDto> expectedList = rootBeerMapper.mapListToDto(beers);

        when(punkApiRepository.getRootBeers(request)).thenReturn(beers);

        List<RootBeerDto> actualList = beersService.getBeers(request);
        assertEquals(expectedList, actualList);
    }

    /**
     * Test method {@link BeersService#getBeers(RequestRootBeerDto)}
     * With parts fields request should return empty List
     */
    @Test
    void testGetBeers_WithPartsFieldsRequest_ShouldReturnEmptyList() {
        RequestRootBeerDto request = new RequestRootBeerDto();
        request.setBeerName("Beer");
        request.setFoodName("Pizza");
        request.setAbvGt(10.2);
        List<RootBeer> beers = new ArrayList<>();
        List<RootBeerDto> expectedList = rootBeerMapper.mapListToDto(beers);

        when(punkApiRepository.getRootBeers(request)).thenReturn(beers);

        List<RootBeerDto> actualList = beersService.getBeers(request);
        assertEquals(expectedList, actualList);
    }

    /**
     * Test method {@link BeersService#getBeers(RequestRootBeerDto)}
     * With full field request should return empty List
     */
    @Test
    void testGetBeers_WithFullFieldRequest_ShouldReturnEmptyList() {
        RequestRootBeerDto request = createFullFieldRequest();
        List<RootBeer> beers = new ArrayList<>();
        List<RootBeerDto> expectedList = rootBeerMapper.mapListToDto(beers);

        when(punkApiRepository.getRootBeers(request)).thenReturn(beers);

        List<RootBeerDto> actualList = beersService.getBeers(request);
        assertEquals(expectedList, actualList);
    }

    /**
     * Test method {@link BeersService#getBeers(RequestRootBeerDto)}
     * With full field request should return List
     */
    @Test
    void testGetBeers_WithFullFieldRequest_ShouldReturnList() {
        RequestRootBeerDto request = createFullFieldRequest();
        List<RootBeer> beers = new ArrayList<>();
        List<RootBeerDto> expectedList = rootBeerMapper.mapListToDto(beers);

        when(punkApiRepository.getRootBeers(request)).thenReturn(beers);

        List<RootBeerDto> actualList = beersService.getBeers(request);
        assertEquals(expectedList, actualList);
    }

    /**
     * Test method {@link BeersService#getBeers(RequestRootBeerDto)}
     * Should return empty List
     */
    @Test
    void testGetBeers_WithRequestNull_ShouldReturnEmptyList() {
        List<RootBeer> beers = new ArrayList<>();
        List<RootBeerDto> expectedList = rootBeerMapper.mapListToDto(beers);

        when(punkApiRepository.getRootBeers(null)).thenReturn(beers);

        List<RootBeerDto> actualList = beersService.getBeers(null);
        assertEquals(expectedList, actualList);
    }

    /**
     * Create RootBeer with parameters
     *
     * @param id   integer Id
     * @param name String name
     * @return {@link RootBeer}
     */
    private RootBeer createRootBeer(Integer id, String name) {

        RootBeer beer = new RootBeer();
        beer.setId(id);
        beer.setName(name);
        beer.setTagline("German");
        beer.setFirstBrewed("2001-10-18");
        beer.setDescription("German beer");
        beer.setImageUrl("http://GermanBeer.png");
        beer.setAbv(4.2);
        beer.setIbu(8.1);
        beer.setTargetFg(1020.5);
        beer.setTargetOg(1060.8);
        beer.setEbc(7.4);
        beer.setSrm(4.6);
        beer.setPh(3.7);
        beer.setAttenuationLevel(88.8);
        beer.setFoodPairing(createFoodsList());
        beer.setBrewersTips("Something");
        beer.setContributedBy("Somebody");

        return beer;
    }

    /**
     * Create List of food
     *
     * @return {@link ArrayList} of food
     */
    private ArrayList<String> createFoodsList() {
        ArrayList<String> foods = new ArrayList<>();
        foods.add("Burger");
        foods.add("Fish");
        foods.add("Pizza");
        return foods;
    }

    /**
     * Create {@link RequestRootBeerDto} with fields
     *
     * @return {@link RequestRootBeerDto}
     */
    private RequestRootBeerDto createFullFieldRequest() {

        return RequestRootBeerDto.builder()
                .beerName("Beer")
                .foodName("Pizza")
                .ibuGt(10.0)
                .ibuLt(50.0)
                .abvGt(5.0)
                .abvLt(40.0)
                .ebcGt(1.0)
                .ebcLt(80.0)
                .build();
    }
}