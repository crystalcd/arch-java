package threadlocal;

public class SequenceB implements Sequence{

    private static ThreadLocal<Integer> numberContainer = ThreadLocal.withInitial(() -> 0);

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get()+1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceB();

        Thread clientA = new ClientThread(sequence);
        Thread clientB = new ClientThread(sequence);
        Thread clientC = new ClientThread(sequence);

        clientA.start();
        clientB.start();
        clientC.start();
    }
}
