package tad_arvore_binaria_completa;
import java.util.ArrayList;
import java.util.Iterator;
import exceptions.BoundaryViolationException;
import exceptions.EmptyTreeException;
import exceptions.InvalidPositionException;
import position.Position;
import tad_lista_de_nodos.NodePositionList;
import tad_lista_de_nodos.PositionList;

public class ArrayListCompleteBinaryTree<E> implements CompleteBinaryTree<E> {
	protected ArrayList<BTPos<E>> T; // Lista indexada dos posições da árvore
	
	protected static class BTPos<E> implements Position<E> {
		E element; 
		int index; 
		public BTPos(E elt, int i) {
			element = elt;
			index = i;
		}
		public E element() { return element; }
		public int index() { return index; }
		public E setElement(E elt) {
			E temp = element;
			element = elt;
			return temp;
		}
		public String toString() { return ("[" + element + "," + index + "]"); }
	}
	
	public ArrayListCompleteBinaryTree() {
		T = new ArrayList<BTPos<E>>();
		T.add(0, null); 
	}
		
	public int size() { return T.size() - 1; }
	
	public boolean isEmpty() { return (size() == 0); }

	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return hasLeft(v); 
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !isInternal(v);
	}
		
	protected BTPos<E> checkPosition(Position<E> v) throws InvalidPositionException {
		if (v == null || !(v instanceof BTPos)) throw new InvalidPositionException("Position is invalid");
		return (BTPos<E>) v;
	}
		
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTPos<E> vv = checkPosition(v);
		return vv.index() == 1;
	}
		
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTPos<E> vv = checkPosition(v);
		return 2 * vv.index() <= size();
	}

	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTPos<E> vv = checkPosition(v);
		return 2 * vv.index() + 1 <= size();
	}
	/** Returns the root of the tree. */
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty()) throw new EmptyTreeException("Tree is empty");
		return T.get(1);
	}
		
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if (!hasLeft(v)) throw new BoundaryViolationException("No left child");
		BTPos<E> vv = checkPosition(v);
		return T.get(2 * vv.index());
	}
		
	public Position<E> right(Position<E> v) throws InvalidPositionException {
		if (!hasRight(v)) throw new BoundaryViolationException("No right child");
		BTPos<E> vv = checkPosition(v);
		return T.get(2 * vv.index() + 1);
	}
		
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if (isRoot(v)) throw new BoundaryViolationException("No parent");
		BTPos<E> vv = checkPosition(v);
		return T.get(vv.index() / 2);
	}
		
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		PositionList<Position<E>> children = new NodePositionList<Position<E>>();
		if (hasLeft(v)) children.addLast(left(v));
		if (hasRight(v)) children.addLast(right(v));
		return children;
	}

	public Iterable<Position<E>> positions() {
		ArrayList<Position<E>> P = new ArrayList<Position<E>>();
		Iterator<BTPos<E>> iter = T.iterator();
		iter.next(); // Pula a primeira posição
		while (iter.hasNext()) P.add(iter.next());
		return P;
	}
		
	public E replace(Position<E> v, E o) throws InvalidPositionException {
		BTPos<E> vv = checkPosition(v);
		return vv.setElement(o);
	}
		
	public Position<E> add(E e) {
		int i = size() + 1;
		BTPos<E> p = new BTPos<E>(e, i);
		T.add(i, p);
		return p;
	}
		
	public E remove() throws EmptyTreeException {
		if (isEmpty()) throw new EmptyTreeException("Tree is empty");
		return T.remove(size()).element();
	}
		
	public Position<E> sibling(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		try {
			Position<E> p = parent(v);
			Position<E> lc = left(p);
			if (v == lc) return right(p);
			else return lc;
		} catch (BoundaryViolationException e) { throw new BoundaryViolationException("Node has no sibling"); }
	}
	
	public void swapElements(Position<E> v, Position<E> w) throws InvalidPositionException {
		BTPos<E> vv = checkPosition(v);
		BTPos<E> ww = checkPosition(w);
		E temp = vv.element();
		vv.setElement(ww.element());
		ww.setElement(temp);
	}
		
	public Iterator<E> iterator() {
		ArrayList<E> list = new ArrayList<E>();
		Iterator<BTPos<E>> iter = T.iterator();
		iter.next();
		while (iter.hasNext()) list.add(iter.next().element());
		return list.iterator();
	}
		
	public String toString() { return T.toString(); }
}
