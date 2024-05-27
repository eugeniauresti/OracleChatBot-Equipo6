// Hola

package com.example.Microservice_OAD.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.radians;
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
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.example.Microservice_OAD.model.Desarrollador;
import com.example.Microservice_OAD.model.Tarea;
import com.example.Microservice_OAD.model.Usuario;
import com.example.Microservice_OAD.service.DesarrolladorService;
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
	private DesarrolladorService desarrolladorService;
	private String botName;
	private String botToken;

	String usuarioSeleccionado = "";
	String nombreUsuarioActual = "";

	private Map<Long, Boolean> welcomeMessageSentMap = new HashMap<>();

	@Autowired
    public BotController(String botToken, String botName, TareaService tareaService, UsuarioService usuarioService, DesarrolladorService desarrolladorService) {
        logger.info("Bot Token: " + botToken);
        logger.info("Bot Name:" + botName);
        this.tareaService = tareaService;
        this.botName = botName;
        this.botToken = botToken;
        this.usuarioService = usuarioService;
		this.desarrolladorService = desarrolladorService;
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
		logger.info("-- update recived --");
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            long telegramId = update.getMessage().getFrom().getId();

            // Check if the telegramId is registered
            Usuario usuario = usuarioService.getTelegramId(telegramId);
            if (usuario == null) {
				// If the user is not registered, handle it accordingly
				SendMessage messageToTelegram = new SendMessage();
				messageToTelegram.setChatId(String.valueOf(chatId));
				messageToTelegram.setText("You are not registered to use this bot.");
				try {
					execute(messageToTelegram);
				} catch (TelegramApiException e) {
					logger.error(e.getLocalizedMessage(), e);
				}
				return;
			}
			else {
				// If the user is registered and the welcome message hasn't been sent yet
				if (!welcomeMessageSentMap.getOrDefault(telegramId, false)) {
					String welcomeMessage = String.format("Bienvenido %s, tienes acceso al bot como: %s", usuario.getNombre(), usuario.getRol());
					SendMessage welcomeMessageToSend = new SendMessage(String.valueOf(chatId), welcomeMessage);
					try {
						execute(welcomeMessageToSend);
						// Set the flag to true after sending the welcome message
						welcomeMessageSentMap.put(telegramId, true);
					} catch (TelegramApiException e) {
						logger.error("Error sending message: " + e.getMessage(), e);
					}
				}
			}
            

            // Verificar el rol del usuario y ejecutar acciones correspondientes
            String role = usuario.getRol();
			logger.info("Role: "+role);
            if (role != null) {
                switch (role) {
                    case "Manager":
                        // Funciones para el rol de Manager
                        handleManagerActions(chatId, update);
                        break;
                    case "Desarrollador":
                        // Funciones para el rol de Desarrollador
                        handleDeveloperActions(chatId, update);
                        break;
                    default:
                        // Si el rol no está definido o no es reconocido, no hacer nada adicional
                        break;
                }
            }
        }
    }

    // Funciones específicas para el rol de Manager
    private void handleManagerActions(long chatId, Update update) {
        SendMessage messageToTelegram = new SendMessage();
        messageToTelegram.setChatId(String.valueOf(chatId));

		long telegramId = update.getMessage().getFrom().getId();
		String messageTextFromTelegram = update.getMessage().getText();

		Usuario usuarioActual = usuarioService.getTelegramId(telegramId);
		String nombreUsuarioActual = usuarioActual.getNombre();
		logger.info("ManID: "+telegramId);


		if(messageTextFromTelegram.equals(BotCommands.START_COMMAND.getCommand())
				|| messageTextFromTelegram.equals(BotLabels.SHOW_MAIN_SCREEN.getLabel())) {
					
				messageToTelegram.setChatId(String.valueOf(chatId));
				messageToTelegram.setText(BotMessages.HELLO_MYTODO_BOT.getMessage());

				ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
				List<KeyboardRow> keyboard = new ArrayList<>();

				// first row
				KeyboardRow row = new KeyboardRow();
				row.add(BotLabels.LIST_USERS.getLabel());
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
		else if (messageTextFromTelegram.equals(BotCommands.HIDE_COMMAND.getCommand())
					|| messageTextFromTelegram.equals(BotLabels.HIDE_MAIN_SCREEN.getLabel())) {

				BotHelper.sendMessageToTelegram(chatId, BotMessages.BYE.getMessage(), this);

		}
		else if (messageTextFromTelegram.indexOf(BotLabels.DONE.getLabel()) != -1) {

			String done = messageTextFromTelegram.substring(0, messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));

			int id = Integer.valueOf(done);

			try {
				Tarea item = getTareaById(id).getBody();
				item.setIsActive(false);
				item.setFechaFinTarea(OffsetDateTime.now());
				updateTarea(item, id);
				BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DONE_MAN.getMessage(), this);
			}
			catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
		else if (messageTextFromTelegram.indexOf(BotLabels.UNDO.getLabel()) != -1){

			String undo = messageTextFromTelegram.substring(0, messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));

			int id = Integer.valueOf(undo);

			try {

				Tarea item = getTareaById(id).getBody();
				item.setIsActive(true);
				item.setFechaFinTarea(null);
				updateTarea(item, id);
				BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_UNDONE_MAN.getMessage(), this);
			}
			catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
		else if (messageTextFromTelegram.indexOf(BotLabels.DELETE.getLabel()) != -1) {

			String delete = messageTextFromTelegram.substring(0, messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));

			int id = Integer.valueOf(delete);

			try {
				deleteTarea(id).getBody();
				BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DELETED_MAN.getMessage(), this);
			}
			catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}


		}
		else if (messageTextFromTelegram.equals(BotCommands.USER_LIST.getCommand())
						|| messageTextFromTelegram.equals(BotLabels.LIST_USERS.getLabel())) {

				List<Usuario> allUsers = getAllUsers();
				ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
				List<KeyboardRow> keyboard = new ArrayList<>();

				// command back to main screen
				KeyboardRow mainScreenRowTop = new KeyboardRow();
				mainScreenRowTop.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
				keyboard.add(mainScreenRowTop);

				KeyboardRow userListTitleRow = new KeyboardRow();
				userListTitleRow.add(BotLabels.LIST_USERS.getLabel());
				keyboard.add(userListTitleRow);

				for (Usuario usuario : allUsers) {

					if (usuario.getTelegramId() != telegramId) {
					KeyboardRow currentRow = new KeyboardRow();
					currentRow.add(usuario.getNombre());
					keyboard.add(currentRow);
					}
				}

				keyboardMarkup.setKeyboard(keyboard);

				messageToTelegram.setChatId(String.valueOf(chatId));
				messageToTelegram.setText(BotLabels.LIST_USERS.getLabel());
				messageToTelegram.setReplyMarkup(keyboardMarkup);

				try {
					execute(messageToTelegram);
				} catch (TelegramApiException e) {
					logger.error(e.getLocalizedMessage(), e);
				}

		}
		else if (isUserName(messageTextFromTelegram)) {
				usuarioSeleccionado = messageTextFromTelegram;
				
				messageToTelegram.setText("Usuario Seleccionado: " + messageTextFromTelegram);
	
				List<Tarea> tareas = tareaService.getTareasPorUsuario(messageTextFromTelegram);

				List<Tarea> tareaActive = tareas.stream().filter(Tarea::getIsActive).collect(Collectors.toList());
				List<Tarea> tareaNotActive = tareas.stream().filter(tarea -> !tarea.getIsActive()).collect(Collectors.toList());
	
				ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
				List<KeyboardRow> keyboard = new ArrayList<>();
	
				KeyboardRow row = new KeyboardRow();
				row.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
				keyboard.add(row);
	
				row = new KeyboardRow();
				row.add(BotLabels.LIST_USERS.getLabel());
				keyboard.add(row);

				for (Tarea tarea : tareaActive) {
					KeyboardRow currentRow = new KeyboardRow();
					currentRow.add(tarea.getTitulo());
					currentRow.add(tarea.getIdTarea() + BotLabels.DASH.getLabel() + BotLabels.DONE.getLabel());
					keyboard.add(currentRow);
				}
				
				for (Tarea tarea : tareaNotActive) {
					KeyboardRow currentRow = new KeyboardRow();
					currentRow.add(tarea.getTitulo());
					currentRow.add(tarea.getIdTarea() + BotLabels.DASH.getLabel() + BotLabels.UNDO.getLabel());
					currentRow.add(tarea.getIdTarea() + BotLabels.DASH.getLabel() +BotLabels.DELETE.getLabel());
					keyboard.add(currentRow);
				}

				row = new KeyboardRow();
				row.add(BotLabels.ADD_NEW_ITEM.getLabel());
				keyboard.add(row);
	
				keyboardMarkup.setKeyboard(keyboard);
				messageToTelegram.setReplyMarkup(keyboardMarkup);

				try {
					execute(messageToTelegram);
				} catch (TelegramApiException e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
			else if (isTarea(messageTextFromTelegram)){
				String titulo = messageTextFromTelegram;
				Tarea tarea = tareaService.getTitulo(titulo);
				String descripcion = tarea.getDescripcion();

				messageToTelegram.setText(descripcion);

				try {
					execute(messageToTelegram);
				} catch (TelegramApiException e) {
					logger.error(e.getLocalizedMessage(), e);
				}
				
			}
			else if (messageTextFromTelegram.equals(BotCommands.ADD_ITEM.getCommand())
					|| messageTextFromTelegram.equals(BotLabels.ADD_NEW_ITEM.getLabel())) {
	
						try {
							messageToTelegram.setChatId(String.valueOf(chatId));
							messageToTelegram.setText(BotMessages.TYPE_NEW_TODO_ITEM.getMessage());

							// hide keyboard
							ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove(true);
							messageToTelegram.setReplyMarkup(keyboardMarkup);
		
							// send message
							execute(messageToTelegram);	
		
						} catch (Exception e) {
							logger.error(e.getLocalizedMessage(), e);
						}
				
			}
			else {
					String titulo, descripcion, prioridad;

					// Busca al usuario por su nombre
					Usuario usuario = usuarioService.getNombre(usuarioSeleccionado);
					logger.info(usuarioSeleccionado);

					// Obtiene el ID del usuario
					int idUsuario = usuario.getIdUsuario();

					// Usa el ID del usuario para obtener el desarrollador
					Desarrollador desarrollador = desarrolladorService.getDesarrolladorPorIdUsuario(idUsuario);

					try {

						String string = messageTextFromTelegram;
						String[] tarea_texto = string.split("-");
						titulo = tarea_texto[0];
						descripcion = tarea_texto[1];
						prioridad = tarea_texto[2];
						
					}
					catch (ArrayIndexOutOfBoundsException e) {
						messageToTelegram.setText("Faltan parametros, no se utilizo el formato especificado");
						
						try{
							execute(messageToTelegram);
						} catch (TelegramApiException ex) {
							logger.error(ex.getLocalizedMessage(), ex);
						}
						return;
					
					} 

					try{
						
						Tarea nuevaTarea = new  Tarea();

						nuevaTarea.setTitulo(titulo);
						nuevaTarea.setDescripcion(descripcion);
						nuevaTarea.setPrioridad(prioridad);
						nuevaTarea.setDesarrollador(desarrollador);
						nuevaTarea.setProyecto(null);
						nuevaTarea.setSprint(null);
						nuevaTarea.setFechaInicio(OffsetDateTime.now());
						nuevaTarea.setFechaFinTarea(null);
						nuevaTarea.setCreadoPor(nombreUsuarioActual);
						nuevaTarea.setResponsable(usuarioSeleccionado);
						nuevaTarea.setEstatusTarea(0);
						nuevaTarea.setIsActive(true);
						ResponseEntity entity = addTarea(nuevaTarea);

						messageToTelegram.setChatId(String.valueOf(chatId));
						messageToTelegram.setText(BotMessages.NEW_ITEM_ADDED_MAN.getMessage());	

						execute(messageToTelegram);	
					
				}
				catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}

    }

    // Funciones específicas para el rol de Desarrollador
    private void handleDeveloperActions(long chatId, Update update) {
        SendMessage messageToTelegram = new SendMessage();
        messageToTelegram.setChatId(String.valueOf(chatId));

		long telegramId = update.getMessage().getFrom().getId();
		String messageTextFromTelegram = update.getMessage().getText();
		logger.info("DevID: "+telegramId);
		Usuario usuarioActual = usuarioService.getTelegramId(telegramId);
		nombreUsuarioActual = usuarioActual.getNombre();

		if (messageTextFromTelegram.equals(BotCommands.START_COMMAND.getCommand())
				|| messageTextFromTelegram.equals(BotLabels.SHOW_MAIN_SCREEN.getLabel())) {

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
		else if (messageTextFromTelegram.equals(BotCommands.HIDE_COMMAND.getCommand())
					|| messageTextFromTelegram.equals(BotLabels.HIDE_MAIN_SCREEN.getLabel())) {

				BotHelper.sendMessageToTelegram(chatId, BotMessages.BYE.getMessage(), this);

		}
		else if (messageTextFromTelegram.indexOf(BotLabels.DONE.getLabel()) != -1) {

			String done = messageTextFromTelegram.substring(0, messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));

			int id = Integer.valueOf(done);

			try {
				Tarea item = getTareaById(id).getBody();
				item.setIsActive(false);
				item.setFechaFinTarea(OffsetDateTime.now());
				updateTarea(item, id);
				BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DONE.getMessage(), this);
			}
			catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
		else if (messageTextFromTelegram.indexOf(BotLabels.UNDO.getLabel()) != -1){

			String undo = messageTextFromTelegram.substring(0, messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));

			int id = Integer.valueOf(undo);

			try {

				Tarea item = getTareaById(id).getBody();
				item.setIsActive(true);
				item.setFechaFinTarea(null);
				updateTarea(item, id);
				BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_UNDONE.getMessage(), this);
			}
			catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
		else if (messageTextFromTelegram.indexOf(BotLabels.DELETE.getLabel()) != -1) {

			String delete = messageTextFromTelegram.substring(0, messageTextFromTelegram.indexOf(BotLabels.DASH.getLabel()));

			int id = Integer.valueOf(delete);

			try {
				deleteTarea(id).getBody();
				BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DELETED.getMessage(), this);
			}
			catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
		else if (messageTextFromTelegram.equals(BotCommands.TODO_LIST.getCommand())
					|| messageTextFromTelegram.equals(BotLabels.LIST_ALL_ITEMS.getLabel())
					|| messageTextFromTelegram.equals(BotLabels.MY_TODO_LIST.getLabel())) {

				List<Tarea> tareas = tareaService.getTareasPorUsuario(nombreUsuarioActual);
				logger.info("Accion: DevListItems  Usuario: "+nombreUsuarioActual);
				List<Tarea> tareaActive = tareas.stream().filter(Tarea::getIsActive).collect(Collectors.toList());
				logger.info("Accion: DevListItems  Paso: 1 ");
				List<Tarea> tareaNotActive = tareas.stream().filter(tarea -> !tarea.getIsActive()).collect(Collectors.toList());
				logger.info("Accion: DevListItems  Paso: 2 ");
				ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
				List<KeyboardRow> keyboard = new ArrayList<>();
	
				KeyboardRow row = new KeyboardRow();
				row.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
				keyboard.add(row);

				logger.info("Accion: DevListItems  Paso: 3 ");
				for (Tarea tarea : tareaActive) {
					KeyboardRow currentRow = new KeyboardRow();
					currentRow.add(tarea.getTitulo());
					currentRow.add(tarea.getIdTarea() + BotLabels.DASH.getLabel() + BotLabels.DONE.getLabel());
					keyboard.add(currentRow);
				}
				logger.info("Accion: DevListItems  Paso: 4 ");
				for (Tarea tarea : tareaNotActive) {
					KeyboardRow currentRow = new KeyboardRow();
					currentRow.add(tarea.getTitulo());
					currentRow.add(tarea.getIdTarea() + BotLabels.DASH.getLabel() + BotLabels.UNDO.getLabel());
					currentRow.add(tarea.getIdTarea() + BotLabels.DASH.getLabel() +BotLabels.DELETE.getLabel());
					keyboard.add(currentRow);
				}

				row = new KeyboardRow();
				row.add(BotLabels.ADD_NEW_ITEM.getLabel());
				keyboard.add(row);
				logger.info("Accion: DevListItems  Paso: 5 ");
				keyboardMarkup.setKeyboard(keyboard);
				messageToTelegram.setReplyMarkup(keyboardMarkup);
				logger.info("Accion: DevListItems  Paso: 6 ");


				messageToTelegram = new SendMessage();
				messageToTelegram.setChatId(String.valueOf(chatId));
				messageToTelegram.setText(BotLabels.MY_TODO_LIST.getLabel());
				messageToTelegram.setReplyMarkup(keyboardMarkup);

				try {
					execute(messageToTelegram);
				} catch (TelegramApiException e) {
					logger.error(e.getLocalizedMessage(), e);
				}

			}
			else if (isTarea(messageTextFromTelegram)){
				String titulo = messageTextFromTelegram;
				Tarea tarea = tareaService.getTitulo(titulo);
				String descripcion = tarea.getDescripcion();

				messageToTelegram.setText(descripcion);

				try {
					execute(messageToTelegram);
				} catch (TelegramApiException e) {
					logger.error(e.getLocalizedMessage(), e);
				}
				
			}
			else if (messageTextFromTelegram.equals(BotCommands.ADD_ITEM.getCommand())
			|| messageTextFromTelegram.equals(BotLabels.ADD_NEW_ITEM.getLabel())) {
				logger.info(" Add Msg");
				try {
					messageToTelegram.setChatId(String.valueOf(chatId));
					messageToTelegram.setText(BotMessages.TYPE_NEW_TODO_ITEM.getMessage());

					// hide keyboard
					ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove(true);
					messageToTelegram.setReplyMarkup(keyboardMarkup);

					// send message
					execute(messageToTelegram);	

				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
			else {

				String titulo, descripcion, prioridad;

				// Busca al usuario por su nombre
				Usuario usuario = usuarioService.getNombre(nombreUsuarioActual);
				logger.info("Add Task "+nombreUsuarioActual);

				// Obtiene el ID del usuario
				int idUsuario = usuario.getIdUsuario();

				// Usa el ID del usuario para obtener el desarrollador
				Desarrollador desarrollador = desarrolladorService.getDesarrolladorPorIdUsuario(idUsuario);
				
				try {
						
					String string = messageTextFromTelegram;
					String[] tarea_texto = string.split("-");
					titulo = tarea_texto[0];
					descripcion = tarea_texto[1];
					prioridad = tarea_texto[2];
					
				}
				catch (ArrayIndexOutOfBoundsException e) {
					messageToTelegram.setText("Faltan parametros, no se utilizo el formato especificado");
					
					try{
						execute(messageToTelegram);
					} catch (TelegramApiException ex) {
						logger.error(ex.getLocalizedMessage(), ex);
					}
					return;
				
				} 


				try{
					Tarea nuevaTarea = new  Tarea();

					nuevaTarea.setTitulo(titulo);
					nuevaTarea.setDescripcion(descripcion);
					nuevaTarea.setPrioridad(prioridad);
					nuevaTarea.setDesarrollador(desarrollador);
					nuevaTarea.setProyecto(null);
					nuevaTarea.setSprint(null);
					nuevaTarea.setFechaInicio(OffsetDateTime.now());
					nuevaTarea.setFechaFinTarea(null);
					nuevaTarea.setCreadoPor(nombreUsuarioActual);
					nuevaTarea.setResponsable(nombreUsuarioActual);
					nuevaTarea.setEstatusTarea(0);
					nuevaTarea.setIsActive(true);
					ResponseEntity entity = addTarea(nuevaTarea);

					messageToTelegram.setChatId(String.valueOf(chatId));
					messageToTelegram.setText(BotMessages.NEW_ITEM_ADDED.getMessage());	

					execute(messageToTelegram);	
				
			}
			catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}

    }

	public List<Usuario> getAllUsers() {
		return usuarioService.getUsuarios();
	}

	private boolean isUserName(String messageText) {
		List<Usuario> allUsers = getAllUsers();
		for (Usuario usuario : allUsers) {
			if (usuario.getNombre().equals(messageText)) {
				return true;
			}
		}
		return false;
	}

	public List<Tarea> getAllTareas() {
		return tareaService.findAll();
	}

	private boolean isTarea(String messageText) {
		List<Tarea> allTareas = getAllTareas();
		for (Tarea tarea : allTareas) {
			if (tarea.getTitulo().equals(messageText)) {
				return true;
			}
		}
		return false;
	}
	
	public  ResponseEntity addTarea(@RequestBody Tarea tarea) throws Exception {
		Tarea td = tareaService.addTarea(tarea);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("location", "" + td.getIdTarea());
		responseHeaders.set("Access-Control-Expose-Headers", "location");

		return ResponseEntity.ok().headers(responseHeaders).build();	
	}

	public ResponseEntity<Tarea> getTareaById(@PathVariable int id){
		try {
			ResponseEntity<Tarea> resposnsEntity = tareaService.getTareaById(id);
			return new ResponseEntity<Tarea>(resposnsEntity.getBody(), HttpStatus.OK);
		}
		catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity updateTarea(@RequestBody Tarea tarea, @PathVariable int id) {
		try {
			Tarea tarea1 = tareaService.updateTarea(id, tarea);
			System.out.println(tarea1.toString());
			return new ResponseEntity<>(tarea1, HttpStatus.OK);
		}
		catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Boolean> deleteTarea(@PathVariable("id") int id){

		Boolean flag = false;
		try {
			flag = tareaService.deleteTarea(id);
			return new ResponseEntity<>(flag, HttpStatus.OK);
		}
		catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
		}

	}

}