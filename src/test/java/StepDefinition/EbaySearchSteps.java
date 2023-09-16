package StepDefinition;


import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class EbaySearchSteps extends BaseTest{

    private ChromeDriver driver;
    private Properties properties;

    @Given("the eBay website is open")
    public void openEbayWebsite() throws IOException {

            properties = new Properties();
            FileInputStream input = new FileInputStream("C:\\Users\\cerh2\\IdeaProjects\\Ebay\\src\\test\\resources\\configfiles\\config.properties");
            properties.load(input);

            System.setProperty("webdriver.chrome.driver", properties.getProperty("chromeDriverPath"));
            ChromeOptions co = new ChromeOptions();
            co.setBinary(properties.getProperty("BinaryPath"));
            driver = new ChromeDriver(co);

            driver.manage().window().maximize();
            driver.get(properties.getProperty("ebay.url"));
    }

    @When("I enter the search keyword")
    public void enterSearchKeyword() throws InterruptedException {
        Thread.sleep(4000);
        WebElement searchBox = driver.findElement(By.name(properties.getProperty("search")));
        searchBox.sendKeys(properties.getProperty("search.keyword"));

    }

    @When("I submit the search")
    public void submitSearch() throws InterruptedException {
        Thread.sleep(4000);
        WebElement searchBox = driver.findElement(By.id(properties.getProperty("clickbuton")));
        searchBox.submit();

    }

    @Then("I should see the search results")
    public void verifySearchResults() throws InterruptedException {
        Thread.sleep(4000);
        WebElement resultCount = driver.findElement(By.className("srp-controls__count-heading"));
        String result = resultCount.getText();
        System.out.println("Número de resultados: " + result);

    }

    @Then("order price descendent")
    public void order_price_descendent() throws InterruptedException {
        Thread.sleep(4000);
        WebElement priceSort = driver.findElement(By.xpath("//div[@class='srp-controls__sort srp-controls__control']"));
        priceSort.click();

    }
    @Then("show five first product in precy")
    public void show_five_first_product_in_precy() throws InterruptedException {
        Thread.sleep(4000);
//        List<WebElement> productElements = driver.findElements(By.className("s-item__info"));
//        System.out.println("Primeros 5 productos con precios:");
//        for (int i = 0; i < 5 && i < productElements.size(); i++) {
//            WebElement product = productElements.get(i);
//            String productName = product.findElement(By.className("s-item__title")).getText();
//            String productPrice = product.findElement(By.className("s-item__price")).getText();
//            System.out.println("Producto "  + (i + 1) + ": " + productName + (i + 1) +": " + productPrice+ (i + 1) );
        List<WebElement> productList = driver.findElements(By.className("s-item"));
        for (int i = 0; i < 5; i++) {
            WebElement product = productList.get(i);
            String productName = product.findElement(By.className("s-item__title")).getText();
            String productPrice = product.findElement(By.className("s-item__price")).getText();
            System.out.println("Producto " + (i + 1) + ": " + productName + " - Precio: " + productPrice);
        }

        }

    @Then("Order and  print the products by name \\(ascendente)")
    public void order_and_print_the_products_by_name_ascendente() {
        List<WebElement> productList = driver.findElements(By.className("s-item"));
        List<WebElement> productListSortedByName = productList.stream()
                .sorted(Comparator.comparing(p -> p.findElement(By.className("s-item__title")).getText()))
                .collect(Collectors.toList());

        System.out.println("Productos ordenados por nombre (ascendente):");
        for (WebElement product : productListSortedByName) {
            String productName = product.findElement(By.className("s-item__title")).getText();
            System.out.println(productName);
        }



    }
    @Then("Order and  print the products by price \\(descendente)")
    public void order_and_print_the_products_by_price_descendente() {
        List<WebElement> productList = driver.findElements(By.className("s-item"));
        List<WebElement> productListSortedByPrice = productList.stream()
                .sorted((p1, p2) -> {
                    String price1 = p1.findElement(By.className("s-item__price")).getText();
                    String price2 = p2.findElement(By.className("s-item__price")).getText();
                    return Double.compare(Double.parseDouble(price2.replace("$", "").replace(",", "")),
                            Double.parseDouble(price1.replace("$", "").replace(",", "")));
                })
                .collect(Collectors.toList());

        System.out.println("Productos ordenados por precio (descendente):");
        for (WebElement product : productListSortedByPrice) {
            String productName = product.findElement(By.className("s-item__title")).getText();
            String productPrice = product.findElement(By.className("s-item__price")).getText();
            System.out.println(productName + " - Precio: " + productPrice);
        }


    }


    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}