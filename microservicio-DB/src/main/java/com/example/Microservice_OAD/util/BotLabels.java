package com.example.Microservice_OAD.util;

public enum BotLabels {
	
	SHOW_MAIN_SCREEN("Mostrar Pantalla"), 
	HIDE_MAIN_SCREEN("Ocultar Pantalla"),
	LIST_ALL_ITEMS("Lista Tareas"), 
	LIST_USERS("Lista Usuarios"),
	ADD_NEW_ITEM("Agregar Tarea"),
	DONE("COMPLETAR"),
	UNDO("REACTIVAR"),
	DELETE("ELIMINAR"),
	MY_TODO_LIST("Lista Tareas"),
	DASH("-");

	private String label;

	BotLabels(String enumLabel) {
		this.label = enumLabel;
	}

	public String getLabel() {
		return label;
	}

}