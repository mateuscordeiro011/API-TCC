    package INF2BN_2024_0_EQUIPE02.api.domain;

  

import jakarta.persistence.*;
import java.time.LocalDate;


    @Entity
    @Table(name = "Animais")
    public class Animal {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id_animal")
        private Long id_animal;

        @Column(name = "Nome", nullable = true, length = 100)
        private String nome;

        @Column(name = "Especie", nullable = true, length = 20)
        private String especie;

        @Column(name = "Raca", nullable = true, length = 40)
        private String raca;

        @Column(name = "Data_nascimento", nullable = true)
        private LocalDate data_nascimento;

        @Column(name = "Peso", nullable = true)
        private float peso;

        @Column(name = "Sexo", nullable = true, length = 1) // M ou F
        private String sexo;

        @Column(name = "foto", nullable = true)
        private byte[] foto;

        @Column(name = "Status", nullable = true)
        private boolean status;

        @Column(name = "Data_cadastro", nullable = true)
        private LocalDate data;

        @ManyToOne(fetch = FetchType.LAZY, optional = true)
        @JoinColumn(name = "Id_cliente", nullable = true)
        private Cliente cliente;

        public Long getId_animal() {
            return id_animal;
        }

        public void setId_animal(Long id_animal) {
            this.id_animal = id_animal;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEspecie() {
            return especie;
        }

        public void setEspecie(String especie) {
            this.especie = especie;
        }

        public String getRaca() {
            return raca;
        }

        public void setRaca(String raca) {
            this.raca = raca;
        }

        public LocalDate getData_nascimento() {
            return data_nascimento;
        }

        public void setData_nascimento(LocalDate data_nascimento) {
            this.data_nascimento = data_nascimento;
        }

        public float getPeso() {
            return peso;
        }

        public void setPeso(float peso) {
            this.peso = peso;
        }

        public String getSexo() {
            return sexo;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }

        public byte[] getFoto() {
            return foto;
        }


        public void setFoto(byte[] foto) {
            this.foto = foto;
        }

        public boolean getStatus(){
            return status;
        }

        public void setStatus(boolean status){
            this.status = status;
        }

        public LocalDate getData(){
            return data;
        }

        public void setData(LocalDate data){
            this.data = data;
        }

        public Cliente getCliente() {
            return cliente;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }
    }