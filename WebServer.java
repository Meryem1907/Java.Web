import java.io.*;
import java.net.*;

public class WebServer {

    public static void main(String[] args) {
        int port = 1989;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Web sunucusu çalışıyor...");
            System.out.println("http://localhost:" + port);

            while (true) {
                Socket socket = serverSocket.accept();
                handleClient(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true)) {

            String requestLine = in.readLine();
            System.out.println("İstek: " + requestLine);

            while (in.ready()) {
                in.readLine();
            }

            String html =
                    "<!DOCTYPE html>" +
                            "<html>" +
                            "<head>" +
                            "<meta charset='UTF-8'>" +
                            "<title>Java Web Sunucusu</title>" +
                            "</head>" +
                            "<body style='background-color:#f0f8ff; font-family:Arial, sans-serif;'>" +
                            "<h1 style='color:darkblue;'>Meryem Köseoğlu</h1>" +
                            "<h2 style='color:darkgreen;'>Öğrenci No: 1250505059</h2>" +
                            "<p style='font-size:18px; color:#333333;'>" +
                            "Merhaba. Ben Yazılım Mühendisliği öğrencisiyim. " +
                            "Java programlama dili ve socket programlama kullanarak " +
                            "basit bir web sunucusu geliştirdim." +
                            "</p>" +
                            "</body>" +
                            "</html>";

            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html; charset=UTF-8");
            out.println("Content-Length: " + html.getBytes("UTF-8").length);
            out.println();
            out.println(html);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}