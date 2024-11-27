package de.sp.trashNothing_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Benutzer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "Name darf nicht leer sein")
    private String name;

    @Email(message = "Ungültige E-Mail-Adresse")
    @NotBlank(message = "E-Mail darf nicht leer sein")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Passwort darf nicht leer sein")
    @Size(min = 3, message = "Das Passwort muss mindestens 8 Zeichen lang sein")
    private String password;

    @NotBlank(message = "PLZ darf nicht leer sein")
    @Pattern(regexp = "\\d{5}", message = "PLZ muss aus 5 Ziffern bestehen")
    private String plz;

    @NotBlank(message = "Orte darf nicht leer sein")
    private String orte;

    @NotBlank(message = "Straße darf nicht leer sein")
    private String addressStrasse;

    @Pattern(regexp = "\\+?\\d{10,15}", message = "Ungültige Handynummer")
    private String handynummer;

    @OneToMany(mappedBy = "benutzer") // muss überlegen ob auch cascade
    private Set<Produkt> verkaufSet = new HashSet<>();
    ;

    @OneToMany(mappedBy = "benutzer", cascade = CascadeType.ALL)
    private Set<WunschSet> wunschSet;

    @OneToMany(mappedBy = "benutzer", cascade = CascadeType.ALL)
    private List<GekauftList> gekauftSet;

    public Benutzer() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Benutzer"));
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }
}