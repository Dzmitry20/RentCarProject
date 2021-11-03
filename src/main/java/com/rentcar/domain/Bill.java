package com.rentcar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rentcar.domain.status.BillStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bills")
@EqualsAndHashCode(exclude = {
        "order"
})
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_bill")
    private Long numberBill;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "bill_status")
    @Enumerated(EnumType.STRING)
    private BillStatus billStatus = BillStatus.AWAITING_PAYMENT;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
