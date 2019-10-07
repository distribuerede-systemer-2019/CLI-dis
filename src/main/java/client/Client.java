package client;

import com.google.gson.Gson;
import server.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    //configure your client here
    private static final String BASE = "http://localhost:8080/api/";

    public static void main(String[] args) throws Exception {
        clearConsole();
        System.out.println("Main menu:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Create new user");
        System.out.println("4. Exit the program");
        System.out.println("");
        System.out.print("Please select an option from 1-4\r\n");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            switch (Integer.parseInt(br.readLine())) {
                case 1:
                    System.out.println("\nGetting all users");
                    getAllUsers();
                    System.out.println("\n returning to main menu \n");

                    main(null);
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.exit(1);
                    break;
                default:
                    System.out.println("invalid selection. Returning to main menu\n");
                    main(null);
            }
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your input!\r\n");
            main(null);
            //System.exit(1);
        }
    }

    public static void getAllUsers() throws Exception{
        String url = BASE+"users";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        User[] users = new Gson().fromJson(response.toString(), User[].class);
        for(User u : users) {
            System.out.println(u);
        }


        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        System.out.println("Press any key to return to main menu");
        br.readLine();
        main(null);
    }

    public static void clearConsole() {
        System.out.println(new String(new char[50]).replace("\0", "\r\n")); //work around to clear console
    }
}
