package threadlocal;

public class SequenceC implements Sequence{

    private static MyThreadLocal<Integer> numberContainer = new MyThreadLocal<Integer>() {
        @Override
        protected Integer initValue() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get()+1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceC();

        Thread clientA = new ClientThread(sequence);
        Thread clientB = new ClientThread(sequence);
        Thread clientC = new ClientThread(sequence);

        clientA.start();
        clientB.start();
        clientC.start();
    }
}
