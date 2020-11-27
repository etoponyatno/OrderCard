package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderCardTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void ifAllValueCorrect() {
        $("[data-test-id=name] input").setValue("Петр");
        $("[data-test-id=phone] input").setValue("+7912345678");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void ifNameInEnglish() {
        $("[data-test-id=name] input").setValue("Petr");
        $("[data-test-id=phone] input").setValue("+79123456789");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void ifNameIsEmpty() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79123456789");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void ifNameInInt() {
        $("[data-test-id=name] input").setValue("1234");
        $("[data-test-id=phone] input").setValue("+79123456789");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void ifNumberLessTen() {
        $("[data-test-id=name] input").setValue("Петр");
        $("[data-test-id=phone] input").setValue("+7912345678");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void ifNumberMoreTen() {
        $("[data-test-id=name] input").setValue("Петр");
        $("[data-test-id=phone] input").setValue("+791234567890");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void ifNumberIsSymbol() {
        $("[data-test-id=name] input").setValue("Петр");
        $("[data-test-id=phone] input").setValue("qwerty");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void ifNumberIsEmpty() {
        $("[data-test-id=name] input").setValue("Петр");
        $("[data-test-id=phone] input").setValue("+79123456789");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

}
