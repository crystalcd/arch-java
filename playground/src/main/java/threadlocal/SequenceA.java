package threadlocal;

public class SequenceA implements Sequence{

    private static int number = 0;

    @Override
    public int getNumber() {
        return ++number;
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceA();

        Thread clientA = new ClientThread(sequence);
        Thread clientB = new ClientThread(sequence);
        Thread clientC = new ClientThread(sequence);

        clientA.start();
        clientB.start();
        clientC.start();
    }
}
