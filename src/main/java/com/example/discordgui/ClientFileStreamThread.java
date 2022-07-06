package com.example.discordgui;

import java.io.*;
import java.net.Socket;

public class ClientFileStreamThread implements Runnable{

    private Socket socket;
    private DataOutputStream out;

    private DataInputStream in;

    public ClientFileStreamThread() throws IOException {
        socket = new Socket("localhost",8888);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {

    }

    public void sendFile (File file) {
        try {
            if (file != null && file.exists()) {
                int bytes = 0;

                FileInputStream fileInputStream = new FileInputStream(file);
                out.writeLong(file.length());

                byte[] buf = new byte[8*1024];
                while ((bytes=fileInputStream.read(buf))!=-1){
                    out.write(buf,0,bytes);
                    out.flush();
                }
                fileInputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
