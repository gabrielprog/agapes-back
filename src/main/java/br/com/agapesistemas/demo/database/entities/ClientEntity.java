package br.com.agapesistemas.demo.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tb_clientes")
@Data
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false)
    private String CNPJ;


    @Column(nullable = false, length = 17)
    private String RG;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(length = 20)
    private String complement;

    @Column(nullable = false, length = 20)
    private String neighborhood;

    @Column(length = 8)
    private String postal;

    @Column(nullable = false, length = 20)
    private String city;

    @Column(nullable = false, length = 2)
    private String UFState;

    @Column(length = 13)
    private String tel;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(length = 150)
    private String observation;

}
