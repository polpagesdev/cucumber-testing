package steps;

// Imports
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;
// Selenium
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
// Cucumber
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

/***
 * This class defines all the steps necessary to implement the feature tests.
 * The DRY pattern is kept in mind at all times, and we have tried to reuse as
 * many functions as possible.
 */
public class FeatureSteps {
    private WebDriver driver;

    /***
     * Declare the Before and After functions to set up the driver and remove it
     * once we are done.
     */
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /***
     * Generic steps definitions
     */
    @Given("the user is on the {string} page")
    public void the_user_is_on_the_page(String page) {
        if (page.equals("home")) {
            driver.get("https://automationteststore.com/");
        } else {
            driver.get("https://automationteststore.com/index.php?rt=" + page);
        }
    }

    @Given("the user is set up")
    public void the_user_is_set_up() {
        the_user_is_on_the_page("account/login");
        the_user_inputs_string_in_field("thisisatestaccount", "loginFrm_loginname", "input", "id");
        the_user_inputs_string_in_field("test", "loginFrm_password", "input", "id");
        the_user_clicks_the_button("Login", "button", "title");
        the_user_is_on_the_page("home");
    }

    @When("the user clicks the {string} button [with tag {string} and attribute {string}]")
    public void the_user_clicks_the_button(String btnName, String btnTag, String btnAttr) {
        driver.findElement(By.xpath("//" + btnTag + "[@" + btnAttr + "=\"" + btnName + "\"]")).click();
    }

    @When("the user inputs {string} in {string} [with tag {string} and attribute {string}]")
    public void the_user_inputs_string_in_field(String input, String value, String fieldTag, String fieldAttr) {
        WebElement inputField = driver.findElement(By.xpath("//" + fieldTag + "[@" + fieldAttr + "=\"" + value + "\"]"));
        inputField.sendKeys(input);
    }

    @Then("the user should be redirected to the {string} page")
    public void the_user_should_be_redirected_to_the_page(String expectedPage) {
        assertTrue(driver.getCurrentUrl().contains(expectedPage));
    }

    @When("the user {string} an item in their cart")
    public void the_user_changes_an_item_in_their_cart(String action) {
        if (action.equals("adds")) {
            the_user_is_on_the_page("product/category&path=49");
            the_user_clicks_the_button("Add to Cart", "a", "title");
        } else {
            the_user_clicks_the_button("btn btn-sm btn-default", "a", "class");
        }
    }

    @When("the user {string} the item quantity")
    public void the_user_changes_the_item_quantity(String action) {
        WebElement quantityField = driver.findElement(By.xpath("//input[@class=\"form-control short\"]"));
        int quantity = Integer.parseInt(quantityField.getAttribute("value"));
        if (action.equals("increases")) {
            quantityField.clear();
            quantityField.sendKeys(String.valueOf(quantity + 1));
        } else if (action.equals("decreases")) {
            if (quantity > 1) {
                quantityField.clear();
                quantityField.sendKeys(String.valueOf(quantity - 1));
            }
        }
        the_user_clicks_the_button("Update", "button", "title");
    }

    @When("the user selects the {string} currency")
    public void user_changes_currency(String currency) {
        the_user_clicks_the_button("dropdown-toggle", "a", "class");
        the_user_clicks_the_button("https://automationteststore.com/index.php?rt=checkout/cart&currency=" + currency, "a", "href");
    }

