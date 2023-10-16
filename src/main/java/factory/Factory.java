package factory;

import com.aplicaoestagio.aplicacaoestagio.entidades.Aluno;
import com.aplicaoestagio.aplicacaoestagio.entidades.Empresa;
import com.aplicaoestagio.aplicacaoestagio.entidades.Estagio;
import com.aplicaoestagio.aplicacaoestagio.entidades.Orientador;

import java.time.LocalDate;

public class Factory {

    public static Aluno criarAluno() {
        return new Aluno();
    }

    public static Empresa criarEmpresa() {
        return new Empresa();
    }

    public static Estagio criarEstagio(LocalDate inicio, LocalDate fim, int cargaHoraria, String status) {
        return new Estagio(inicio, fim, cargaHoraria, status);
    }

    public static Orientador criarOrientador() {
        return new Orientador();
    }
}
