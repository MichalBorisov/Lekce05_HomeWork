package com.company;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
	// write your code here
        final String INPUT_FILE_PATH = "RoomPlants.txt";
        final String OUTPUT_FILE_PATH = "RoomPlantsOutPut.txt";

        RoomPlantList roomPlantList = new RoomPlantList();
        roomPlantList.AddPlantToListFromFile(INPUT_FILE_PATH);

        try {
            roomPlantList.RemovePlantFromList(22);
        } catch (PlantException e) {
            System.out.println(e.getMessage());
        }
        try {
            roomPlantList.RemovePlantFromList(roomPlantList.GetPlantFromList(0));
        } catch (PlantException e) {
            System.out.println(e.getMessage());
        }

        RoomPlant roomPlant = null;
        try {
            roomPlant = new RoomPlant("ROBERT","Music friendly", LocalDate.of(1970,6,7), LocalDate.of(2021,6,7),20);
            roomPlantList.AddPlant(roomPlant);
        } catch (PlantException e) {
            System.out.println(e.getMessage());
        }

        if(roomPlant != null)
            System.out.println(roomPlant.GetWateringInfo());

        try {
            roomPlant = new RoomPlant("ROBERT the Second");
            roomPlantList.AddPlant(roomPlant);
        } catch (PlantException e) {
            System.out.println(e.getMessage());
        }

        if(roomPlant != null)
            System.out.println(roomPlant.GetWateringInfo());

        roomPlantList.SavePlantListToFile(OUTPUT_FILE_PATH);
    }
}
