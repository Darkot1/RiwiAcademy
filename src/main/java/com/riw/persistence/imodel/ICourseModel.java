package com.riw.persistence.imodel;

import com.riw.entities.Course;
import com.riw.persistence.crud.CreateModel;
import com.riw.persistence.crud.DeleteModel;
import com.riw.persistence.crud.ReadModel;
import com.riw.persistence.crud.UpdateModel;

public interface ICourseModel extends CreateModel<Course>, DeleteModel<Integer>, ReadModel<String,Course>, UpdateModel<Course> {
}
