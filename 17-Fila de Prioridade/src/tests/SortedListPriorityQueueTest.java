package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Comparator;
import org.junit.jupiter.api.Test;
import exceptions.EmptyPriorityQueueException;
import pessoa.PessoaComparator;
import pessoa.Pessoa;
import source.Entry;
import source.SortedListPriorityQueue;

class SortedListPriorityQueueTest {

	@Test
	void test() {
		void teste_basico() {
			SortedListPriorityQueue<Integer, String> P = new SortedListPriorityQueue<Integer, String>();
			Entry<Integer, String> saida;

			saida = P.insert(5, "A");
			assertEquals("(5,A)", saida.toString());
			assertEquals("[(5,A)]", P.toString());

			saida = P.insert(9, "C");
			assertEquals("(9,C)", saida.toString());
			assertEquals("[(5,A), (9,C)]", P.toString());

			saida = P.insert(3, "B");
			assertEquals("(3,B)", saida.toString());
			assertEquals("[(3,B), (5,A), (9,C)]", P.toString());

			saida = P.insert(7, "D");
			assertEquals("(7,D)", saida.toString());
			assertEquals("[(3,B), (5,A), (7,D), (9,C)]", P.toString());

			saida = P.min();
			assertEquals("(3,B)", saida.toString());
			assertEquals("[(3,B), (5,A), (7,D), (9,C)]", P.toString());

			saida = P.removeMin();
			assertEquals("(3,B)", saida.toString());
			assertEquals("[(5,A), (7,D), (9,C)]", P.toString());
			assertEquals(3, P.size());

			saida = P.removeMin();
			assertEquals("(5,A)", saida.toString());
			assertEquals("[(7,D), (9,C)]", P.toString());

			saida = P.removeMin();
			assertEquals("(7,D)", saida.toString());
			assertEquals("[(9,C)]", P.toString());

			saida = P.removeMin();
			assertEquals("(9,C)", saida.toString());
			assertEquals("[]", P.toString());

			assertThrows(EmptyPriorityQueueException.class, () -> { P.removeMin(); });

	}
		
	@Test
	void teste_comparador_de_Pessoa() {
		
		SortedListPriorityQueue<Pessoa, Pessoa> P = new SortedListPriorityQueue<Pessoa, Pessoa>();
		Entry<Pessoa, Pessoa> saida;
		Pessoa p1;

		p1 = new Pessoa("J", 20);
		saida = P.insert(p1, null);
		assertEquals("(Pessoa {nome=J, idade=20},null)", saida.toString());
		assertEquals("[(Pessoa {nome=J, idade=20},null)]", P.toString());

		p1 = new Pessoa("M", 30);
		saida = P.insert(p1, null);
		assertEquals("(Pessoa {nome=M, idade=30},null)", saida.toString());
		assertEquals("[(Pessoa {nome=J, idade=20},null), (Pessoa {nome=M, idade=30},null)]", P.toString());

		p1 = new Pessoa("F", 25);
		saida = P.insert(p1, null);
		assertEquals("(Pessoa {nome=F, idade=25},null)", saida.toString());
		assertEquals("[(Pessoa {nome=J, idade=20},null), (Pessoa {nome=F, idade=25},null), (Pessoa {nome=M, idade=30},null)]", P.toString());
	}

	@Test
	void teste_comparador_externo_de_Pessoa() {
	
		Comparator<Pessoa> compa = new PessoaComparator();
		SortedListPriorityQueue<Pessoa, Pessoa> P = new SortedListPriorityQueue<Pessoa, Pessoa>(compa);
		Entry<Pessoa, Pessoa> saida;
		Pessoa p1;
	
		p1 = new Pessoa("J", 20);
		saida = P.insert(p1, null);
		assertEquals("(Pessoa {nome=J, idade=20},null)", saida.toString());
		assertEquals("[(Pessoa {nome=J, idade=20},null)]", P.toString());
	
		p1 = new Pessoa("M", 30);
		saida = P.insert(p1, null);
		assertEquals("(Pessoa {nome=M, idade=30},null)", saida.toString());
		assertEquals("[(Pessoa {nome=J, idade=20},null), (Pessoa {nome=M, idade=30},null)]", P.toString());
	
		p1 = new Pessoa("F", 25);
		saida = P.insert(p1, null);
		assertEquals("(Pessoa {nome=F, idade=25},null)", saida.toString());
		assertEquals("[(Pessoa {nome=F, idade=25},null), (Pessoa {nome=J, idade=20},null), (Pessoa {nome=M, idade=30},null)]", P.toString());
	}

}
