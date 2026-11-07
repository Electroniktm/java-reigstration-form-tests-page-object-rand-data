package utils;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public final class FakeDataProvider {

    private static final Faker FAKER = new Faker();

    private static final List<String> GENDERS = List.of("Male", "Female", "Other");

    private static final List<String> SUBJECTS = List.of(
            "English", "Math", "Physics", "Chemistry",
            "History", "Biology", "Computer Science", "Geography");

    private static final List<String> MONTHS = List.of(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");

    private static final Map<String, List<String>> STATE_CITIES = Map.of(
            "NCR", List.of("Delhi", "Gurgaon", "Noida"),
            "Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"),
            "Haryana", List.of("Karnal", "Panipat"),
            "Rajasthan", List.of("Jaipur", "Jaiselmer"));

    private static final List<String> STATES = new ArrayList<>(STATE_CITIES.keySet());

    private FakeDataProvider() {
    }

    public static String randomFirstName() {
        return FAKER.name().firstName();
    }

    public static String randomLastName() {
        return FAKER.name().lastName();
    }

    public static String randomFullName() {
        return FAKER.name().fullName();
    }

    public static String randomEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String randomPhone() {
        int firstDigit = ThreadLocalRandom.current().nextInt(6, 10);
        return firstDigit + FAKER.number().digits(9);
    }

    public static String randomGender() {
        return pick(GENDERS);
    }

    public static String randomSubject() {
        return pick(SUBJECTS);
    }

    public static String randomAddress() {
        return FAKER.address().fullAddress();
    }

    public static String randomBirthMonth() {
        return pick(MONTHS);
    }

    public static String randomBirthYear() {
        return Integer.toString(ThreadLocalRandom.current().nextInt(1970, 2024));
    }

    public static String randomBirthDay() {
        return Integer.toString(ThreadLocalRandom.current().nextInt(1, 29));
    }

    public static String randomState() {
        return pick(STATES);
    }

    public static String randomCity(String state) {
        return pick(STATE_CITIES.get(state));
    }

    private static <T> T pick(List<T> options) {
        return options.get(ThreadLocalRandom.current().nextInt(options.size()));
    }
}