    @And("the user waits {string} seconds")
    public void the_user_waits(String durationStr) {
        int duration = Integer.parseInt(durationStr) * 1000;
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("the user scrolls {double} % of the page")
    public void scrollToPercentageOfPage(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Convert the percentage to a decimal fraction
        double scrollFraction = percentage / 100;
        // Calculate the scroll amount based on the page height
        Long scrollAmount = (Long) js.executeScript("return document.body.scrollHeight") * (long) (scrollFraction);
        // Scroll to the calculated position
        js.executeScript("window.scrollTo(0, arguments[0])", scrollAmount);
    }

    @Then("the user should receive a {string} message [with tag {string} and attribute {string}]")
    public void user_receives_message(String msgValue, String msgTag, String msgAttr) {
        WebElement msg = driver.findElement(By.xpath("//" + msgTag + "[@" + msgAttr + "=\"" + msgValue + "\"]"));
        assertNotNull(msg);
    }

    @And("the user selects the option {string} [with attribute {string} and value {string}]")
    public void user_selects(String option, String attr, String value) {
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@" + attr + "=\"" + value + "\"]")));
        dropdown.selectByValue(option);
    }

    /***
     * Feature: Accessibility
     */
    // Scenario: User changes the currency
    @Then("prices should be displayed in {string}")
    public void prices_displayed_in_currency(String currency) {
        List<WebElement> tdElements = driver.findElements(By.xpath("//td[@class=\"align_right\"]"));
        String unitPriceElement = tdElements.getFirst().getText();
        switch (currency) {
            case "EUR":
                assertTrue(unitPriceElement.contains("€"));
                break;
            case "USD":
                assertTrue(unitPriceElement.contains("$"));
                break;
            case "GBP":
                assertTrue(unitPriceElement.contains("£"));
                break;
        }
    }

    /***
     * Feature: Account
     */
    // Scenario: User registers a new account
    @When("the user enters valid registration details")
    public void the_user_enters_valid_registration_details() {
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000); // Generate a random number between 1000 and 9999
        the_user_inputs_string_in_field("randomtestfirstname", "AccountFrm_firstname", "input", "id");
        the_user_inputs_string_in_field("randomtestlastname", "AccountFrm_lastname", "input", "id");
        the_user_inputs_string_in_field("testemail" + randomNumber + "@cucumbertesting.com", "AccountFrm_email", "input", "id");
        the_user_inputs_string_in_field("randomtestaddressthatdontexist", "AccountFrm_address_1", "input", "id");
        the_user_inputs_string_in_field("randomtestcitythatdontexist", "AccountFrm_city", "input", "id");
        user_selects("3522", "id", "AccountFrm_zone_id"); // Select by value, the corresponding text is "Bristol"
        the_user_inputs_string_in_field("01234", "AccountFrm_postcode", "input", "id");
        user_selects("222", "id", "AccountFrm_country_id"); // Select by value, the corresponding text is "United Kingdom"
        the_user_inputs_string_in_field("thisisatestaccount" + randomNumber, "AccountFrm_loginname", "input", "id");
        the_user_inputs_string_in_field("test", "AccountFrm_password", "input", "id");
        the_user_inputs_string_in_field("test", "AccountFrm_confirm", "input", "id");
        the_user_clicks_the_button("newsletter", "input", "name");
        the_user_clicks_the_button("agree", "input", "name");
    }

    /***
     * Feature: Cart
     */
    // Scenario: User changes the quantity of an item in their cart
    @Then("the cart should update the total price accordingly")
    public void the_cart_should_update_price() {
        // Find the input element for quantity and retrieve its value
        WebElement quantityInput = driver.findElement(By.xpath("//input[@class=\"form-control short\"]"));
        String quantityValue = quantityInput.getAttribute("value");
        int quantity = Integer.parseInt(quantityValue);
        // Find all td elements within the tr
        List<WebElement> tdElements = driver.findElements(By.xpath("//td[@class=\"align_right\"]"));
        // The index is 3 because list indexes are zero-based
        String unitPriceElement = tdElements.getFirst().getText();
        // Assuming the price is in format "$29.50", we remove the first character and parse it to double
        double unitPrice = Double.parseDouble(unitPriceElement.substring(1));
        // Calculate the expected total price
        double expectedTotal = quantity * unitPrice;
        // Find the element for total price and retrieve its text
        String totalPriceElement = tdElements.get(1).getText();
        double actualTotal = Double.parseDouble(totalPriceElement.substring(1));
        // Compare the expected total with the actual total
        assertEquals("The total price did not update as expected.", expectedTotal, actualTotal, 0.01);
    }

    // Scenario: User removes an item from their cart
    @Then("the cart should no longer contain the item")
    public void the_cart_doesnt_contain_item() {
        List<WebElement> tdElements = driver.findElements(By.xpath("//a[@href=\"https://automationteststore.com/index.php?rt=product/product&product_id=62&key=62\"]"));
        assertTrue(tdElements.isEmpty());
    }

    /***
     * Feature: Sitemap
     */
    // Scenario:
    @Then("the user should see all categories listed")
    public void check_sitemap_categories_list() {
        List<WebElement> categoriesListElements = driver.findElements(By.xpath("//li[@class=\"list-group-item\"]"));
        assertEquals(46, categoriesListElements.size());
    }

    /***
     * Feature: Special Offers
     */
    // Scenario: User adds a sale product to the shopping cart and checks discount
    @Then("the price should be the same after adding the product to the cart")
    public void price_should_be_equal() {
        WebElement discountedProductPriceElement = driver.findElement(By.xpath("//div[@class=\"productfilneprice\"]"));
        String discountedProductPrice = discountedProductPriceElement.getText().trim();
        the_user_clicks_the_button("cart", "a", "class");
        List<WebElement> tdElements = driver.findElements(By.xpath("//td[@class=\"align_right\"]"));
        String cartProductPriceElement = tdElements.getFirst().getText();
        assertEquals(discountedProductPrice, cartProductPriceElement);
    }
}