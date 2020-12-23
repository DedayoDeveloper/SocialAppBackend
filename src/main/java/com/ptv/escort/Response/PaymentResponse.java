package com.ptv.escort.Response;

import com.ptv.escort.Admin.EscortDetails;
import com.ptv.escort.Admin.EscortPaymentDetails;

public class PaymentResponse {

    private EscortDetails escortDetails;
    private EscortPaymentDetails escortPaymentDetails;

    public PaymentResponse(EscortDetails escortDetails, EscortPaymentDetails escortPaymentDetails) {
        this.escortDetails = escortDetails;
        this.escortPaymentDetails = escortPaymentDetails;
    }

    public EscortDetails getEscortDetails() {
        return escortDetails;
    }

    public EscortPaymentDetails getEscortPaymentDetails() {
        return escortPaymentDetails;
    }
}
