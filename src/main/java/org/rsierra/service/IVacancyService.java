package org.rsierra.service;

import org.rsierra.models.Vacancy;

import java.util.List;

public interface IVacancyService {
    List<Vacancy> getVacancies();
}
