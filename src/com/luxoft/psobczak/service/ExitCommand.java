package com.luxoft.psobczak.service;

public class ExitCommand implements Command {

	@Override
	public void execute() {
		System.out.println("Program stopped");
		System.exit(0);

	}

	@Override
	public void printCommandInfo() {
		System.out.println("Exit program");

	}

}
