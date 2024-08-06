package com.riw.persistence.imodel;

import com.riw.entities.Registration;
import com.riw.persistence.crud.CreateModel;
import com.riw.persistence.crud.ReadAllModel;
import com.riw.persistence.crud.UpdateModel;

public interface IRegistrationModel extends CreateModel<Registration>, ReadAllModel<Registration>, UpdateModel<Registration> {
}
