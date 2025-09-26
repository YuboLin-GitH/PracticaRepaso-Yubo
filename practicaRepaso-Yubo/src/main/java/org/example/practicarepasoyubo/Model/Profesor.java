package org.example.practicarepasoyubo.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * ClassName: Profesor
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author Yubo
 * @Create 16/09/2025 21:19
 * @Version 1.0
 */

enum Cargo {
    Director,
    Secretario,
    Profesor
}

public class Profesor extends Usuario{
  private String especialidad;
  private LocalDate fecha_comienzo;
  private int IRPF;
  private Cargo cargo;
  private double salario;
  private int complementoCargo;
  private int dineroAntiguedad;
  private double totalBruto;

    public Profesor(int id, String nombre, String email, String password, String especialidad, LocalDate fecha_comienzo, int IRPF, Cargo cargo) {
        super(id, nombre, email, password);
        this.especialidad = especialidad;
        this.fecha_comienzo = fecha_comienzo;
        this.IRPF = IRPF;
        this.cargo = (cargo == null) ? Cargo.Profesor : cargo;

    }

    public double calcular_salario(LocalDate fecha_comienzo) {
        double salarioBruto = 1500;
        if(cargo == Cargo.Director){
            salarioBruto += 500;
        }
        else if(cargo == Cargo.Secretario){
            salarioBruto += 300;
        }

        long anosAntiguedad = ChronoUnit.YEARS.between(fecha_comienzo, LocalDate.now());

        if (anosAntiguedad >= 3) {
            long incrementos = anosAntiguedad / 3;
            salarioBruto += incrementos * 90;
        }

        double salarioNeto = salarioBruto * (1 - IRPF / 100.0);
        this.salario = salarioNeto;
        return salario;
    }

    public int complementoCargo(){
        int i = 0;

        if(cargo == Cargo.Director){
            i += 500;
        }
        else if(cargo == Cargo.Secretario){
            i += 300;
        }
        this.complementoCargo = i;
        return complementoCargo;
    };

    public int dineroAntiguedad(){
        int i = 0;
        long anosAntiguedad = ChronoUnit.YEARS.between(fecha_comienzo, LocalDate.now());

        if (anosAntiguedad >= 3) {
            long incrementos = anosAntiguedad / 3;
            i += incrementos * 90;
        }
        this.dineroAntiguedad = i;
        return dineroAntiguedad;
    }

    public double totalBruto(){
        double salarioBruto = 1500.0;
        salarioBruto = getcomplementoCargo() + getdineroAntiguedad() + salarioBruto;
        this.totalBruto = salarioBruto;
        return totalBruto;

    }

    @Override
    public String toString() {
        return "Profesor{" +
                "especialidad='" + especialidad + '\'' +
                ", fecha_comienzo=" + fecha_comienzo +
                ", IRPF=" + IRPF +
                ", cargo=" + cargo +
                ", salario=" + salario +
                '}';
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDate getFecha_comienzo() {
        return fecha_comienzo;
    }

    public void setFecha_comienzo(LocalDate fecha_comienzo) {
        this.fecha_comienzo = fecha_comienzo;
    }

    public int getIRPF() {
        return IRPF;
    }

    public void setIRPF(int IRPF) {
        this.IRPF = IRPF;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public int getcomplementoCargo() {
        return complementoCargo;
    }

    public int getdineroAntiguedad() {
        return dineroAntiguedad;
    }

    public double getTotalBruto() {
        return totalBruto;
    }

    public String generarNomina(LocalDate fecha) throws IOException {

        complementoCargo();
        dineroAntiguedad();
        totalBruto();


        double salario = calcular_salario(fecha);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fecha.format(formatter);


        String nombreFichero = String.format("%d%s%s.txt",
                getId(), getNombre().replace(" ", ""), fechaFormateada);


        StringBuilder contenido = new StringBuilder();
        contenido.append("===== NÓMINA DE PAGO =====\n");
        contenido.append(String.format("Nombre: %s", getNombre()));
        contenido.append(String.format("\t\t\tEspecialidad: %s\n", getEspecialidad()));
        contenido.append(String.format("Cargo: %s", getCargo()));
        contenido.append("\t\tAntigüedad: ").append(getFecha_comienzo()).append("\n");

        contenido.append(String.format("Mes: %s",
                fecha.format(DateTimeFormatter.ofPattern("MM"))));
        contenido.append(String.format("\tAño: %s\n",
                fecha.format(DateTimeFormatter.ofPattern("yyyy"))));
        contenido.append("CONCEPTO\t\tIMPORTE\n");
        contenido.append("--------------------------------------------------\n");
        contenido.append("Salario Base\t\t1500 €\n");
        contenido.append("Complemento Cargo\t").append(complementoCargo()).append(" €\n");
        contenido.append("Antigüedad\t\t").append(dineroAntiguedad()).append(" €\n");
        contenido.append("-------------------------------------------\n");
        contenido.append(String.format("Total(Bruto): ")).append(getTotalBruto()).append(" €\n");
        contenido.append(String.format("IRPF aplicado: %d%%\n", getIRPF()));
        contenido.append(String.format("TOTAL (Neto, a percibir): %.2f €\n", salario));
        contenido.append("==========================");


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero))) {
            writer.write(contenido.toString());
        }

        return nombreFichero;
    }
}
