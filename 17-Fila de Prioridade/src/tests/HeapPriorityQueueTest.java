package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import tad_fila_prioridade_heap.HeapPriorityQueue;

class HeapPriorityQueueTest {

	@Test
	void test() {
		HeapPriorityQueue<Integer, String> P = new HeapPriorityQueue<Integer, String>();
		P.insert(4, "C");
		P.insert(5, "A");
		P.insert(6, "Z");
		P.insert(15, "K");
		P.insert(9, "F");
		P.insert(7, "Q");
		P.insert(20, "B");
		P.insert(16, "X");
		P.insert(25, "J");
		P.insert(14, "E");
		P.insert(12, "H");
		P.insert(11, "S");
		P.insert(8, "W");
		assertEquals("[null, [(4,C),1], [(5,A),2], [(6,Z),3], [(15,K),4], [(9,F),5], "
				+ "[(7,Q),6], [(20,B),7], [(16,X),8], [(25,J),9], [(14,E),10], [(12,H),11], "
				+ "[(11,S),12], [(8,W),13]]", P.toString());
		P.insert(2, "T");
		assertEquals("[null, [(2,T),1], [(5,A),2], [(4,C),3], [(15,K),4], [(9,F),5], "
				+ "[(7,Q),6], [(6,Z),7], [(16,X),8], [(25,J),9], [(14,E),10], [(12,H),11], "
				+ "[(11,S),12], [(8,W),13], [(20,B),14]]", P.toString());
		P.removeMin();
		assertEquals(
				"[null, [(4,C),1], [(5,A),2], [(6,Z),3], [(15,K),4], [(9,F),5], [(7,Q),6], [(20,B),7],"
				+ " [(16,X),8], [(25,J),9], [(14,E),10], [(12,H),11], [(11,S),12], [(8,W),13]]", P.toString());
	}
}
