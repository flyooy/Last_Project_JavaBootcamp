package de.sp.trashNothing_backend.entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WunschSet_Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wunsch_set_id", nullable = false)
    private WunschSet wunschSet;

    @ManyToOne
    @JoinColumn(name = "produkt_id", nullable = false)
    private Produkt produkt;

    public WunschSet_Produkt() {
    }

    public WunschSet_Produkt(WunschSet wunschSet, Produkt produkt) {
        this.wunschSet = wunschSet;
        this.produkt = produkt;
    }
}

