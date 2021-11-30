package fop.w8trees;

import java.util.Comparator;
import java.util.function.Predicate;

public class InnerNode<T> implements TreeElement<T> {
    private T info;
    private TreeElement<T> left;
    private TreeElement<T> right;

    public InnerNode(T info) {
        this.info = info;
        this.left = new Leaf<T>();
        this.right = new Leaf<T>();
    }

    @Override
    public TreeElement<T> insert(T value, Comparator<T> comp) {
        if(comp.compare(value,info) < 0){
            left = left.insert(value, comp);
        } else {
            right = right.insert(value, comp);
        }
        return this;
    }

    @Override
    public void toString(StringBuilder sb) {
        left.toString(sb);
        sb.append(info);
        right.toString(sb);
    }

    @Override
    public int size() {
        return left.size() + 1 + right.size();
    }

    @Override
    public T getMin() {
        if(left instanceof Leaf) return info;
        return left.getMin();
    }

    @Override
    public TreeElement<T> remove(T value, Comparator<T> comp) {
        if(comp.compare(value,info)==0) {
            if(right instanceof Leaf) return left;
            info = right.getMin();
            right = right.remove(info,comp);
        }
        else if(comp.compare(value,info)<0)
            left = left.remove(value,comp);

        else if(comp.compare(value,info)>0)
            right = right.remove(value,comp);

        return this;
    }

    @Override
    public boolean contains(T value, Comparator<T> comp) {
        if(comp.compare(value,info) == 0) return true;
        boolean findLeft = left.contains(value,comp);
        boolean findRight = right.contains(value,comp);
        return (findLeft || findRight);
    }

    @Override
    public int countMatches(Predicate<T> filter) {
        return left.countMatches(filter) + (filter.test(info)? 1:0) + right.countMatches(filter);

    }

    @Override
    public int getAll(Predicate<T> filter, T[] array, int index) {
        index = left.getAll(filter, array, index);
        if(filter.test(info)) {
            array[index] = info;
            index++;
        }
        index = right.getAll(filter, array, index);
        return index;
    }
}
