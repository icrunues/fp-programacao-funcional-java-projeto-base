package exercicios;

import exercicios.base.Aula;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Manoel Campos da Silva Filho
 */
public class Aula07 extends Aula {

    private final Predicate<Estudante> mulher = estudante -> estudante.isMulher();

    private final Predicate<Estudante> mulheresAprovadas = mulher
            .and(Estudante::hasCurso)
            .and(Estudante::isAprovado);

    private final Comparator<Estudante> porCurso =
            Comparator.comparing(estudante -> estudante.getCurso().getNome());

    private final Comparator<Estudante> porNota =
            Comparator.comparingDouble(Estudante::getNota);

    private final Comparator<Estudante> porCursoENota =
            porCurso.thenComparing(porNota);

    private final Comparator<Estudante> porCursoDecrescenteENotaCrescente =
            porCurso.reversed().thenComparing(porNota);

    private final Comparator<Estudante> porCursoENotaDecrescentes =
            porCursoENota.reversed();

    private final Comparator<Estudante> porCursoCrescenteENotaDecrescente =
            porCurso.thenComparing(porNota.reversed());

    public Aula07() {
    }

    public static void main(String[] args) {
        var aula = new Aula07();
        aula.getEstudantesMulheresAprovadasOrdenadasPorCursoAndNota()
                .forEach(System.out::println);
    }

    public List<Estudante> getEstudantesMulheresAprovadas() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .toList();
    }

    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoAndNota() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(porCursoENota)
                .toList();
    }

    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoDecrescenteAndNotaCrescente() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(porCursoDecrescenteENotaCrescente)
                .toList();
    }

    public List<Estudante> getEstudantesMulheresAprovadasNaoOrdenadasModificavel() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasTotalmenteDecrescente() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(porCursoENotaDecrescentes)
                .toList();
    }

    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoCrescenteAndNotaDecrescente() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(porCursoCrescenteENotaDecrescente)
                .toList();
    }
}