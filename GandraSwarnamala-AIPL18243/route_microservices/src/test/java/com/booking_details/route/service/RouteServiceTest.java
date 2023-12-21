package com.booking_details.route.service;

import com.booking_details.route.exception.RouteIdNotFoundException;
import com.booking_details.route.model.RouteModel;
import com.booking_details.route.repository.RouteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {
    @Mock
    private RouteRepository routeRepository;
    @InjectMocks
    private RouteServiceImpl routeService;
    private static RouteModel routeModel1;
    private static RouteModel routeModel2;
    private static List<RouteModel> routeModelList;
    @BeforeAll
    static void setupModels(){
        routeModel1 = new RouteModel(17,"chennai","hyderabad",23.87);
        routeModel2 = new RouteModel(16,"mumbai","chennai",200.8);
        routeModelList = new ArrayList<>();
        routeModelList.add(routeModel1);
        routeModelList.add(routeModel2);

    }
    @Test
    void addRouteDetailsTest(){
        Mockito.when(routeRepository.save(routeModel1)).thenReturn(routeModel1);
        Assertions.assertEquals(routeModel1,routeService.addRouteDetails(routeModel1));
    }
    @Test
    void getAllRouteDetailsTest(){
        Mockito.when(routeRepository.findAll()).thenReturn(routeModelList);
        Assertions.assertEquals(16, routeService.getAllRouteDetails().get(1).getRouteId());
    }
    @Test
    void getAllRouteDetailsByIdTest() throws RouteIdNotFoundException {
        Optional<RouteModel> optional = Optional.of(routeModel1);
        Mockito.when(routeRepository.findById(17L)).thenReturn(optional);
        Mockito.when(routeRepository.findById(16L)).thenReturn(Optional.empty());
        Assertions.assertEquals(routeModel1,routeService.getAllRouteDetailsById(17L));
        Assertions.assertThrows(RouteIdNotFoundException.class,() -> routeService.getAllRouteDetailsById(16L));
    }
    @Test
    void updateRouteDetailsTest() throws RouteIdNotFoundException {
        Optional<RouteModel> optional = Optional.of(routeModel1);
        Mockito.when(routeRepository.findById(17L)).thenReturn(optional);
        Mockito.when(routeRepository.findById(16L)).thenReturn(Optional.empty());
        Mockito.when(routeRepository.save(routeModel1)).thenReturn(routeModel1);
        Assertions.assertEquals(routeModel1,routeService.updateRouteDetails(routeModel1));
        Assertions.assertThrows(RouteIdNotFoundException.class,() -> routeService.updateRouteDetails(routeModel2));
    }

    @Test
    void deleteRouteDetailsByIdTest() throws RouteIdNotFoundException {
        Optional<RouteModel> optional = Optional.of(routeModel1);
        Mockito.when(routeRepository.findById(17L)).thenReturn(optional);
        Mockito.when(routeRepository.findById(16L)).thenReturn(Optional.empty());
        Assertions.assertEquals("Route details for this route id deleted successfully",routeService.deleteRouteDetailsById(17L));
        Assertions.assertThrows(RouteIdNotFoundException.class,() -> routeService.deleteRouteDetailsById(16L));
    }

}
