package org.rsierra.service;

import org.rsierra.models.Vacancy;
import org.rsierra.repository.VacancyRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

//Imp = Implementation
@Service


public class VacancyServiceImp implements IVacancyService {

    private final List<Vacancy> list;
    private final VacancyRepository vacancyRepository;

    public VacancyServiceImp(VacancyRepository vacancyRepository) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        list = new LinkedList<>();
        try {
            // Creamos la oferta de Trabajo 1.
            Vacancy vacante1 = new Vacancy();
            vacante1.setId(1);
            vacante1.setName("Civil Engineer"); // Job title
            vacante1.setDescription("We are looking for a Civil Engineer to design a pedestrian bridge.");
            vacante1.setDate(sdf.parse("08-02-2019"));
            vacante1.setSalary(15000.0);
            vacante1.setFeatured(1);
            vacante1.setImage("empresa1.png");

// Create Job Offer 2.
            Vacancy vacante2 = new Vacancy();
            vacante2.setId(2);
            vacante2.setName("Certified Public Accountant");
            vacante2.setDescription("Important company is looking for a CPA with 5 years of experience.");
            vacante2.setDate(sdf.parse("09-02-2019"));
            vacante2.setSalary(12000.0);
            vacante1.setFeatured(0);
            vacante2.setImage("empresa2.png");

// Create Job Offer 3.
            Vacancy vacante3 = new Vacancy();
            vacante3.setId(3);
            vacante3.setName("Electrical Engineer");
            vacante3.setDescription("International company is looking for a mechanical engineer for electrical maintenance.");
            vacante3.setDate(sdf.parse("10-02-2019"));
            vacante3.setSalary(10500.0);
            vacante1.setFeatured(0);

// Create Job Offer 4.
            Vacancy vacante4 = new Vacancy();
            vacante4.setId(4);
            vacante4.setName("Graphic Designer");
            vacante4.setDescription("We are looking for a certified Graphic Designer to design company advertisements.");
            vacante4.setDate(sdf.parse("11-02-2019"));
            vacante4.setSalary(7500.0);
            vacante1.setFeatured(1);
            vacante4.setImage("empresa3.png");


            list.add(vacante1);
            list.add(vacante2);
            list.add(vacante3);
            list.add(vacante4);

        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        this.vacancyRepository = vacancyRepository;
    }

    public List<Vacancy> getVacancies() {
        return list;
    }

    public Vacancy getVacancyById(int id) {

        for (Vacancy vacancy : list) {
            if (vacancy.getId() == id) {
                return vacancy;
            }
        }

        return null;
    }

    public void saveVacancy(Vacancy vacancy) {
        list.add(vacancy);
    }

    public List<Vacancy> searchFeaturedVacancies() {
        return vacancyRepository.findByFeaturedAndStatusOrderByIdDesc(1,"Aprobada");
    }
}
