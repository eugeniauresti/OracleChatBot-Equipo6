package com.example.Microservice_OAD.util;

public enum BotMessages {
	
	HELLO_MYTODO_BOT(
	"Hola, soy el Gestor de Tareas!"),
	BOT_REGISTERED_STARTED("Bot registrado e inicializado!"),
	ITEM_DONE_MAN("Tarea Completada! Usa /listaUsuarios para ver la lista de usuarios o /start para regresar a la pantalla principal."),
	ITEM_UNDONE_MAN("Tarea Reactivada! Usa /listaUsuarios para ver la lista de usuarios o /start para regresar a la pantalla principal."),
	ITEM_DELETED_MAN("Tarea Eliminada! Usa /listaUsuarios para ver la lista de usuarios o /start para regresar a la pantalla principal."),
	ITEM_DONE("Tarea Completada! Usa /listaTareas para ver la lista de tareas o /start para regresar a la pantalla principal."), 
	ITEM_UNDONE("Tarea Reactivada! Usa /listaTareas para ver la lista de tareas o /start para regresar a la pantalla principal."), 
	ITEM_DELETED("Tarea Eliminada! Usa /listaTareas para ver la lista de tareas o /start para regresar a la pantalla principal."),
	TYPE_NEW_TODO_ITEM("Escribe la nueva tarea utilizando el formato (Titulo-Descripcion-Prioridad) y presiona el boton de enviar (Flecha Azul)"),
	NEW_ITEM_ADDED_MAN("Tarea agregada! Usa /listaUsuarios para ver la lista de usuarios o /start para regresar a la pantalla principal."),
	NEW_ITEM_ADDED("Tarea agregada! Usa /listaTareas para ver la lista de tareas o /start para regresar a la pantalla principal."),
	BYE("Adios! Usa /start para usar el bot!");

	private String message;

	BotMessages(String enumMessage) {
		this.message = enumMessage;
	}

	public String getMessage() {
		return message;
	}

}