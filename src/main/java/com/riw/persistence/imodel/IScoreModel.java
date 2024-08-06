package com.riw.persistence.imodel;

import com.riw.entities.Score;
import com.riw.persistence.crud.CreateModel;
import com.riw.persistence.crud.UpdateModel;

public interface IScoreModel extends CreateModel<Score>, UpdateModel<Score> {
}
