public class Main {
    static final int COUNT = 5;
    static final int PAUSE = 1000;

    static ToggleSwitch toggleSwitch = new ToggleSwitch();

    public static void main(String[] args) throws InterruptedException {
        Runnable user = (() -> {
            for (int i = 0; i < COUNT;) {
                synchronized (toggleSwitch) {
                    if (toggleSwitch.isToggleSwitch() == false) {
                        toggleSwitch.setToggleSwitch(true);
                        System.out.println("Тумблер включен");
                        i++;
                        try {
                            Thread.sleep(PAUSE);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Runnable box = (() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (toggleSwitch) {
                    if (toggleSwitch.isToggleSwitch() == true) {
                        toggleSwitch.setToggleSwitch(false);
                        System.out.println("Тумблер выключен");
                    }
                }
            }
        });

        Thread userThread = new Thread(user);
        Thread boxThread = new Thread(box);

        userThread.start();
        boxThread.start();

        userThread.join();
        Thread.sleep(PAUSE);
        boxThread.interrupt();
    }
}
