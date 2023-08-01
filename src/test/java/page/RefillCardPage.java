package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class RefillCardPage {
    private SelenideElement heading = $(withText("Пополнение карты"));
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement fromField = $("[data-test-id='from'] input");
    private SelenideElement toField = $("[data-test-id='to'] input");
    private SelenideElement refillButton = $("[data-test-id='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");
    private SelenideElement errorMessage = $("[data-test-id='error-notification'] .notification__content");

    public RefillCardPage() {
        heading.shouldBe(Condition.visible);
    }

    public DashboardPage getValidTransfer(String sum, DataHelper.CardInfo cardInfo) {
        refillCard(sum, cardInfo);
        return new DashboardPage();
    }

    public void refillCard(String sum, DataHelper.CardInfo cardInfo) {
        amountField.sendKeys(sum);
        fromField.sendKeys(cardInfo.getCardNumber());
        refillButton.click();
    }

    public void getInvalidTransfer(String errorText) {
        errorMessage.shouldHave(Condition.exactText(errorText),
                Duration.ofSeconds(10)).shouldBe(Condition.visible);
    }

    public DashboardPage cancelRefill() {
        cancelButton.click();
        return new DashboardPage();
    }
}
