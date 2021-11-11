package com.rentcar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rentcar.domain.status.CarStatus;
import com.rentcar.domain.status.Transmission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cars")
@EqualsAndHashCode(exclude = {
        "orders"
})
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_car")
    private String nameCar;

    @Column
    private String model;

    @Column(name = "release_date")
    private Timestamp releaseData;

    @Column
    private String color;

    @Column(name = "v_motor")
    private Double vMotor;

    @Column
    private Double power;

    @Column(name = "link_photo")
    private String linkPhoto;

    @Column
    @Enumerated(EnumType.STRING)
    private Transmission transmission = Transmission.NOT_SELECTED;

    @Column(name = "cost_per_day")
    private Double costPerDay;

    @Column(name = "car_status")
    @Enumerated(EnumType.STRING)
    private CarStatus carStatus = CarStatus.NOT_AVAILABLE;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Order> orders = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "discount_id")
    @JsonBackReference
    private Discount discount;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}