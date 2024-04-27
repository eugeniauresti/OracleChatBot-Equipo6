package com.example.Microservice_OAD.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.example.Microservice_OAD.model.Tarea;
import com.example.Microservice_OAD.model.Usuario;
import com.example.Microservice_OAD.service.TareaService;
import com.example.Microservice_OAD.service.UsuarioService;
import com.example.Microservice_OAD.util.BotCommands;
import com.example.Microservice_OAD.util.BotHelper;
import com.example.Microservice_OAD.util.BotLabels;
import com.example.Microservice_OAD.util.BotMessages;

public class BotController extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(BotController.class);
	private TareaService tareaService;
	private UsuarioService usuarioService;
	private String botName;
	private String botToken;

	@Autowired
    public BotController(String botToken, String botName, TareaService tareaService, UsuarioService usuarioService) {
        logger.info("Bot Token: " + botToken);
        logger.info("Bot Name:" + botName);
        this.tareaService = tareaService;
        this.botName = botName;
        this.botToken = botToken;
        this.usuarioService = usuarioService;
    }

	@Override
    public String getBotUsername() {
        return this.botName;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

	@Override
    public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {
		
			long chatId = update.getMessage().getChatId();
            long telegramId = update.getMessage().getFrom().getId();
            
            // Check if the telegramId is registered
            Usuario usuario = usuarioService.getTelegramId(telegramId);
            if (usuario == null) {
                // If the user is not registered, handle it accordingly (e.g., send a message)
                SendMessage messageToTelegram = new SendMessage();
                messageToTelegram.setChatId(String.valueOf(chatId));
                messageToTelegram.setText("You are not registered to use this bot.");
                try {
                    execute(messageToTelegram);
                } catch (TelegramApiException e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
                return; // Exit the method, as the user is not registered
            }

			// Usuario registrado: enviar mensaje de bienvenida personalizado
            String welcomeMessage = String.format("Bienvenido %s, tienes acceso al Bot!", usuario.getNombre());
            SendMessage message = new SendMessage(String.valueOf(chatId), welcomeMessage);

			try {
                execute(message);
            } catch (TelegramApiException e) {
                logger.error("Error sending message: " + e.getMessage(), e);
            }

			String messageTextFromTelegram = update.getMessage().getText();
			if (messageTextFromTelegram.equals(BotCommands.START_COMMAND.getCommand())
					|| messageTextFromTelegram.equals(BotLabels.SHOW_MAIN_SCREEN.getLabel())) {

				SendMessage messageToTelegram = new SendMessage();
				messageToTelegram.setChatId(String.valueOf(chatId));
				messageToTelegram.setText(BotMessages.HELLO_MYTODO_BOT.getMessage());

				ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
				List<KeyboardRow> keyboard = new ArrayList<>();

				// first row
				KeyboardRow row = new KeyboardRow();
				row.add(BotLabels.LIST_ALL_ITEMS.getLabel());
				row.add(BotLabels.ADD_NEW_ITEM.getLabel());
				// Add the first row to the keyboard
				keyboard.add(row);

				// second row
				row = new KeyboardRow();
				row.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
				row.add(BotLabels.HIDE_MAIN_SCREEN.getLabel());
				keyboard.add(row);

				// Set the keyboard
				keyboardMarkup.setKeyboard(keyboard);

				// Add the keyboard markup
				messageToTelegram.setReplyMarkup(keyboardMarkup);

				try {
					execute(messageToTelegram);
				} catch (TelegramApiException e) {
					logger.error(e.getLocalizedMessage(), e);
				}

			}
		
		}

    }


    
}
