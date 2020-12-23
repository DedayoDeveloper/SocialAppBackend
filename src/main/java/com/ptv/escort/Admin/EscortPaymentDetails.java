package com.ptv.escort.Admin;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ptv.escort.User.User;

import javax.persistence.*;

@Entity
@Table(name = "escort_payment_details")
public class EscortPaymentDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "escort_id")
    private long escort;

    @Column(name = "user_id")
    private long user;

    @Column(name = "payment_confirmed")
    private boolean paymentConfirmed;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEscort() {
        return escort;
    }

    public void setEscort(long escort) {
        this.escort = escort;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public boolean isPaymentConfirmed() {
        return paymentConfirmed;
    }

    public void setPaymentConfirmed(boolean paymentConfirmed) {
        this.paymentConfirmed = paymentConfirmed;
    }
}
