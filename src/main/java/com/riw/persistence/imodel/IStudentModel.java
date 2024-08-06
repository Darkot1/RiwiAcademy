package com.riw.persistence.imodel;

import com.riw.entities.Student;
import com.riw.persistence.crud.CreateModel;
import com.riw.persistence.crud.ReadAllModel;
import com.riw.persistence.crud.ReadModel;
import com.riw.persistence.crud.UpdateModel;

public interface IStudentModel extends CreateModel<Student>, ReadAllModel<Student>, UpdateModel<Student>,ReadModel<String,Student>{

}
