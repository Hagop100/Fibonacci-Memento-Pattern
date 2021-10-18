import java.util.*;
import java.math.BigInteger;

class Memento {
    private BigInteger state;

    public Memento(BigInteger state) {
        this.state = state;
    }

    public BigInteger getState() {
        return state;
    }
}

class Originator {
    private BigInteger state;

    public void setState(BigInteger state) {
        this.state = state;
    }

    public BigInteger getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(this.state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}

class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }

    public int size() {
        return mementoList.size();
    }
}

public class Fibonacci {

    public static BigInteger fibo(int n, Originator originator, CareTaker arrList) {
        if (arrList.size() > n) {
            return arrList.get(n).getState();
        }

        if (n == 0 || n == 1) {
            originator.setState(BigInteger.valueOf(1));
            arrList.add(originator.saveStateToMemento());
            return arrList.get(n).getState();
        }

        originator.setState(fibo(n - 2, originator, arrList).add(fibo(n - 1, originator, arrList)));
        arrList.add(originator.saveStateToMemento());
        return arrList.get(n).getState();
    }
    
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker arrList = new CareTaker();
        long start, end;
        double duration;
        BigInteger f;

        for(int i = 0; i <= 1000; i++) {
            start = System.currentTimeMillis();
            f = fibo(i, originator, arrList);
            end = System.currentTimeMillis();
            duration = (end - start)/1000.0;
            System.out.printf("Fibo(%d): %12d (Secs: %5.3f)\n",i,f,duration);
        }
    }
}
