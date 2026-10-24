import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String args[]) {
        try {
            ServerSocket ss = new ServerSocket(8074);

            while (true) {
                Socket s = ss.accept();
                InputStream in = s.getInputStream();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(in));
                OutputStream out = s.getOutputStream();

                String primeiraLinha = br.readLine();
                System.out.println(primeiraLinha);

                String linha = "";
                while ((linha = br.readLine()) != null && linha.length() > 0) {
                    System.out.println(linha);
                }

                if (primeiraLinha == null || primeiraLinha.length() == 0) {
                    s.close();
                    continue;
                }

                String[] partes = primeiraLinha.split(" ");
                String metodo = partes[0];
                String caminho = partes[1];

                if (!metodo.equals("GET")) {
                    String content = "<html><body><h1>405 - Metodo nao permitido</h1></body></html>";

                    PrintWriter pw = new PrintWriter(out);
                    pw.println("HTTP/1.1 405 Method Not Allowed");
                    pw.println("Content-Type: text/html");
                    pw.println("Content-Length: " + content.length());
                    pw.println();
                    pw.print(content);
                    pw.flush();

                    s.close();
                    continue;
                }

                if (caminho.equals("/")) {
                    File pasta = new File("site");
                    File[] arquivos = pasta.listFiles();

                    String content = "<html><body><h1>Arquivos disponiveis</h1>";

                    if (arquivos != null) {
                        for (File f : arquivos) {
                            if (f.isFile() && f.getName().endsWith(".html")) {
                                content += "<a href='/" + f.getName() + "'>" + f.getName() + "</a><br>";
                            }
                        }
                    }

                    content += "</body></html>";

                    PrintWriter pw = new PrintWriter(out);
                    pw.println("HTTP/1.1 200 OK");
                    pw.println("Content-Type: text/html");
                    pw.println("Content-Length: " + content.getBytes("UTF-8").length);
                    pw.println();
                    pw.print(content);
                    pw.flush();

                    s.close();
                    continue;
                }

                if (caminho.contains("..")) {
                    String content = "<html><body><h1>403 - Acesso negado</h1></body></html>";

                    PrintWriter pw = new PrintWriter(out);
                    pw.println("HTTP/1.1 403 Forbidden");
                    pw.println("Content-Type: text/html");
                    pw.println("Content-Length: " + content.length());
                    pw.println();
                    pw.print(content);
                    pw.flush();

                    s.close();
                    continue;
                }

                File arquivo = new File("site" + caminho);

                if (!arquivo.exists() || !arquivo.isFile()) {
                    String content = "<html><body><h1>404 - Arquivo nao encontrado</h1></body></html>";

                    PrintWriter pw = new PrintWriter(out);
                    pw.println("HTTP/1.1 404 Not Found");
                    pw.println("Content-Type: text/html");
                    pw.println("Content-Length: " + content.length());
                    pw.println();
                    pw.print(content);
                    pw.flush();

                    s.close();
                    continue;
                }

                String tipo = "text/html";
                if (caminho.endsWith(".jpg") || caminho.endsWith(".jpeg")) {
                    tipo = "image/jpeg";
                } else if (caminho.endsWith(".png")) {
                    tipo = "image/png";
                } else if (caminho.endsWith(".gif")) {
                    tipo = "image/gif";
                }

                PrintWriter pw = new PrintWriter(out);
                pw.println("HTTP/1.1 200 OK");
                pw.println("Content-Type: " + tipo);
                pw.println("Content-Length: " + arquivo.length());
                pw.println();
                pw.flush();

                FileInputStream fis = new FileInputStream(arquivo);
                byte[] buffer = new byte[1024];
                int bytesLidos;

                while ((bytesLidos = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesLidos);
                }

                out.flush();
                fis.close();

                System.out.println("---FIM!---");
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}