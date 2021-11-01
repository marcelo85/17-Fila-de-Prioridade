package pessoa;

import java.util.Comparator;

public class PessoaComparator implements Comparator<Pessoa> {
	public int compare(Pessoa p1, Pessoa p2) {
        return p1.getNome().compareTo(p2.getNome());
	}
}
