package com.canopy.canopyspring.drivers;

import com.canopy.canopyspring.scores.Score;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @Column(name = "driverid")
    private Long id;

    @Column(name = "transporterid")
    private String transporterId;

    @Column(name = "driverinitials")
    private String initials;

    @Column(name = "drivername")
    private String name;

    @OneToMany(mappedBy = "driverId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Score> score = new HashSet<>();

    public Driver() {

    }

    public Driver(Long id, String transporterId, String initials, String name, Set<Score> score) {
        this.id = id;
        this.transporterId = transporterId;
        this.initials = initials;
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) && Objects.equals(transporterId, driver.transporterId) && Objects.equals(initials, driver.initials) && Objects.equals(name, driver.name) && Objects.equals(score, driver.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transporterId, initials, name, score);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(String transporterId) {
        this.transporterId = transporterId;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Score> getScore() {
        return score;
    }

    public void setScore(Set<Score> score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", transporterId='" + transporterId + '\'' +
                ", initials='" + initials + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
