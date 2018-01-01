package com.transport.taxi.bus.taxis.domain.base;

/**
 * Created by GHome on 20.12.2017.
 */

public class TaxisDomain {
    private String id;
    private String name;
    private String direct_direction;
    private String reverse_direction;

    public String getDirect_direction() {
        return direct_direction;
    }

    public void setDirect_direction(String direct_direction) {
        this.direct_direction = direct_direction;
    }

    public String getReverse_direction() {
        return reverse_direction;
    }

    public void setReverse_direction(String reverse_direction) {
        this.reverse_direction = reverse_direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
