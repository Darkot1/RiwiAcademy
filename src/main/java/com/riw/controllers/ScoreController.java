package com.riw.controllers;

import com.riw.entities.Course;
import com.riw.entities.Score;
import com.riw.models.ScoreModelImplement;
import com.riw.persistence.imodel.IScoreModel;

public class ScoreController {

    IScoreModel scoreModel;

    public ScoreController(){

        this.scoreModel = new ScoreModelImplement();
    }


    public Score createScore(Score score){
        return scoreModel.create(score);
    }

    public Score updateScore(int idStudent, Double scoreNote){
        //Creamos una entidad
        Score score = new Score();
        //id del curso para actualizar
        score.setIdStudent(idStudent);

        //Se le asignan los nuevos valores
        score.setScore(scoreNote);
        return scoreModel.update(score);
    }
}
