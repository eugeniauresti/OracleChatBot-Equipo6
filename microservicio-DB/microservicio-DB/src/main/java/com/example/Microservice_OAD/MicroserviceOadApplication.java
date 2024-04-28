package com.example.Microservice_OAD;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.example.Microservice_OAD.controller.BotController;
import com.example.Microservice_OAD.service.*;
import com.example.Microservice_OAD.util.BotMessages;

@SpringBootApplication
public class MicroserviceOadApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MicroserviceOadApplication.class);

	@Autowired
	private TareaService tareaService;

	@Autowired
	private UsuarioService usuarioService;

	@Value("${telegram.bot.token}")
	private String telegramBotToken;

	@Value("${telegram.bot.name}")
	private String botName;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceOadApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(new BotController(telegramBotToken, botName, tareaService, usuarioService));
			logger.info(BotMessages.BOT_REGISTERED_STARTED.getMessage());
		}
		catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	

}
