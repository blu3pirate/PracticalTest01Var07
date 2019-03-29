package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("ro.pub.cs.systems.eim.arraylist");
        int[] arr = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = random.nextInt(10);
        }

        intent.putExtra("msg", arr);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
