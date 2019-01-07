package controller;

import application.Application;
import db.OrderRepository;
import db.SandwichRepository;
import model.Sandwich;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
// import static model.SandwichOrderTestBuilder.aDefaultSandwichOrder;
import static model.SandwichTestBuilder.aDefaultSandwich;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SandwichOrderControllerIntegrationTest extends AbstractControllerIntegrationTest {


    private Sandwich testSandwich;

    @Autowired
    private SandwichRepository sandwichRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Before
    public void setUpASavedSandwich() {
        sandwichRepository.deleteAll();
        orderRepository.deleteAll();
        Sandwich testSandwich = aDefaultSandwich().withName("Americain").withIngredients("Americain").withPrice("4.20").build();

        sandwichRepository.save(testSandwich);

    }

    @Test
    public void testGETSandwichOrders_whenNoSavedOrders_returnsEmptyList() {

        String actualSandwiches = HTTP_GET("/orders");

        String expectedSandwiches = "[]";

        assertThatJson(actualSandwiches).isEqualTo(expectedSandwiches);


    }

    /*

    @Test
    public void testPOSTSandwichOrders_WhenSandwichOrderAdded_returnsAddedSandwichOrderAsJson() {

        SandwichOrder order = aDefaultSandwichOrder().forSandwich(testSandwich).withBreadType(SandwichOrder.BreadType.BOTERHAMMEKES).withMobileNumber("0498/123456").build();

        String actualSandwichOrders = HTTP_POST("/orders", order);

        String expectedSandwichOrders = "{\"id\":\"${json-unit.ignore}\",\"sandwichId\":\"" + testSandwich.getId() + "\",\"name\":\"Americain\",\"breadType\":\"BOTERHAMMEKES\",\"creationDate\":\"${json-unit.ignore}\",\"price\":3.5,\"mobilePhoneNumber\":\"0498/123456\"}";

        assertThatJson(actualSandwichOrders).isEqualTo(expectedSandwichOrders);
    }


    @Test
    public void testGETSandwichOrders_whenSavedSandwichOrders_returnsSavedSandwichOrdersList() {

        SandwichOrder testOrder = aDefaultSandwichOrder().withName("Kaas").withIngredients("Kaas").withPrice("3.80").build();

        sandwichRepository.save(testSandwich);
        orderRepository.save(testOrder);

        String actualSandwichOrderAsJson = HTTP_GET("/orders");

        String expectedSandwichOrderAsJson = "[{\"id\":\"${json-unit.ignore}\",\"sandwichId\":\"" + testSandwich.getId() + "\",\"name\":\"Americain\",\"breadType\":\"BOTERHAMMEKES\",\"creationDate\":\"${json-unit.ignore}\",\"price\":3.5,\"mobilePhoneNumber\":\"0487/123456\"}]";


        assertThatJson(actualSandwichOrderAsJson).isEqualTo(expectedSandwichOrderAsJson);


    }
*/

}
