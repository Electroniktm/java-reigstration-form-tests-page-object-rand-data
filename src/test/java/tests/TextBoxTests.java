package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import static testdata.TestData.*;

public class TextBoxTests extends TestBase {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void successfulFillFormTest() {
        textBoxPage.open();
        textBoxPage.fillName(NAME)
                .fillEmail(EMAIL)
                .fillCurrentAddress(CURRENT_ADDRESS)
                .fillPermanentAddress(PERMANENT_ADDRESS);
        textBoxPage.submit();

        textBoxPage.checkOutputVisible();
        textBoxPage.checkNameInOutput(NAME);
        textBoxPage.checkEmailInOutput(EMAIL);
        textBoxPage.checkCurrentAddressInOutput(CURRENT_ADDRESS);
        textBoxPage.checkPermanentAddressInOutput(PERMANENT_ADDRESS);
    }

    @Test
    void textBoxMinimalFieldsTest() {
        textBoxPage.open();
        textBoxPage.fillName(MINIMAL_NAME);
        textBoxPage.submit();

        textBoxPage.checkOutputVisible();
        textBoxPage.checkNameInOutput(MINIMAL_NAME);
    }

    @Test
    void textBoxEmptyFieldsTest() {
        textBoxPage.open();
        textBoxPage.submit();

        textBoxPage.checkOutputHidden();
    }

    @Test
    void textBoxTooLongNameTest() {
        textBoxPage.open();
        textBoxPage.fillName(TOO_LONG_NAME);
        textBoxPage.submit();

        textBoxPage.checkOutputVisible();
        textBoxPage.checkNameInOutput(TOO_LONG_NAME);
    }

    @Test
    void failEmailFillFormTest() {
        textBoxPage.open();
        textBoxPage.fillName(NAME)
                .fillEmail(INVALID_EMAIL)
                .fillCurrentAddress(CURRENT_ADDRESS)
                .fillPermanentAddress(PERMANENT_ADDRESS);
        textBoxPage.submit();

        textBoxPage.checkEmailErrorBorder(TEXTBOX_ERROR_BORDER);
    }
}
