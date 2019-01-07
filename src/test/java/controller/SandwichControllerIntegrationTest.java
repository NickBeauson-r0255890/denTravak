package controller;


import application.Application;
import db.SandwichRepository;
import model.Sandwich;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static model.SandwichTestBuilder.aDefaultSandwich;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SandwichControllerIntegrationTest extends AbstractControllerIntegrationTest {


    @Autowired
    private SandwichRepository sandwichRepository;

    @Before
    public void SetUpASavedSandwich() {
        sandwichRepository.deleteAll();
    }


    @Test
    public void testGETSandwiches_whenNoSavedSandwiches_returnsEmptyList() {

        String actualSandwiches = HTTP_GET("/sandwiches");
        String expectedSandwiches = "[]";

        assertThatJson(actualSandwiches).isEqualTo(expectedSandwiches);
    }


    @Test
    public void testPOSTSandwich_WhenSandwichAdded_returnsAddedSandwichAsJson() {

        Sandwich testSandwich = aDefaultSandwich().withName("Kaas").withIngredients("Kaas").withPrice("3.80").build();

        String actualSandwichAsJson = HTTP_POST("/sandwiches", testSandwich);

        String expectedSandwichAsJson = "{\"id\":\"${json-unit.ignore}\",\"name\":\"Kaas\",\"ingredients\":\"Kaas\",\"price\":3.80}";

        assertThatJson(actualSandwichAsJson).isEqualTo(expectedSandwichAsJson);

    }


    @Test
    public void testPUTSandwich_WhenNameChanged_returnsSandwichWithNameChanged() {
        Sandwich testSandwich = aDefaultSandwich().withName("Kaas").withIngredients("Kaas").withPrice("3.80").build();

        testSandwich.setName("Hesp");
        sandwichRepository.save(testSandwich);

        String actualSandwichAsJson = HTTP_PUT("/sandwiches/" + testSandwich.getId(), testSandwich);

        String expectedSandwichAsJson = "{\"id\":\"${json-unit.ignore}\",\"name\":\"Hesp\",\"ingredients\":\"Kaas\",\"price\":3.80}";

        assertThatJson(actualSandwichAsJson).isEqualTo(expectedSandwichAsJson);

    }


    @Test
    public void testGETSandwiches_whenSavedSandwiches_returnsSavedSandwichesList(){

        Sandwich testSandwich = aDefaultSandwich().withName("Kaas").withIngredients("Kaas").withPrice("3.80").build();

        sandwichRepository.save(testSandwich);

        String actualSandwichAsJson = HTTP_GET("/sandwiches");

        String expectedSandwichAsJson = "[{\"id\":\"${json-unit.ignore}\",\"name\":\"Kaas\",\"ingredients\":\"Kaas\",\"price\":3.80}]";


        assertThatJson(actualSandwichAsJson).isEqualTo(expectedSandwichAsJson);


    }


}
