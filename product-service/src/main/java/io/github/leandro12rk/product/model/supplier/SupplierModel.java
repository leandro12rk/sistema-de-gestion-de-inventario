package io.github.leandro12rk.product.model.supplier;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers")
public class SupplierModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Missing Company Name")
    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @NotBlank(message = "Missing Contact Name ")
    @Column(name = "contact_name", length = 100)
    private String contactName;

    @Email(message = "Incorrect E-mail Format")
    @NotBlank(message = "Missing E-mail")
    private String email;

    @NotBlank(message = "Missing Phone Number")
    private String phone;

    @NotBlank(message = " Missing Address")
    private String address;
    
    @NotBlank(message = " Missing Status")
    private boolean status;

    @NotBlank(message = " Missing Country Location")
    private String country;

    @NotBlank(message = " Missing City Location")
    private String city;

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.time.LocalDateTime createdAt;
}