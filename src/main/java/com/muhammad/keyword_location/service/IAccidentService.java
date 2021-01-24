package com.muhammad.keyword_location.service;

import com.muhammad.keyword_location.domain.Accident;
import java.util.List;

public interface IAccidentService {

    List<Accident> retrieveAllAccident();
    Accident addNew(Accident accident);
    Accident updateAccident(long id, Accident accident);
    boolean removeAccident(long id);
}
