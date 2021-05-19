package com.company;

import java.time.LocalDate;

public class RoomPlant {
    private String name;
    private String notes;
    private LocalDate datePlanted;
    private LocalDate dateLastWatering;
    private int wateringFrequency;

    public RoomPlant(String name, String notes, LocalDate datePlanted, LocalDate dateLastWatering, int wateringFrequency) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.datePlanted = datePlanted;
        this.dateLastWatering = dateLastWatering;
        if(this.dateLastWatering.isBefore(this.datePlanted))
            throw new PlantException("Last Watering Date ("+this.dateLastWatering+") cannot be less then the date ("+this.datePlanted+") when "+this.name+" was planted");

        this.wateringFrequency = wateringFrequency;
        if(this.wateringFrequency <= 0)
            throw new PlantException("Watering Frequency ("+this.wateringFrequency+") cannot be less then or equal to 0");
    }

    public RoomPlant(String name, LocalDate datePlanted, int wateringFrequency) throws PlantException {
        this.name = name;
        this.notes = "";
        this.datePlanted = datePlanted;
        this.dateLastWatering = LocalDate.now();
        if(this.dateLastWatering.isBefore(this.datePlanted))
            throw new PlantException("Last Watering Date ("+this.dateLastWatering+") cannot be less then the date ("+this.datePlanted+") when "+this.name+" was planted");

        this.wateringFrequency = wateringFrequency;
        if(this.wateringFrequency <= 0)
            throw new PlantException("Watering Frequency ("+this.wateringFrequency+") cannot be less then or equal to 0");
    }
    public RoomPlant(String name) throws PlantException {
        this(name,LocalDate.now(),7);
    }

    public void SetName(String name)
    {
        this.name = name;
    }
    public void SetNotes(String notes)
    {
        this.notes = notes;
    }
    public void SetDatePlanted(LocalDate datePlanted)
    {
         this.datePlanted=datePlanted;
    }
    public void SetDateWatering(LocalDate dateLastWatering) throws PlantException {
        this.dateLastWatering = dateLastWatering;
        if (this.dateLastWatering.isBefore(this.datePlanted))
            throw new PlantException("Last Watering Date (" + this.dateLastWatering + ") cannot be less then the date (" + this.datePlanted + ") when " + this.name + " was planted");
    }
    public void SetWateringFrequency(int wateringFrequency) throws PlantException {
        this.wateringFrequency = wateringFrequency;
        if (this.wateringFrequency <= 0)
            throw new PlantException("Watering Frequency (" + this.wateringFrequency + ") cannot be less then or equal to 0");
    }

    public String GetName()
    {
        return name;
    }
    public String GetNotes()
    {
        return notes;
    }
    public LocalDate GetDatePlanted()
    {
        return datePlanted;
    }
    public LocalDate GetDateWatering()
    {
        return dateLastWatering;
    }
    public int GetWateringFrequency()
    {
        return wateringFrequency;
    }
    public String GetWateringInfo()
    {
        return "Plant: " + GetName() + " | Last date of Watering: " + GetDateWatering() + " | Next date of Watering: " + GetDateWatering().plusDays(GetWateringFrequency());
    }
}
