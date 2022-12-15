package ru.dinerik.tacocloud;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

// Проверка для домашней страницы
@WebMvcTest(HomeController.class)           // Тест для MVC
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;        // Внедряем MockMvc для тестирования без запуска сервера

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())     // Статус должен быть HTTP 200 (OK)
                .andExpect(view().name("home"))    // Имя у представления должно быть home
                .andExpect(content().string(
                        containsString("Welcome to...")     // содержание текста
                ));
    }
}
