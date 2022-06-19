package com.manager.information.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "Activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "{validation.dob.past}")
    @NotNull(message = "{validation.dob.NotNull}")
    private LocalDate date;
    @Column(name = "nameOfActivity")
    @Enumerated(EnumType.STRING)
    private ActivityType nameOfActivity;
    @Column(name = "amount")
    private double amount;
    @Column(name = "comment")
    private String comment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User userRegistration;

    public Activity() {

    }

    public Activity(LocalDate date, ActivityType nameOfActivity, double amount, String comment, User userRegistration) {
        this.date = date;
        this.nameOfActivity = nameOfActivity;
        this.amount = amount;
        this.comment = comment;
        this.userRegistration = userRegistration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ActivityType getNameOfActivity() {
        return nameOfActivity;
    }

    public void setNameOfActivity(ActivityType nameOfActivity) {
        this.nameOfActivity = nameOfActivity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUserRegistration() {
        return userRegistration;
    }

    public void setUserRegistration(User userRegistration) {
        this.userRegistration = userRegistration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", date=" + date +
                ", nameOfActivity=" + nameOfActivity +
                ", amount=" + amount +
                ", comment='" + comment + '\'' +
                ", userRegistration=" + userRegistration +
                '}';
    }
}





