package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DatePicker {

    private final SelenideElement dateInput = $("[id=dateOfBirthInput]");
    private final SelenideElement monthSelect = $(".react-datepicker__month-select");
    private final SelenideElement yearSelect = $(".react-datepicker__year-select");

    public void selectDate(String month, String year, String day) {
        dateInput.click();
        monthSelect.$(byText(month)).click();
        yearSelect.$(byText(year)).click();
        $(String.format(".react-datepicker__day--0%s", day)).click();
    }
}
