package org.rsierra.service;

import org.rsierra.models.Vacancy;
import org.springframework.data.domain.Example;

import java.util.List;

public interface IVacancyService {
    List<Vacancy> getVacancies();
    Vacancy getVacancyById(int id);
    void saveVacancy(Vacancy vacancy);
    List<Vacancy> searchFeaturedVacancies();
    void deleteVacancy(Integer idVacancy);
    List<Vacancy> searchByExample(Example<Vacancy> example);
}
