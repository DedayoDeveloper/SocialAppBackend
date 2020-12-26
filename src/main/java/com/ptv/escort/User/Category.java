package com.ptv.escort.User;

import com.ptv.escort.Utils.BaseEnum;

public enum Category implements BaseEnum {


    Relationship("Relationship"),
    FriendWithBenefit("Friends With Benefits"),
    SexHookUp("Sex Hook Up"),
    SugarMummy("Sugar Mummy"),
    SugarDaddy("Sugar Daddy"),
    Strippers("Strippers"),
    PartyStarters("Party Starters");

    private String description;

    Category(String description) {
        this.description = description;
    }


    @Override
    public String getDescription() { return description; }

    @Override
    public String getName() { return name(); }
}
