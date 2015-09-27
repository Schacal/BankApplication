package com.luxoft.psobczak.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.luxoft.psobczak.controller.BankCommander;

public class BankFeedService {

	public void loadFeed(String folder) {

		File file = new File(folder);

		try (BufferedReader buffer = new BufferedReader(new FileReader(folder))) {
			String line = buffer.readLine();
			
			while (line != null) {
				Map<String, String> mapOfDataFromOneLine = new HashMap<String, String>();

				//this part is spliting lines from file by signs ; and  = to independent tables of Strings
				String[] stringTable = line.split(";");
				for (String partOfTable : stringTable) {
					String[] splitedTable = partOfTable.split("=");
					mapOfDataFromOneLine.put(splitedTable[0], splitedTable[1]);
				}
				BankCommander.currentBank.parseFeed(mapOfDataFromOneLine);
				line = buffer.readLine();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
