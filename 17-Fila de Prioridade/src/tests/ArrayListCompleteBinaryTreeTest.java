package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import tad_arvore_binaria_completa.ArrayListCompleteBinaryTree;

class ArrayListCompleteBinaryTreeTest {

	@Test
	void test() {
		void test() {
			ArrayListCompleteBinaryTree<Integer> T = new ArrayListCompleteBinaryTree<Integer>();
			T.add(4);
			T.add(5);
			T.add(6);
			T.add(15);
			T.add(9);
			T.add(7);
			T.add(20);
			T.add(16);
			T.add(25);
			T.add(14);
			T.add(12);
			T.add(11);
			T.add(8);
			assertEquals(
					"[null, [4,1], [5,2], [6,3], [15,4], [9,5], [7,6], [20,7], [16,8], [25,9], [14,10], [12,11], [11,12], [8,13]]",
					T.toString());
		}
}
