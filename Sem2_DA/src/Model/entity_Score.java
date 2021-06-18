/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.*;

/**
 *
 * @author ngnha
 */
public class entity_Score {
    public int ID;
    public String Name;
    public int Score;
    public String Times;
    public String Dates;

    public entity_Score(int ID, String Name, int Score, String Times, String Dates) {
        this.ID = ID;
        this.Name = Name;
        this.Score = Score;
        this.Times = Times;
        this.Dates = Dates;
    }

    public entity_Score(String Name, int Score, String Times, String Dates) {
        this.Name = Name;
        this.Score = Score;
        this.Times = Times;
        this.Dates = Dates;
    }

    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public String getTimes() {
        return Times;
    }

    public void setTimes(String Times) {
        this.Times = Times;
    }

    public String getDates() {
        return Dates;
    }

    public void setDates(String Dates) {
        this.Dates = Dates;
    }

    

}
