package org.rsierra.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
    private Integer id;
    private LocalDate date; // Fecha en que aplico el usuario para esta oferta de trabajo
    private String comments;
    private String file; // El nombre del archivo PDF, DOCX del CV.

    @OneToOne
    @JoinColumn(name = "idVacant") // foreignKey en la tabla de solicitudes
    private Vacancy vacant;

    @OneToOne
    @JoinColumn(name = "idUser") // foreignKey en la tabla de usuarios
    private User user;

    public Request() {
        this.date = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Vacancy getVacant() {
        return vacant;
    }

    public void setVacant(Vacancy vacant) {
        this.vacant = vacant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", date=" + date +
                ", comments='" + comments + '\'' +
                ", file='" + file + '\'' +
                ", vacant=" + vacant +
                ", user=" + user +
                '}';
    }
}
