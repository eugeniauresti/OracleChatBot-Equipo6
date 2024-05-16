package com.example.Microservice_OAD.util;

public enum BotCommands {

	START_COMMAND("/start"), 
	HIDE_COMMAND("/hide"),
	USER_LIST("/listaUsuarios"), 
	TODO_LIST("/listaTareas"),
	ADD_ITEM("/agregarTarea");

	private String command;

	BotCommands(String enumCommand) {
		this.command = enumCommand;
	}

	public String getCommand() {
		return command;
	}
}
