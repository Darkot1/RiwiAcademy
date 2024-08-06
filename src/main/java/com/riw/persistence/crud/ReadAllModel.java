package com.riw.persistence.crud;

import java.util.ArrayList;

public interface ReadAllModel <Entity>{

    ArrayList<Entity> getAll(Entity request);
}
