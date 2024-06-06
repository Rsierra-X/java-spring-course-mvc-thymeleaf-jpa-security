package org.rsierra.service.db;

import org.rsierra.models.Vacancy;
import org.rsierra.repository.VacancyRepository;
import org.rsierra.service.IVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class VacancyServiceJpa implements IVacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    public List<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }

    public Vacancy getVacancyById(int id) {
        return vacancyRepository.findById(id).orElse(null);
    }

    public void saveVacancy(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
    }

    public List<Vacancy> searchFeaturedVacancies() {
        return vacancyRepository.findByFeaturedAndStatusOrderByIdDesc(1,"Aprobada");
    }



    public void deleteVacancy(Integer idVacancy) {
        vacancyRepository.deleteById(idVacancy);
    }

    public List<Vacancy> searchByExample(Example<Vacancy> example) {
        return  vacancyRepository.findAll(example);
    }

    @Override
    public Page<Vacancy> buscarTodas(Pageable page) {
        return vacancyRepository.findAll(page);
    }
}
