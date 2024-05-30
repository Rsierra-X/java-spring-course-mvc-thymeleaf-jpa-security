package org.rsierra.service;

import org.rsierra.models.Vacancy;

import java.util.List;

public interface IVacancyService {
    List<Vacancy> getVacancies();
    Vacancy getVacancyById(int id);
    void saveVacancy(Vacancy vacancy);
    List<Vacancy> searchFeaturedVacancies();
}
