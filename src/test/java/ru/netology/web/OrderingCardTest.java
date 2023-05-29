package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class OrderingCardTest {


    @Test
        // Заполнение всех полей данной формы валидными значениями
    void orderOfTheCardIsTheTestFirst() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Велев Максим");
        $("[data-test-id=phone] input").setValue("+79645851953");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
        // Добавление в поле "Ф.И." Отчества с использованием пробелов
    void orderOfTheCardIsTheTestSecond() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Велев Максим Владимирович");
        $("[data-test-id=phone] input").setValue("+79645851953");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
        // Добавление в поле "Ф.И." Отчества с использованием символа дефис
    void orderOfTheCardIsTheTestThird() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Велев-Максим-Владимирович");
        $("[data-test-id=phone] input").setValue("+79645851953");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
        // Добавление в поле "Ф.И." Отчества с маленькой буквы
    void orderOfTheCardIsTheTestFourth() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("велев-максим-владимирович");
        $("[data-test-id=phone] input").setValue("+79645851953");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
        // Заполнение поля "Ф.И."  невалидными значениями
    void orderOfTheCardIsTheTestFifth() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Velev Maxim");
        $("[data-test-id=phone] input").setValue("+79645851953");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
        // Пусто поле "Ф.И."
    void orderOfTheCardIsTheTestSixth() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79645851953");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }


    @Test
        // Заполнение поля "Мобильный телефон"  невалидными  значениями
    void orderOfTheCardIsTheTestSeventh() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Велев Максим");
        $("[data-test-id=phone] input").setValue("7+9645851953");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
        // Пустое поле "Мобильный телефон"
    void orderOfTheCardIsTheTestEighth() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Велев Максим");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
        // Заполнение формы валидными значениями без использования чекбокса
    void orderOfTheCardIsTheTestNinth() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Велев Максим");
        $("[data-test-id=phone] input").setValue("+79645851953");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
        // Пустая форма
    void orderOfTheCardIsTheTestTenth() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}
