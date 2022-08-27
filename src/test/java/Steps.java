import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Steps {
    @Step("Open the main page")
    public void openMainPage() {
        open("");
    }

    @Step("Search for the repo {repo}")
    public void searchForRepo(String repo) {
        $("[data-test-selector=nav-search-input]").sendKeys(repo);
        $("[data-test-selector=nav-search-input]").submit();
        $("[data-test-selector=nav-search-input]").click();
    }

    @Step("Click on the repo {repo} link")
    public void clickOnRepo(String repo) {
        $(By.linkText(repo)).click();
    }

    @Step("Click on Issues Tab")
    public void clickOnIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Should have the issue #{issue}")
    public void shouldHaveIssue(int issue) {
        $(withText("#" + issue)).should(exist);
    }
}
