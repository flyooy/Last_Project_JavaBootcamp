package de.sp.trashNothing_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.sp.trashNothing_backend.entities.enumClass.Kategorie;
import de.sp.trashNothing_backend.entities.enumClass.Zustand;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "Titel darf nicht leer sein")
    private String titel;

    @Column(length = 1000)
    @NotBlank(message = "Beschreibung darf nicht leer sein")
    @Size(max = 1000, message = "Die Beschreibung darf maximal 1000 Zeichen lang sein")
    private String beschreibung;

    @Min(value = 0, message = "Die Anzahl muss mindestens 0 sein")
    private int anzahl;

    @DecimalMin(value = "0.0", inclusive = false, message = "Der Preis muss größer als 0 sein")
    private BigDecimal preis;

    @NotNull(message = "Zustand darf nicht leer sein")
    @Enumerated(EnumType.STRING)
    private Zustand zustand;

    @NotBlank(message = "Marke darf nicht leer sein")
    private String marke;

    private boolean lieferung;

    @Column(length = 1300)
    private String imgUrl;

    @Column(length = 1300)
    private String deleteUrl;

    @NotNull(message = "Kategorie darf nicht leer sein")
    @Enumerated(EnumType.STRING)
    private Kategorie kategorie;

    @ManyToOne
    @JoinColumn(name = "benutzer_id")
    private Benutzer benutzer;

    @OneToMany(mappedBy = "produkt")
    private List<WunschSet_Produkt> wunschSetProdukte;

    @OneToMany(mappedBy = "produkt")
    private List<GekauftSet_Produkt> gekauftSetProdukte;

    public Produkt() {
    }

    public Produkt(String titel, String beschreibung, int anzahl, BigDecimal preis, Zustand zustand, String marke, boolean lieferung, String imgUrl, String deleteUrl, Kategorie kategorie, Benutzer benutzer) {
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.anzahl = anzahl;
        this.preis = preis;
        this.zustand = zustand;
        this.marke = marke;
        this.lieferung = lieferung;
        this.imgUrl = imgUrl;
        this.deleteUrl = deleteUrl;
        this.kategorie = kategorie;
        this.benutzer = benutzer;
    }

    public Produkt(String titel, String beschreibung, int anzahl, BigDecimal preis, Zustand zustand, String marke, boolean lieferung, Kategorie kategorie, Benutzer benutzer) {
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.anzahl = anzahl;
        this.preis = preis;
        this.zustand = zustand;
        this.marke = marke;
        this.lieferung = lieferung;
        this.kategorie = kategorie;
        this.benutzer = benutzer;
    }
}