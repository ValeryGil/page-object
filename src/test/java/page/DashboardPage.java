package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private static ElementsCollection cards = $$(".list__item div");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    private SelenideElement deposit = $("[data-test-id='action-deposit']");
    private SelenideElement reload = $("[data-test-id='action-reload']");

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public RefillCardPage selectCard(DataHelper.CardInfo cardInfo) {
        cards.findBy(Condition.attribute("data-test-id", cardInfo.getCardID()))
                .$("[data-test-id='action-deposit']").click();
        return new RefillCardPage();
    }

    public SelenideElement findCard(DataHelper.CardInfo card) {
        return cards.findBy(Condition.attribute("data-test-id", card.getCardID()));
    }

    public int getCardBalance(DataHelper.CardInfo card) {
        val text = findCard(card).text();
        return extractBalance(text);
    }
}
