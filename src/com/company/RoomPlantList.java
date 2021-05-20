package com.company;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomPlantList {
    private static final String FILE_READ_ITEM_DELIMITER = " \\| ";
    private static final String FILE_WRITE_ITEM_DELIMITER = " | ";
    ArrayList<RoomPlant> roomPlants = new ArrayList<>();

    public RoomPlant ParseTextIntoRoomPlant(String plantLine) throws PlantException {
        String[] plantInfo = plantLine.split(FILE_READ_ITEM_DELIMITER);
        LocalDate planted = null;
        LocalDate lastWatered = null;
        int wateringFrequency = -1;
        try {
            planted = LocalDate.parse(plantInfo[4]);
            lastWatered = LocalDate.parse(plantInfo[3]);
        }
        catch (DateTimeParseException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        try {
            wateringFrequency = Integer.parseInt(plantInfo[2]);
        }
        catch (NumberFormatException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return new RoomPlant(plantInfo[0],plantInfo[1],planted, lastWatered,wateringFrequency);
    }

    public void AddPlantToListFromFile(String filePath)
    {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)))) {
            while (scanner.hasNext()) {
                RoomPlant roomPlant = null;
                try {
                    roomPlant = ParseTextIntoRoomPlant(scanner.nextLine());
                } catch (PlantException e) {
                    System.out.println(e.getMessage());
                }
                if(roomPlant != null)
                    AddPlant(roomPlant);
            }
        } catch (IOException | PlantException e) {
            System.out.println(e.getMessage());
        }
    }

    public void SavePlantListToFile(String filePath)
    {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            if(roomPlants.size() > 0)
            {
                for (RoomPlant plant : roomPlants) {
                    writer.write(plant.GetName() + FILE_WRITE_ITEM_DELIMITER
                            + plant.GetNotes() + FILE_WRITE_ITEM_DELIMITER
                            + plant.GetWateringFrequency() + FILE_WRITE_ITEM_DELIMITER
                            + plant.GetDateWatering() + FILE_WRITE_ITEM_DELIMITER
                            + plant.GetDatePlanted()  + "\r\n");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void AddPlant(RoomPlant plant) throws PlantException {
        if(plant == null)
            throw new PlantException("Do not want empty cells in Plant Array");
        roomPlants.add(plant);
    }

    public void RemovePlantFromList(int index) throws PlantException {
        if(index >= roomPlants.size() || index < 0)
            throw new PlantException("Index '"+index+"' is out of Plant Array bounds");
        roomPlants.remove(index);
    }
    public void RemovePlantFromList(RoomPlant plant) throws PlantException {
        if(!roomPlants.contains(plant))
            throw new PlantException(plant.GetName() + " is not contained in the Plant list");
        roomPlants.remove(plant);
    }

    public RoomPlant GetPlantFromList(int index) throws PlantException {
        if(index >= roomPlants.size() || index < 0)
            throw new PlantException("Index '"+index+"' is out of Plant Array bounds");
        return roomPlants.get(index);
    }
}
