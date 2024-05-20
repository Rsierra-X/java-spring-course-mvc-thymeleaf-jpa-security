package org.rsierra.service;

import org.rsierra.models.Vacancy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

//Imp = Implementation
public class VacancyServiceImp implements IVacancyService{

    private List<Vacancy> list = null;

    public VacancyServiceImp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        list = new LinkedList<Vacancy>();
        try {
            // Creamos la oferta de Trabajo 1.
            Vacancy vacante1 = new Vacancy();
            vacante1.setId(1);
            vacante1.setName("Ingeniero Civil"); // Titulo de la vacante
            vacante1.setDescription("Solicitamos Ing. Civil para diseñar puente peatonal.");
            vacante1.setDate(sdf.parse("08-02-2019"));
            vacante1.setSalary(8500.0);
            vacante1.setDestacado(1);
            vacante1.setImagen("empresa1.png");


            // Creamos la oferta de Trabajo 2.
            Vacancy vacante2 = new Vacancy();
            vacante2.setId(2);
            vacante2.setName("Contador Publico");
            vacante2.setDescription("Empresa importante solicita contador con 5 años de experiencia titulado.");
            vacante2.setDate(sdf.parse("09-02-2019"));
            vacante2.setSalary(12000.0);
            vacante1.setDestacado(0);
            vacante2.setImagen("empresa2.png");


            // Creamos la oferta de Trabajo 3.
            Vacancy vacante3 = new Vacancy();
            vacante3.setId(3);
            vacante3.setName("Ingeniero Eléctrico");
            vacante3.setDescription("Empresa internacional solicita Ingeniero mecánico para mantenimiento de la instalación eléctrica.");
            vacante3.setDate(sdf.parse("10-02-2019"));
            vacante3.setSalary(10500.0);
            vacante1.setDestacado(0);

            // Creamos la oferta de Trabajo 4.
            Vacancy vacante4 = new Vacancy();
            vacante4.setId(4);
            vacante4.setName("Diseñador Gráfico");
            vacante4.setDescription("Solicitamos Diseñador Gráfico titulado para diseñar publicidad de la empresa.");
            vacante4.setDate(sdf.parse("11-02-2019"));
            vacante4.setSalary(7500.0);
            vacante1.setDestacado(1);
            vacante4.setImagen("empresa3.png");


            list.add(vacante1);
            list.add(vacante2);
            list.add(vacante3);
            list.add(vacante4);

        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Vacancy> getVacancies() {
        return list;
    }
}
