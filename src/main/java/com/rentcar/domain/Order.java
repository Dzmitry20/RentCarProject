package com.rentcar.domain;

import com.rentcar.domain.status.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "received_date")
    private Date receivedDate;

    @Column(name ="return_date")
    private Date returnData;

    @Column(name ="order_status")
    private OrderStatus orderStatus = OrderStatus.NOT_CONFIRMED;

    @ManyToOne
    private User user;

    @OneToMany()
    private Set<Car> cars = new HashSet<>();


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
