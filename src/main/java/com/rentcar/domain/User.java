package com.rentcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rentcar.domain.status.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;



@Data
@NoArgsConstructor
@Setter
@Entity
@Table(name = "users")
@EqualsAndHashCode(exclude = {
        "orders", "roles"
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Long phone;

    @Column(name = "passport_series" )
    private String passportSeries;

    @Column(name = "passport_number" )
    private Integer passportNumber;

    @Column
    private String email;

    @Column(name = "driver_license_number" )
    private Integer driverLicenseNumber;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Order> orders = new HashSet<>();

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JsonIgnoreProperties("users")
    private Set<Role> roles = Collections.emptySet();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


}
