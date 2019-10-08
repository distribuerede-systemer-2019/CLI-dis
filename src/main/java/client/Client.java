package client;

import com.google.gson.Gson;
import server.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    //configure your client here
    private static final String BASE = "http://localhost:8080/api/";

    public static void main(String[] args) {
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
                    clearConsole();
                    System.out.println("Enter user id");
                    try {
                        int userid = Integer.parseInt(br.readLine());
                        User u = getUser(userid);
                        if(u == null) System.out.println("No user by that id");
                        else System.out.println(u);
                        returnToMainMenu();
                    } catch (Exception e) {
                        System.out.println("invalid input\nReturning to main menu");
                        try {
                            returnToMainMenu();
                        } catch (Exception ee) {
                            System.out.println(ee.getMessage());
                        }
                    }

                case 3:
                    clearConsole();
                    System.out.println("Enter user id");
                    try {
                        int userId = Integer.parseInt(br.readLine());
                        System.out.println("Enter user name");
                        String userName = br.readLine();
                        System.out.println("Enter user age");
                        int userAge = Integer.parseInt(br.readLine());
                        User u = new User(userId, userName, userAge);
                        addUser(u);
                        returnToMainMenu();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("invalid input\nReturning to main menu");
                        try {
                            returnToMainMenu();
                        } catch(Exception eee) {
                            System.out.println(eee.getMessage());
                        }
                    }
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

    public static void getAllUsers() {
        String url = BASE+"users";
        try {
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

            returnToMainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static User getUser(int id) throws Exception {
        String url = BASE+"users/"+id;
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

        User u = new Gson().fromJson(response.toString(), User.class);
        return u;
    }

    public static void addUser(User u) {
        //String url = BASE+"users";
        String url = "http://localhost:8080/api/users/";
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonInputString = new Gson().toJson(u);
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes();
                os.write(input, 0, input.length);
            } catch(Exception e) {
                System.out.println("Something went wrong");
                try {
                    returnToMainMenu();
                } catch (Exception ee) {
                    System.out.println(ee.getMessage());
                }
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
                br.close();
                System.out.println("User created");
            } catch (Exception e3) {
                System.out.println(e3.getMessage());
            }
        } catch (Exception eee) {
            System.out.println(eee.getMessage());
        }
    }

    public static void returnToMainMenu() throws Exception {
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
