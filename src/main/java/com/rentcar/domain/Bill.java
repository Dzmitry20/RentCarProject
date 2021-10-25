package com.rentcar.domain;

import com.rentcar.domain.status.BillStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

public class Bill {

    private String id;

    private Long numberContract;

    private LocalDateTime paymentDate;

    private Double totalPrice;

    private Order order;

    private BillStatus billStatus = BillStatus.AWAITING_PAYMENT;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
