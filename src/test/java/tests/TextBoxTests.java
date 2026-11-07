package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testdata.TestData.*;

public class TextBoxTests extends TestBase {

    @Test
    void successfulFillFormTest() {
        open("/text-box");
        $("[id=userName]").setValue(NAME);
        $("[id=userEmail]").setValue(EMAIL);
        $("[id=currentAddress]").setValue(CURRENT_ADDRESS);
        $("[id=permanentAddress]").setValue(PERMANENT_ADDRESS);
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text(NAME));
        $("[id=output] [id=email]").shouldHave(text(EMAIL));
        $("[id=output] [id=currentAddress]").shouldHave(text(CURRENT_ADDRESS));
        $("[id=output] [id=permanentAddress]").shouldHave(text(PERMANENT_ADDRESS));
    }

    @Test
    void textBoxMinimalFieldsTest() {
        open("/text-box");
        $("#userName").setValue(MINIMAL_NAME);
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("[id=output] [id=name]").shouldHave(text(MINIMAL_NAME));
    }

    @Test
    void textBoxEmptyFieldsTest() {
        open("/text-box");
        $("#submit").click();
        $("#output").shouldBe(hidden);
    }

    @Test
    void textBoxTooLongNameTest() {
        open("/text-box");
        $("#userName").setValue(TOO_LONG_NAME);
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("[id=output] [id=name]").shouldHave(text(TOO_LONG_NAME));
    }

    @Test
    void failEmailFillFormTest() {
        open("/text-box");
        $("[id=userName]").setValue(NAME);
        $("[id=userEmail]").setValue(INVALID_EMAIL);
        $("[id=currentAddress]").setValue(CURRENT_ADDRESS);
        $("[id=permanentAddress]").setValue(PERMANENT_ADDRESS);
        $("[id=submit]").click();

        $("[id=userEmail]").shouldHave(cssValue("border-color", TEXTBOX_ERROR_BORDER));
    }
}
