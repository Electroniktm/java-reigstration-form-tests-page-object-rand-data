package testdata;

/**
 * Централизованное хранилище тестовых данных.
 * Все вводимые и проверяемые значения вынесены сюда,
 * чтобы при изменении данных не пришлось править каждый тест.
 */
public class TestData {

    // ===== TextBox: успешное заполнение =====
    public static final String NAME = "Alex Black";
    public static final String EMAIL = "alex@black.com";
    public static final String CURRENT_ADDRESS = "first address 1";
    public static final String PERMANENT_ADDRESS = "second address 2";

    // ===== TextBox: минимальные поля =====
    public static final String MINIMAL_NAME = "John Doe";

    // ===== TextBox: слишком длинное имя =====
    public static final String TOO_LONG_NAME = "JohnJohnJohnJohnJohnJohnJohnJohnJohnJohn";

    // ===== TextBox: невалидный email =====
    public static final String INVALID_EMAIL = "alex@black";

    // ===== Registration Form: основные данные =====
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String STUDENT_EMAIL = "john.doe@example.com";
    public static final String GENDER = "Male";
    public static final String PHONE = "1234567890";

    // ===== Registration Form: дата рождения =====
    public static final String BIRTH_MONTH = "June";
    public static final String BIRTH_YEAR = "2023";
    public static final String BIRTH_DAY = "5";

    // ===== Registration Form: дополнительные поля =====
    public static final String SUBJECT = "English";
    public static final String UPLOAD_FILE = "test.png";
    public static final String ADDRESS = "123 Main Street, New York";
    public static final String STATE = "Uttar Pradesh";
    public static final String CITY = "Agra";

    // ===== Registration Form: ожидаемые значения в таблице =====
    public static final String EXPECTED_STUDENT_NAME_LABEL = "Student Name";
    public static final String EXPECTED_FULL_NAME = "John Doe";
    public static final String EXPECTED_HOBBY = "Sports";

    // ===== Негативные сценарии =====
    public static final String INVALID_EMAIL_FORMAT = "john.doe@example";
    public static final String EMAIL_RU = "john.doe@example.ru";
    public static final String INVALID_PHONE = "1234567";
    public static final String TOO_LONG_FIRST_NAME =
            "JohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohn" +
            "JohnJohnJohnJohJohnJohn";

    // ===== Ожидаемые цвета рамок при ошибке =====
    public static final String REGISTRATION_ERROR_BORDER = "rgb(220, 53, 69)";
    public static final String TEXTBOX_ERROR_BORDER = "rgb(255, 0, 0)";
}
