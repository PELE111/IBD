package studia.inz.inzynierka;

import org.apache.tomcat.util.http.parser.Authorization;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import studia.inz.inzynierka.Controller.AuthController;
import studia.inz.inzynierka.Models.ApiRequest.CreateDiet;
import studia.inz.inzynierka.Models.ApiRequest.MealFilter;
import studia.inz.inzynierka.Models.ApiRequest.MealValues;
import studia.inz.inzynierka.Models.ApiRequest.ProductValues;
import studia.inz.inzynierka.Models.DTO.ClientDTO;
import studia.inz.inzynierka.Models.DTO.DietDTO;
import studia.inz.inzynierka.Models.Entites.DietEntity;
import studia.inz.inzynierka.Models.Entites.MealEntity;
import studia.inz.inzynierka.Models.Entites.ProductEntity;
import studia.inz.inzynierka.Repos.DietRepository;
import studia.inz.inzynierka.Repos.MealIngredientsRepository;
import studia.inz.inzynierka.Repos.MealRepository;
import studia.inz.inzynierka.Repos.ProductRepository;
import studia.inz.inzynierka.Security.TokenService;
import studia.inz.inzynierka.Service.*;

import java.lang.reflect.ParameterizedType;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private AuthController authController;
    @Autowired
    ClientService clientService;
    @Autowired
    MealService mealService;
    @Autowired
    private DietRepository dietRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MealIngredientsService mealIngredientsService;
    @Autowired
    private MealIngredientsRepository mealIngredientsRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private DietService dietService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SummaryService summaryService;



    @Test
    void contextLoads() {
    }

//Login
    @Test
    void badLoginValidationTest() {
        assert (authController.token(null).getStatusCode() == HttpStatus.UNAUTHORIZED);
    }

//Register
    @Test
    void badRegister() {
        ClientDTO clientDTO = new ClientDTO("klient", "password");
        assert (clientService.saveClient(clientDTO).getStatusCode() == HttpStatus.CONFLICT);
    }

//Product
    @Test
    void searchProductByNameTest() {
        String name = "jajko";
        List<ProductValues> products = productService.searchByName(name, null).getBody().stream().toList();
        assert (products.size() == 1);
    }

    @Test
    void searchProductByBadNameTest() {
        String name = "fdfsdfsdf";
        List<ProductValues> products = productService.searchByName(name, null).getBody().stream().toList();
        assert (products.size() == 0);
    }

    @Test
    void searchProductByPageSize() {
        List<ProductValues> products = productService.searchByName("", Pageable.ofSize(2)).getBody().stream().toList();
        assert (products.size() == 2);
    }

    @Test
    void searchAllProductsTest() {
        long size = productRepository.count();
        List<ProductValues> products = productService.searchByName("", null).getBody().stream().toList();
        assert (products.size() == size);
    }


//Diet
    @Test
    void createEmptyDietTest() {
        assert (dietService.createDiet(null).getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    void createExistingDietTest() {
        CreateDiet createDiet = new CreateDiet("klient", 1000, true);
        assert (dietService.createDiet(createDiet).getStatusCode() == HttpStatus.CONFLICT);
    }

    @Test
    void editDietTest() {
        DietDTO diet = new DietDTO();
        diet.setLogin("klient");
        assert (dietService.editDiet(diet).getStatusCode() == HttpStatus.OK);
    }

    @Test
    void editDietBadLoginTest() {
        DietDTO diet = new DietDTO();
        diet.setLogin("badLogin");
        assert (dietService.editDiet(diet).getStatusCode() == HttpStatus.NOT_FOUND);
    }


//Summary
    @Test
    void getSummaryByLoginTest() {
        assert (summaryService.getSummary("klient", Date.valueOf(LocalDate.of(2022, 12, 01))).getStatusCode() == HttpStatus.OK);
    }

    @Test
    void getSummaryTestByWrongLogin() {
        assert (summaryService.getSummary("badLogin", Date.valueOf(LocalDate.of(2022, 12, 01))).getStatusCode() == HttpStatus.NOT_FOUND);

    }

    @Test
    void getSummaryByBadDayeTest() {
        assert (summaryService.getSummary("klient", null).getStatusCode() == HttpStatus.NOT_ACCEPTABLE);
    }


}
