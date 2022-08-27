import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


public class AllureTests {

    private static final String REPOSITORY = "DiUS/java-faker";
    private static final int ISSUE = 738;

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://github.com/";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    void selenideWithListenerTest() {
        open("");

        $("[data-test-selector=nav-search-input]").click();
        $("[data-test-selector=nav-search-input]").sendKeys("java-faker");
        $("[data-test-selector=nav-search-input]").submit();
        $(By.linkText("DiUS/java-faker")).click();
        $("#issues-tab").click();

        $(withText("#738")).should(exist);
    }

    @Test
    void selenideWithLambdaStepsTest() {
        step("Open the main page", () -> {
            open("");
        });

        step("Search for the repo " + REPOSITORY, () -> {
            $("[data-test-selector=nav-search-input]").sendKeys(REPOSITORY);
            $("[data-test-selector=nav-search-input]").submit();
            $("[data-test-selector=nav-search-input]").click();
        });

        step("Click on the repo " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Click on Issues Tab", () -> {
            $("#issues-tab").click();
        });

        step("Should have Issue with number" + ISSUE);
        $(withText("#" + ISSUE)).should(exist);
    }

    @Test
    void selenideWithStepAnnotations() {
        Steps steps = new Steps();

        steps.openMainPage();

        steps.searchForRepo(REPOSITORY);
        steps.clickOnRepo(REPOSITORY);
        steps.clickOnIssuesTab();

        steps.shouldHaveIssue(ISSUE);
    }
}
